package org.pdm.ib.service.impl;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.converter.impl.AccountConverter;
import org.pdm.ib.model.Account;
import org.pdm.ib.retrofit.RetrofitAPIService;
import org.pdm.ib.service.AccountService;
import org.pdm.ib.service.JsonConverterService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private RetrofitAPIService retrofitAPIService;
    private AccountConverter accountConverter;

    private String data =
            "[" +
                    "{\"title\": \"Current account\", \"type\":\"CURRENT\", balance: {\"month\": \"2018-01-01 00:00:00\", \"amount\": 1450.00}}," +
                    "{\"title\": \"Savings account\", \"type\":\"SAVINGS\", balance: {\"month\": \"2018-01-01 00:00:00\", \"amount\": 6530.00}}," +
                    "{\"title\": \"Credit account\", \"type\":\"CREDIT\"}" +
                    "]";

    private JsonConverterService jsonConverterService;

    public AccountServiceImpl() {
        this.jsonConverterService = new JsonConverterServiceImpl();
        this.retrofitAPIService = RetrofitAPIService.aRetrofitApiService();
        this.accountConverter = AccountConverter.anAccountConverter();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Account> getAccounts(Long customerId) {
        List<AccountCommand> accountCommands = retrofitAPIService.getAllUsersAccounts(customerId);
        List<Account> accounts  = accountCommands.stream()
                .map(a -> accountConverter.convertToEntity(a))
                .collect(Collectors.toList());
        return accounts;
    }

    /**
     * TODO: change this
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Account getCurrentAccount() {
        return getAccounts(1L).get(0);
    }

    /**
     * TODO: change this
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Account getSavingsAccount() {
        return getAccounts(1L).get(1);
    }

    /**
     * TODO: change this
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Account getCreditAccount() {
        return getAccounts(1L).get(2);
    }
}

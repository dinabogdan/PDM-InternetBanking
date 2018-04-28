package org.pdm.ib.service.impl;

import org.pdm.ib.model.Account;
import org.pdm.ib.service.AccountService;
import org.pdm.ib.service.JsonConverterService;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private String data =
            "[" +
                "{\"title\": \"Current account\", \"type\":\"CURRENT\", balance: {\"month\": \"2018-01-01 00:00:00\", \"amount\": 1450.00}}," +
                "{\"title\": \"Savings account\", \"type\":\"SAVINGS\", balance: {\"month\": \"2018-01-01 00:00:00\", \"amount\": 6530.00}}," +
                "{\"title\": \"Credit account\", \"type\":\"CREDIT\"}" +
            "]";

    private JsonConverterService jsonConverterService;

    public AccountServiceImpl() {
        this.jsonConverterService = new JsonConverterServiceImpl();
    }

    @Override
    public List<Account> getAccounts() {
        return jsonConverterService.fromJsonArray(data, Account.class);
    }

    /**
     * TODO: change this
     */
    @Override
    public Account getCurrentAccount() {
        return getAccounts().get(0);
    }

    /**
     * TODO: change this
     */
    @Override
    public Account getSavingsAccount() {
        return getAccounts().get(1);
    }

    /**
     * TODO: change this
     */
    @Override
    public Account getCreditAccount() {
        return getAccounts().get(2);
    }
}

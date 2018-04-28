package org.pdm.ib.service.impl;

import org.pdm.ib.model.Account;
import org.pdm.ib.model.AccountBalance;
import org.pdm.ib.service.AccountBalanceService;
import org.pdm.ib.service.JsonConverterService;

import java.util.List;

public class AccountBalanceServiceImpl implements AccountBalanceService {

    private String data = "[" +
                "{\"month\": \"2018-01-01 00:00:00\", \"amount\": 1450.00}," +
                "{\"month\": \"2018-02-01 00:00:00\", \"amount\": 3356.00}," +
                "{\"month\": \"2018-03-01 00:00:00\", \"amount\": 2256.00}," +
                "{\"month\": \"2018-04-01 00:00:00\", \"amount\": 5556.00}," +
                "{\"month\": \"2018-05-01 00:00:00\", \"amount\": 6556.00}," +
                "{\"month\": \"2018-06-01 00:00:00\", \"amount\": 8956.00}," +
                "{\"month\": \"2018-07-01 00:00:00\", \"amount\": 8400.00}," +
                "{\"month\": \"2018-08-01 00:00:00\", \"amount\": 1200.00}," +
                "{\"month\": \"2018-09-01 00:00:00\", \"amount\": 3450.00}," +
                "{\"month\": \"2018-10-01 00:00:00\", \"amount\": 6500.00}," +
                "{\"month\": \"2018-11-01 00:00:00\", \"amount\": 9209.00}" +
            "]";

    private JsonConverterService jsonConverterService = new JsonConverterServiceImpl();

    @Override
    public List<AccountBalance> getLastYearAccountBalance(Account account) {
        return jsonConverterService.fromJsonArray(data, AccountBalance.class);
    }
}

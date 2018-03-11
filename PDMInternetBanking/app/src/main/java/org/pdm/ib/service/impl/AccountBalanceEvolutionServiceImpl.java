package org.pdm.ib.service.impl;

import org.pdm.ib.model.AccountBalance;
import org.pdm.ib.service.AccountBalanceEvolutionService;
import org.pdm.ib.service.JsonConverterService;

import java.util.List;

public class AccountBalanceEvolutionServiceImpl implements AccountBalanceEvolutionService {

    private String data = "[" +
                "{\"month\": \"2018-01-01 00:00:00\", \"amount\": 1450.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-02-01 00:00:00\", \"amount\": 3356.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-03-01 00:00:00\", \"amount\": 2256.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-04-01 00:00:00\", \"amount\": 5556.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-05-01 00:00:00\", \"amount\": 6556.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-06-01 00:00:00\", \"amount\": 8956.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-07-01 00:00:00\", \"amount\": 8400.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-08-01 00:00:00\", \"amount\": 1200.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-09-01 00:00:00\", \"amount\": 3450.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-10-01 00:00:00\", \"amount\": 6500.00, \"iban\": \"RO23INGB000099995673092123\"}," +
                "{\"month\": \"2018-11-01 00:00:00\", \"amount\": 10209.00, \"iban\": \"RO23INGB000099995673092123\"}" +
            "]";

    private JsonConverterService jsonConverterService = new JsonConverterServiceImpl();

    @Override
    public List<AccountBalance> getLastYearAccountBalance(String iban) {
        return jsonConverterService.fromJsonArray(data, AccountBalance.class);
    }
}

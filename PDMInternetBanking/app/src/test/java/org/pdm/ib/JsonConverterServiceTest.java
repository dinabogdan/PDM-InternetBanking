package org.pdm.ib;

import android.util.Log;

import org.json.JSONException;
import org.junit.Test;
import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.model.AccountBalance;
import org.pdm.ib.retrofit.RetrofitAPIService;
import org.pdm.ib.service.JsonConverterService;
import org.pdm.ib.service.impl.JsonConverterServiceImpl;

import java.util.List;

import retrofit2.Retrofit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JsonConverterServiceTest {

    private JsonConverterService jsonConverterService = new JsonConverterServiceImpl();

    @Test
    public void test_convert_json_array_string_to_list_of_account_balance() throws JSONException {
        String input = "[" +
                "{\"month\": \"2017-12-01 00:00:00\", \"amount\": 1450.00, \"iban\": \"RO23INGB000099993333222432\"}, " +
                "{\"month\": \"2018-01-01 00:00:00\", \"amount\": 3356.00, \"iban\": \"RO23INGB000099995673092123\"}" +
                "]";

        List<AccountBalance> data = jsonConverterService.fromJsonArray(input, AccountBalance.class);

        assertNotNull(data);
        assertThat(data.size(), is(2));

        AccountBalance balance1 = data.get(0);
        AccountBalance balance2 = data.get(1);

        assertThat(1900 + balance1.getMonth().getYear(), is(2017));
        assertThat(1 + balance1.getMonth().getMonth(), is(12));
        assertThat(balance1.getAmount(), is(1450.0));
        // assertThat(balance1.getIban(), is("RO23INGB000099993333222432"));

        assertThat(1900 + balance2.getMonth().getYear(), is(2018));
        assertThat(1 + balance2.getMonth().getMonth(), is(1));
        assertThat(balance2.getAmount(), is(3356.0));
        // assertThat(balance2.getIban(), is("RO23INGB000099995673092123"));
    }

    @Test
    public void test_first_api_call() {
        RetrofitAPIService retrofitAPIService = RetrofitAPIService.aRetrofitApiService();
        List<AccountCommand> accounts = retrofitAPIService.getAllUsersAccounts(1L);
        System.out.println("The size of accounts " + accounts.size());
    }
}

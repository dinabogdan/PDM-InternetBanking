package org.pdm.ib;

import org.json.JSONException;
import org.junit.Test;
import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.model.AccountBalance;
import org.pdm.ib.retrofit.RetrofitAPIInteface;
import org.pdm.ib.retrofit.RetrofitAPIService;
import org.pdm.ib.retrofit.RetrofitAPIServiceGenerator;
import org.pdm.ib.service.JsonConverterService;
import org.pdm.ib.service.impl.JsonConverterServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

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
    public void test_first_sync_api_call() {
        RetrofitAPIInteface retrofitAPIService = RetrofitAPIServiceGenerator.createService(RetrofitAPIInteface.class);
        Call<List<AccountCommand>> call = retrofitAPIService.getAllUsersAccounts(1L);
        try {
            List<AccountCommand> accounts = call.execute().body();
            System.out.println("The size of the list: " + accounts.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_first_api_call() {
        List<AccountCommand> accounts = new ArrayList<>();
        RetrofitAPIService retrofitAPIService = RetrofitAPIService.aRetrofitApiService();
        //RetrofitGenericCallBackImpl<AccountCommand> accountCommandRetrofitGenericCallBack = new RetrofitGenericCallBackImpl<>();
        /*retrofitAPIService.getAllUsersAccounts(1L, new RetrofitGenericCallBack<AccountCommand>() {
            @Override
            public List<AccountCommand> setElements(List<AccountCommand> elements) {
                System.out.println("The list: " + elements.size());
                return elements;
            }
        });*/
        System.out.println("The size of accounts " + accounts.size());
    }
}

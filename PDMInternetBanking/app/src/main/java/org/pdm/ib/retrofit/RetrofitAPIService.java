package org.pdm.ib.retrofit;

import android.os.Build;

import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.model.Account;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitAPIService {

    private RetrofitAPIInteface retrofitAPIInteface;

    protected RetrofitAPIService() {
        retrofitAPIInteface = RetrofitAPIClient.getRetrofitClient().create(RetrofitAPIInteface.class);
    }

    public static RetrofitAPIService aRetrofitApiService() {
        return new RetrofitAPIService();
    }

    public List<AccountCommand> getAllUsersAccounts(Long customerId) {
        List<AccountCommand> toReturnAccounts = new ArrayList<>();
        Call<List<AccountCommand>> call = retrofitAPIInteface.getAllUsersAccounts(customerId);
        call.enqueue(new Callback<List<AccountCommand>>() {
            @Override
            public void onResponse(Call<List<AccountCommand>> call, Response<List<AccountCommand>> response) {
                List<AccountCommand> accounts = response.body();
               // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    accounts.forEach(a -> toReturnAccounts.add(a));
                //}
            }

            @Override
            public void onFailure(Call<List<AccountCommand>> call, Throwable t) {
                call.cancel();
            }
        });
        return toReturnAccounts;
    }
}

package org.pdm.ib.retrofit;

import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.command.CustomerCommand;
import org.pdm.ib.command.TransactionCommand;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class RetrofitAPIService {

    private RetrofitAPIInteface retrofitAPIInteface;

    protected RetrofitAPIService() {
        retrofitAPIInteface = RetrofitAPIClient.getRetrofitClient().create(RetrofitAPIInteface.class);
    }

    public static RetrofitAPIService aRetrofitApiService() {
        return new RetrofitAPIService();
    }

    public List<AccountCommand> getAllUsersAccounts(Long customerId) {
        Call<List<AccountCommand>> call = retrofitAPIInteface.getAllUsersAccounts(customerId);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AccountCommand getAccountById(Long customerId, Long accountId) {
        Call<AccountCommand> call = retrofitAPIInteface.getAccountById(customerId, accountId);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AccountCommand getAccountByAcctNo(Long customerId, Long accountNo) {
        Call<AccountCommand> call = retrofitAPIInteface.getAccountByAcctNo(customerId, accountNo);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object addNewAccount(Long customerId, AccountCommand accountCommand) {
        Call<Object> call = retrofitAPIInteface.addNewAccount(customerId, accountCommand);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CustomerCommand> getAllCustomers() {
        Call<List<CustomerCommand>> call = retrofitAPIInteface.getAllCustomers();
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CustomerCommand getCustomerById(Long customerId) {
        Call<CustomerCommand> call = retrofitAPIInteface.getCustomerById(customerId);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object addNewCustomer(CustomerCommand customerCommand) {
        Call<Object> call = retrofitAPIInteface.addNewCustomer(customerCommand);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TransactionCommand> getAllTransactionForASpecificAccountByAcctId(Long customerId, Long accountId) {
        Call<List<TransactionCommand>> call = retrofitAPIInteface.getAllTransactionForASpecificAccountByAcctId(customerId, accountId);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TransactionCommand getSpecificTransactionForAcctNo(Long customerId, Long accountNo, Long transactionId) {
        Call<TransactionCommand> call = retrofitAPIInteface.getSpecificTransactionForAcctNo(customerId, accountNo, transactionId);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

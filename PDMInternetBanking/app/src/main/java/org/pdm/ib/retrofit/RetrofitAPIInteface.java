package org.pdm.ib.retrofit;

import org.pdm.ib.model.Account;
import org.pdm.ib.model.Customer;
import org.pdm.ib.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPIInteface {

    @GET("/customers/{customerId}/accounts")
    Call<List<Account>> getAllUsersAccounts(@Path("customerId") Long customerId);

    @GET("/customers/{customerId}/accoutnsByAcctId/{accountId}")
    Call<Account> getAccountById(@Path("customerId") Long customerId, @Path("accountId") Long accountId);

    @GET("/customers/{customerId}/accountsByAcctNo/{accountNumber}")
    Call<Account> getAccountByAcctNo(@Path("customerId") Long customerId, @Path("accountNumber") Long accountNumber);

    @POST("/customers/{customerId}/accounts")
    Call<Object> addNewAccount(@Body Account account);

    @GET("/customers")
    Call<List<Customer>> getAllCustomers();

    @GET("/customers/{customerId}")
    Call<Customer> getCustomerById(@Path("customerId") Long customerId);

    @POST("/customers")
    Call<Object> addNewCustomer(@Body Customer customer);

    @GET("/customers/{customerId}/accountsByAcctId/{accountId}/transactions")
    Call<List<Transaction>> getAllTransactionForASpecificAccountByAcctId(@Path("customerId") Long customerId, @Path("accountId") Long accountId);

    @GET("/customers/{customerId}/accountsByAcctNo/{accountNumber}/transactions/{transactionId}")
    Call<Transaction> getSpecificTransactionForAcctNo(@Path("customerId") Long customerId, @Path("accountNumber") Long accountNumber, @Path("transactionId") Long transactionId);
}

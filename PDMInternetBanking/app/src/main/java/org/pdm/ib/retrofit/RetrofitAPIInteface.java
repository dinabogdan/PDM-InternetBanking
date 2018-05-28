package org.pdm.ib.retrofit;

import org.pdm.ib.command.AccountBalanceCommand;
import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.command.CustomerCommand;
import org.pdm.ib.command.TransactionCommand;
import org.pdm.ib.command.UserAuthCommand;
import org.pdm.ib.model.Account;
import org.pdm.ib.model.Notification;
import org.pdm.ib.model.Transaction;
import org.pdm.ib.model.TxRecyclerView;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPIInteface {

    @GET("/customers/{customerId}/accounts")
    Call<List<AccountCommand>> getAllUsersAccounts(@Path("customerId") Long customerId);

    @GET("/customers/{customerId}/accountsByAcctId/{accountId}")
    Call<AccountCommand> getAccountById(@Path("customerId") Long customerId, @Path("accountId") Long accountId);

    @GET("/customers/{customerId}/accountsByAcctNo/{accountNumber}")
    Call<AccountCommand> getAccountByAcctNo(@Path("customerId") Long customerId, @Path("accountNumber") Long accountNumber);

    @POST("/customers/{customerId}/accounts")
    Call<Object> addNewAccount(@Path("customerId") Long customerId, @Body AccountCommand account);

    @GET("/customers")
    Call<List<CustomerCommand>> getAllCustomers();

    @GET("/customers/{customerId}")
    Call<CustomerCommand> getCustomerById(@Path("customerId") Long customerId);

    @POST("/customers")
    Call<Object> addNewCustomer(@Body CustomerCommand customer);

    @GET("/customers/{customerId}/accountsByAcctId/{accountId}/transactions")
    Call<List<TransactionCommand>> getAllTransactionForASpecificAccountByAcctId(@Path("customerId") Long customerId, @Path("accountId") Long accountId);

    @GET("/customers/{customerId}/accountsByAcctNo/{accountNumber}/transactions/{transactionId}")
    Call<TransactionCommand> getSpecificTransactionForAcctNo(@Path("customerId") Long customerId, @Path("accountNumber") Long accountNumber, @Path("transactionId") Long transactionId);

    @POST("/authorize")
    Call<UserAuthCommand> authorizeUser(@Body UserAuthCommand user);

    @GET("/balances")
    Call<List<AccountBalanceCommand>> getBalances();

    @POST("/update-balance/{transaction}")
    Call<Void> performTransaction(@Body AccountCommand account, @Path("transaction") BigDecimal transactionAmount);

    @GET("/transactions")
    Call<List<TxRecyclerView>> getTransactions();

    @POST("/transactions")
    Call<Void> addTx(@Body TxRecyclerView tx);

    @GET("/notifications")
    Call<Notification> getNotification();
}

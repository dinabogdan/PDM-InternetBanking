package org.pdm.ib.command;

import com.google.gson.annotations.SerializedName;

import org.pdm.ib.model.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AccountCommand {

    @SerializedName("id")
    public Long id;
    @SerializedName("accountNumber")
    public Integer accountNumber;
    @SerializedName("balance")
    public BigDecimal balance;
    @SerializedName("accountType")
    public AccountType accountType;
    @SerializedName("openDate")
    public Date openDate;
    @SerializedName("customer")
    public CustomerCommand customerCommand;
    @SerializedName("payers")
    public List<TransactionCommand> payers;
    @SerializedName("receivers")
    public List<TransactionCommand> receivers;

    public AccountCommand(Long id, Integer accountNumber, BigDecimal balance, AccountType accountType, Date openDate, CustomerCommand customerCommand, List<TransactionCommand> payers, List<TransactionCommand> receivers) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.openDate = openDate;
        this.customerCommand = customerCommand;
        this.payers = payers;
        this.receivers = receivers;
    }
}

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

    protected AccountCommand(Long id, Integer accountNumber, BigDecimal balance, AccountType accountType, Date openDate, CustomerCommand customerCommand, List<TransactionCommand> payers, List<TransactionCommand> receivers) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.openDate = openDate;
        this.customerCommand = customerCommand;
        this.payers = payers;
        this.receivers = receivers;
    }

    private AccountCommand(Builder builder) {
        id = builder.id;
        accountNumber = builder.accountNumber;
        balance = builder.balance;
        accountType = builder.accountType;
        openDate = builder.openDate;
        customerCommand = builder.customerCommand;
        payers = builder.payers;
        receivers = builder.receivers;
    }


    public static final class Builder {
        private Long id;
        private Integer accountNumber;
        private BigDecimal balance;
        private AccountType accountType;
        private Date openDate;
        private CustomerCommand customerCommand;
        private List<TransactionCommand> payers;
        private List<TransactionCommand> receivers;

        public Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withAccountNumber(Integer val) {
            accountNumber = val;
            return this;
        }

        public Builder withBalance(BigDecimal val) {
            balance = val;
            return this;
        }

        public Builder withAccountType(AccountType val) {
            accountType = val;
            return this;
        }

        public Builder withOpenDate(Date val) {
            openDate = val;
            return this;
        }

        public Builder withCustomerCommand(CustomerCommand val) {
            customerCommand = val;
            return this;
        }

        public Builder withPayers(List<TransactionCommand> val) {
            payers = val;
            return this;
        }

        public Builder withReceivers(List<TransactionCommand> val) {
            receivers = val;
            return this;
        }

        public AccountCommand build() {
            return new AccountCommand(this);
        }
    }
}

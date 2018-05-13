package org.pdm.ib.command;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class AccountBalanceCommand {

    @SerializedName("month")
    private Date month;
    @SerializedName("amoun")
    private BigDecimal amount;

    public AccountBalanceCommand(Date month, BigDecimal amount) {
        this.month = month;
        this.amount = amount;
    }

    private AccountBalanceCommand(Builder builder) {
        month = builder.month;
        amount = builder.amount;
    }

    public Date getMonth() {
        return month;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static final class Builder {
        private Date month;
        private BigDecimal amount;

        public Builder() {
        }

        public Builder withMonth(Date val) {
            month = val;
            return this;
        }

        public Builder withAmount(BigDecimal val) {
            amount = val;
            return this;
        }

        public AccountBalanceCommand build() {
            return new AccountBalanceCommand(this);
        }
    }
}

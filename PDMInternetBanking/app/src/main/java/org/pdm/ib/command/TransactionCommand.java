package org.pdm.ib.command;

import com.google.gson.annotations.SerializedName;

import org.pdm.ib.model.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionCommand {

    @SerializedName("txId")
    public Long txId;
    @SerializedName("payerAccount")
    public AccountCommand payerAccount;
    @SerializedName("receiverAccount")
    public AccountCommand receiverAccount;
    @SerializedName("sum")
    public BigDecimal sum;
    @SerializedName("processDate")
    public Date processDate;
    @SerializedName("transactionType")
    public TransactionType transactionType;

    public TransactionCommand(Long txId, AccountCommand payerAccount, AccountCommand receiverAccount, BigDecimal sum, Date processDate) {
        this.txId = txId;
        this.payerAccount = payerAccount;
        this.receiverAccount = receiverAccount;
        this.sum = sum;
        this.processDate = processDate;
    }

    private TransactionCommand(Builder builder) {
        txId = builder.txId;
        payerAccount = builder.payerAccount;
        receiverAccount = builder.receiverAccount;
        sum = builder.sum;
        processDate = builder.processDate;
        transactionType = builder.transactionType;
    }

    public static final class Builder {
        private Long txId;
        private AccountCommand payerAccount;
        private AccountCommand receiverAccount;
        private BigDecimal sum;
        private Date processDate;
        private TransactionType transactionType;

        public Builder() {
        }

        public Builder withTxId(Long val) {
            txId = val;
            return this;
        }

        public Builder withPayerAccount(AccountCommand val) {
            payerAccount = val;
            return this;
        }

        public Builder withReceiverAccount(AccountCommand val) {
            receiverAccount = val;
            return this;
        }

        public Builder withSum(BigDecimal val) {
            sum = val;
            return this;
        }

        public Builder withProcessDate(Date val) {
            processDate = val;
            return this;
        }

        public Builder withTransactionType(TransactionType val) {
            transactionType = val;
            return this;
        }

        public TransactionCommand build() {
            return new TransactionCommand(this);
        }
    }
}

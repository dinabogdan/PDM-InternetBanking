package org.pdm.ib.command;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionCommand {

    @SerializedName("txId")
    private Long txId;
    @SerializedName("payerAccount")
    private AccountCommand payerAccount;
    @SerializedName("receiverAccount")
    private AccountCommand receiverAccount;
    @SerializedName("sum")
    private BigDecimal sum;
    @SerializedName("processDate")
    private Date processDate;

    public TransactionCommand(Long txId, AccountCommand payerAccount, AccountCommand receiverAccount, BigDecimal sum, Date processDate) {
        this.txId = txId;
        this.payerAccount = payerAccount;
        this.receiverAccount = receiverAccount;
        this.sum = sum;
        this.processDate = processDate;
    }
}

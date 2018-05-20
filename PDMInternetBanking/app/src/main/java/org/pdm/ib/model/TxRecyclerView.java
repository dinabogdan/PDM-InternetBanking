package org.pdm.ib.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class TxRecyclerView {

    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private BigDecimal amount;

    public TxRecyclerView(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public TxRecyclerView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

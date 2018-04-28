package org.pdm.ib.model;

import java.util.Date;

public class AccountBalance {

    private Date month;
    private Double amount;

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

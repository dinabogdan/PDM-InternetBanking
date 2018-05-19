package org.pdm.ib.model;

import org.pdm.ib.model.enums.AccountType;

public class Account {

    private String title;
    private AccountType type;
    private AccountBalance balance;
    private Integer accountNo;

    public Integer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Integer accountNo) {
        this.accountNo = accountNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountBalance getBalance() {
        return balance;
    }

    public void setBalance(AccountBalance balance) {
        this.balance = balance;
    }
}

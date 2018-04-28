package org.pdm.ib.event;

import org.pdm.ib.model.Account;

public class AccountChangedEvent implements ApplicationEvent {

    private Account account;

    public AccountChangedEvent(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}

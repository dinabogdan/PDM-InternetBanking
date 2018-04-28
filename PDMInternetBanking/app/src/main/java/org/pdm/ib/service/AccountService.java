package org.pdm.ib.service;

import org.pdm.ib.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts();

    Account getCurrentAccount();
    Account getSavingsAccount();
    Account getCreditAccount();
}

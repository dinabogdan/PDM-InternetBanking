package org.pdm.ib.service;

import org.pdm.ib.model.Account;
import org.pdm.ib.model.AccountBalance;

import java.util.List;

public interface AccountBalanceService {

    List<AccountBalance> getLastYearAccountBalance(Account account);
}

package org.pdm.ib.service;

import org.pdm.ib.model.AccountBalance;

import java.util.List;

public interface AccountBalanceEvolutionService {

    List<AccountBalance> getLastYearAccountBalance(String iban);
}

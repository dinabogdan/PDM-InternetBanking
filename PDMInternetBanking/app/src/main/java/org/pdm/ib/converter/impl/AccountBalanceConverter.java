package org.pdm.ib.converter.impl;

import org.pdm.ib.command.AccountBalanceCommand;
import org.pdm.ib.converter.Converter;
import org.pdm.ib.model.AccountBalance;

public final class AccountBalanceConverter implements Converter<AccountBalanceCommand, AccountBalance> {

    @Override
    public AccountBalance convertToEntity(AccountBalanceCommand accountBalanceCommand) {
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setMonth(accountBalanceCommand.getMonth());
        accountBalance.setAmount(accountBalanceCommand.getAmount().doubleValue());
        return accountBalance;
    }

    @Override
    public AccountBalanceCommand convertToCommand(AccountBalance accountBalance) {
        return null;
    }

    public static AccountBalanceConverter anAccountBalanceConverter() {
        return new AccountBalanceConverter();
    }

}

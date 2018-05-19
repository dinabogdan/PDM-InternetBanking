package org.pdm.ib.converter.impl;

import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.converter.Converter;
import org.pdm.ib.model.Account;
import org.pdm.ib.model.AccountBalance;
import org.pdm.ib.model.enums.AccountType;
import org.pdm.ib.model.enums.AcctType;

import java.math.BigDecimal;
import java.util.Date;

public final class AccountConverter implements Converter<AccountCommand, Account> {

    private final AccountTypeConverter accountTypeConverter = AccountTypeConverter.anAccountTypeConverterInstance();

    @Override
    public Account convertToEntity(AccountCommand accountCommand) {
        Account account = new Account();
        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAmount(accountCommand.balance.doubleValue());
        accountBalance.setMonth(new Date());
        account.setBalance(accountBalance);
        account.setTitle(convertAccountTitle(accountCommand.accountType));
        account.setType(accountTypeConverter.convertToCommand(accountCommand.accountType));
        return account;
    }

    @Override
    public AccountCommand convertToCommand(Account account) {
        return new AccountCommand.Builder()
                .withBalance(BigDecimal.valueOf(account.getBalance().getAmount()))
                //.withAccountType(account.getType())
                .build();
    }

    private String convertAccountTitle(AcctType acctType) {
        switch (acctType.getValue()) {
            case 1:
                return AccountType.CURRENT.getValue();
            case 2:
                return AccountType.SAVINGS.getValue();
            case 3:
                return AccountType.CREDIT.getValue();
            default:
                return null;
        }
    }

    private AccountConverter() {

    }

    public final static AccountConverter anAccountConverter() {
        return new AccountConverter();
    }
}

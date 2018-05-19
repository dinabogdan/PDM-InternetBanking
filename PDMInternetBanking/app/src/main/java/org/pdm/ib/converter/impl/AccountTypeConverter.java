package org.pdm.ib.converter.impl;

import org.pdm.ib.converter.Converter;
import org.pdm.ib.model.enums.AccountType;
import org.pdm.ib.model.enums.AcctType;

public final class AccountTypeConverter implements Converter<AccountType, AcctType> {

    @Override
    public AcctType convertToEntity(AccountType accountType) {
        switch (accountType) {
            case CURRENT:
                return AcctType.CURRENT_ACCOUNT;
            case SAVINGS:
                return AcctType.DEPOSIT_ACCOUNT;
            case CREDIT:
                return AcctType.LOAN_ACCOUNT;
            default:
                return null;
        }
    }

    @Override
    public AccountType convertToCommand(AcctType acctType) {
        switch (acctType) {
            case CURRENT_ACCOUNT:
                return AccountType.CURRENT;
            case DEPOSIT_ACCOUNT:
                return AccountType.SAVINGS;
            case LOAN_ACCOUNT:
                return AccountType.CREDIT;
            default:
                return null;
        }
    }

    public static final AccountTypeConverter anAccountTypeConverterInstance() {
        return new AccountTypeConverter();
    }
}

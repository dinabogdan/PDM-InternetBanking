package org.pdm.ib.model.enums;

public enum AcctType {
    CURRENT_ACCOUNT(1),
    DEPOSIT_ACCOUNT(2),
    LOAN_ACCOUNT(3);

    private final int value;

    AcctType(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}

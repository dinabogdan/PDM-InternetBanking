package org.pdm.ib.model.enums;

public enum AccountType {
    CURRENT("Current account"),
    SAVINGS("Savings account"),
    CREDIT("Credit account");

    private String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

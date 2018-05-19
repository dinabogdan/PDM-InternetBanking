package org.pdm.ib.util;

import java.math.BigDecimal;

public class Validation {

    public static boolean validateAmount(String amount, BigDecimal balance) {
        String regex = "[0-9.]+";
        if (amount != null && !amount.isEmpty()) {
            if (amount.matches(regex)) {
                BigDecimal decimalAmount = new BigDecimal(amount);
                if (balance.compareTo(decimalAmount) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validateIban(String iban) {
        if (iban.length() == 24) {
            return true;
        }
        return false;
    }

}

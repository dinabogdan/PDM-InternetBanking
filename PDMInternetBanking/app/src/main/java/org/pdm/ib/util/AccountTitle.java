package org.pdm.ib.util;

import android.content.Context;

import org.pdm.ib.R;
import org.pdm.ib.model.enums.AccountType;

public class AccountTitle {

    public static String getTitleByAccountType(Context context, AccountType accountType) {
        return getTitleByAccountType(context, accountType, null);
    }

    public static String getTitleByAccountType(Context context, AccountType accountType, Integer placeholderId) {
        String title = "";

        switch (accountType) {
            case CURRENT:
                title = context.getResources().getString(R.string.current_account_title);
                break;
            case SAVINGS:
                title = context.getResources().getString(R.string.savings_account_title);
                break;
            case CREDIT:
                title = context.getResources().getString(R.string.credit_account_title);
                break;
        }

        if (placeholderId != null) {
            return context.getResources().getString(placeholderId, title);
        }

        return title;
    }
}

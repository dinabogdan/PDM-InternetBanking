package org.pdm.ib.context;

import org.pdm.ib.event.AccountChangedEvent;
import org.pdm.ib.listener.OnAccountChangedEventListener;
import org.pdm.ib.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountContextHolder {

    private static List<OnAccountChangedEventListener> accountChangedListeners = new ArrayList<>(0);

    private static Account currentAccount;

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static List<OnAccountChangedEventListener> accountChangedEventListeners() {
        return accountChangedListeners;
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;

        if (accountChangedListeners.size() > 0) {
            triggerAccountChangedEvent();
        }
    }

    private static void triggerAccountChangedEvent() {
        AccountChangedEvent event = new AccountChangedEvent(currentAccount);

        for (OnAccountChangedEventListener listener : accountChangedListeners) {
            listener.handle(event);
        }
    }

    public static void addOnAccountChangedListener(OnAccountChangedEventListener listener) {
        accountChangedListeners.add(listener);
    }

    public static void removeListener(OnAccountChangedEventListener listener) {
        accountChangedListeners.remove(listener);
    }
}

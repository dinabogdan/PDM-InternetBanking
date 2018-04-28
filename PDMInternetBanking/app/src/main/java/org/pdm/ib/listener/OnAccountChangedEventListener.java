package org.pdm.ib.listener;

import org.pdm.ib.event.AccountChangedEvent;

public interface OnAccountChangedEventListener extends EventListener<AccountChangedEvent> {

    void handle(AccountChangedEvent event);
}

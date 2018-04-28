package org.pdm.ib.listener;

import org.pdm.ib.event.ApplicationEvent;

public interface EventListener<T extends ApplicationEvent> {

    void handle(T event);
}

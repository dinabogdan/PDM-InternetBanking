package org.pdm.ib.service.impl;

import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.service.RetrofitGenericCallBack;

import java.util.List;

public class RetrofitGenericCallBackImpl<T> implements RetrofitGenericCallBack {

    private List<T> elementsToSet;

    @Override
    public void setElements(List elements) {
        this.elementsToSet = elements;
    }

    public List<T> getElements() {
        return this.elementsToSet;
    }
}

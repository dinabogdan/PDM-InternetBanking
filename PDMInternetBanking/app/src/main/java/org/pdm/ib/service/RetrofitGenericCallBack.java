package org.pdm.ib.service;

import java.util.List;

public interface RetrofitGenericCallBack<T> {

    void setElements(List<T> elements);
}

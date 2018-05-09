package org.pdm.ib.service;

import java.util.List;

public interface RetrofitGenericCallBack<T> {

    List<T> setElements(List<T> elements);
}

package org.pdm.ib.service;

import java.util.List;

public interface JsonConverterService {

    <T> List<T> fromJsonArray(String jsonArrayStr, Class<T> typeOfT);
}

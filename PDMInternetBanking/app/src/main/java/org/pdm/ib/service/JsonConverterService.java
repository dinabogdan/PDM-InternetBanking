package org.pdm.ib.service;

import java.util.List;

public interface JsonConverterService {

    <T> T fromJson(String json, Class<T> typeOfT);

    <T> List<T> fromJsonArray(String jsonArrayStr, Class<T> typeOfT);
}

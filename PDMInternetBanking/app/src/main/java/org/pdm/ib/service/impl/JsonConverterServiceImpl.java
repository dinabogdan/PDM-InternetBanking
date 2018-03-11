package org.pdm.ib.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.pdm.ib.json.adapter.DateAdapter;
import org.pdm.ib.service.JsonConverterService;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonConverterServiceImpl implements JsonConverterService {

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateAdapter("yyyy-MM-dd HH:mm:ss"))
            .create();

    @Override
    public <T> List<T> fromJsonArray(String jsonArrayStr, final Class<T> typeOfT) {

        List elements = gson.fromJson(jsonArrayStr, List.class);

        List <T> converted = new ArrayList<>();

        for (Object el : elements) {
            converted.add(fromObject(el, typeOfT));
        }

        return converted;
    }

    private <T> T fromObject(Object object, Class<T> typeOfT) {

        return gson.fromJson(gson.toJson(object), typeOfT);
    }
}

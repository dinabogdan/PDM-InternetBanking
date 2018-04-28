package org.pdm.ib.json.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateAdapter implements JsonDeserializer<Date> {

    private String dateFormat;

    public DateAdapter(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(json.getAsString());
        }
        catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }
}

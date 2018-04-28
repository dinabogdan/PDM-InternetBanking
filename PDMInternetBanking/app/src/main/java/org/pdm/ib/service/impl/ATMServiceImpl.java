package org.pdm.ib.service.impl;

import org.pdm.ib.model.ATMCounty;
import org.pdm.ib.service.ATMService;
import org.pdm.ib.service.JsonConverterService;
import org.pdm.ib.util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.pdm.ib.util.ObjectsUtil.isAnyNull;

public class ATMServiceImpl implements ATMService {

    private JsonConverterService jsonConverterService = new JsonConverterServiceImpl();

    @Override
    public List<ATMCounty> findATMs() {
        return findATMs(null, null);
    }

    @Override
    public List<ATMCounty> findATMs(Double latitude, Double longitude) {
        try {
            String json = FileUtils.readToString(getClass().getClassLoader().getResourceAsStream("location.json"));

            List<ATMCounty> atms = jsonConverterService.fromJsonArray(json, ATMCounty.class);

            if (isAnyNull(latitude, longitude)) {
                return atms;
            }

            List<ATMCounty> result = new ArrayList<>();

            for (ATMCounty county : atms) {
                if (county.getLocation() == null) continue;

                if (county.getCounty().equals("Bucuresti")) {
                    result.add(county);
                }
            }

            return result;
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

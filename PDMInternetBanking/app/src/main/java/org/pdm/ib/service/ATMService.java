package org.pdm.ib.service;

import org.pdm.ib.model.ATMCounty;

import java.util.List;

public interface ATMService {

    List<ATMCounty> findATMs(Double latitude, Double longitude);
    List<ATMCounty> findATMs();
}

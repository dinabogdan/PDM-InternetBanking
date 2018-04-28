package org.pdm.ib.service;

import android.content.Context;
import android.support.annotation.Nullable;

import org.pdm.ib.model.Payment;

public interface PaymentsService {

    void makePayment(Payment payment, @Nullable Context context);
}

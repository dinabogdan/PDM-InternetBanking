package org.pdm.ib.service;

import android.content.Context;

import org.pdm.ib.model.Payment;

import java.util.List;

public interface PaymentsService {

    void makePayment(Payment payment, Context context);

    List<Payment> getSavedPayments(Context context);
}

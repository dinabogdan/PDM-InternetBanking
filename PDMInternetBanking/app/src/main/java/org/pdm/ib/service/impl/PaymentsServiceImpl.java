package org.pdm.ib.service.impl;

import android.content.Context;
import android.support.annotation.Nullable;

import org.pdm.ib.dao.PaymentDAO;
import org.pdm.ib.database.ApplicationDatabase;
import org.pdm.ib.model.Payment;
import org.pdm.ib.service.PaymentsService;
import org.pdm.ib.service.TransactionService;

public class PaymentsServiceImpl implements PaymentsService {

    private TransactionService transactionService = new TransactionServiceImpl();

    @Override
    public void makePayment(Payment payment, @Nullable Context context) {
        transactionService.newPayment(payment);

        if (payment.isSavePayment()) {
            PaymentDAO dao = ApplicationDatabase.getInstance(context).getPaymentDAO();

            dao.savePayment(payment);
        }
    }
}

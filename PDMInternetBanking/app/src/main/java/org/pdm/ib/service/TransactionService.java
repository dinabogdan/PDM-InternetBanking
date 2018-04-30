package org.pdm.ib.service;

import org.pdm.ib.model.Payment;
import org.pdm.ib.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactions();
    
    void newPayment(Payment payment);
}

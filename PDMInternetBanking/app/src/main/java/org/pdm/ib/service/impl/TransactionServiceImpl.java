package org.pdm.ib.service.impl;

import org.pdm.ib.model.Payment;
import org.pdm.ib.model.Transaction;
import org.pdm.ib.model.enums.TransactionType;
import org.pdm.ib.service.TransactionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private static List<Transaction> transactionList = new ArrayList<>();

    @Override
    public List<Transaction> getTransactions() {
        return transactionList;
    }

    @Override
    public void newPayment(Payment payment) {
        transactionList.add(convert(payment));
    }

    private Transaction convert(Payment payment) {
        Transaction transaction = new Transaction();

        transaction.setTime(new Date());
        transaction.setType(TransactionType.CREDIT);
        transaction.setAccount(payment.getAccountIban());
        transaction.setAmount(payment.getAmount());
        transaction.setReceiverIban(payment.getReceiverIban());
        transaction.setReceiverName(payment.getReceiverName());
        transaction.setBankName("PDM Bank");

        return transaction;
    }
}

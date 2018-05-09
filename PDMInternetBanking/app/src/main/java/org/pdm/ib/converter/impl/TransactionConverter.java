package org.pdm.ib.converter.impl;

import org.pdm.ib.command.TransactionCommand;
import org.pdm.ib.converter.Converter;
import org.pdm.ib.model.Transaction;

public class TransactionConverter implements Converter<TransactionCommand, Transaction> {

    @Override
    public Transaction convertToEntity(TransactionCommand transactionCommand) {
        Transaction transaction = new Transaction();
        transaction.setAccount(String.valueOf(transactionCommand.payerAccount.accountNumber));
        transaction.setAmount(transactionCommand.sum.doubleValue());
        transaction.setReceiverIban(String.valueOf(transactionCommand.receiverAccount.accountNumber));
        transaction.setTime(transactionCommand.processDate);
        transaction.setType(transactionCommand.transactionType);
        return transaction;
    }

    @Override
    public TransactionCommand convertToCommand(Transaction transaction) {
        return null;
    }
}

package org.pdm.ib.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.pdm.ib.model.Payment;

import java.util.List;

@Dao
public interface PaymentDAO {

    @Query("SELECT * FROM payment")
    List<Payment> getSavedPayments();

    @Insert
    void savePayment(Payment payment);
}

package org.pdm.ib.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import org.pdm.ib.dao.PaymentDAO;
import org.pdm.ib.model.Payment;

@Database(entities = {Payment.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static ApplicationDatabase instance;

    public abstract PaymentDAO getPaymentDAO();

    public static ApplicationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ApplicationDatabase.class, "pdm-ib-application.db")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}

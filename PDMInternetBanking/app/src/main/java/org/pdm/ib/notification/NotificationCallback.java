package org.pdm.ib.notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import org.pdm.ib.R;
import org.pdm.ib.model.Notification;

public class NotificationCallback {


    private Context context;

    public NotificationCallback(Context context) {
        this.context = context;
    }

    public void call(Notification notification) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NotificationService.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_payment_white_24px)
                .setContentTitle("Your payment was processed")
                .setContentText(notification.getMessage())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(notification.getId(), mBuilder.build());
    }
}

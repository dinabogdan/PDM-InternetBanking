package org.pdm.ib.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class NotificationService extends Service {

    public static final long POLL_INTERVAL = 30 * 1000; //30 seconds
    public static final String CHANNEL_ID = "notification_channel";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("NotificationService", "Starting notification service");

        createNotificationChannel();

        Handler handler = new Handler();
        NotificationCallback notificationCallback = new NotificationCallback(this);
        NotificationPoller notificationPoller = new NotificationPoller(
                handler,
                POLL_INTERVAL,
                notificationCallback);

        handler.post(notificationPoller);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("NotificationService", "Binding notification service");

        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel description");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

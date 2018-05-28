package org.pdm.ib.notification;

import android.os.Handler;

import org.pdm.ib.model.Notification;
import org.pdm.ib.retrofit.RetrofitAPIService;

public class NotificationPoller implements Runnable {

    private Handler handler;
    private NotificationCallback callback;
    private long pollingInterval;
    private RetrofitAPIService retrofitAPIService = RetrofitAPIService.aRetrofitApiService();

    public NotificationPoller(Handler handler, long pollingInterval, NotificationCallback callback) {
        this.handler = handler;
        this.pollingInterval = pollingInterval;
        this.callback = callback;
    }

    @Override
    public void run() {
        new Thread(() -> {
            Notification notification = retrofitAPIService.getNotification();

            if (notification != null) {
                callback.call(notification);
            }
        }).start();

        handler.postDelayed(this, pollingInterval);
    }
}

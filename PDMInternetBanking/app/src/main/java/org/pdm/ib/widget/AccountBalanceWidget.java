package org.pdm.ib.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;

import org.pdm.ib.R;
import org.pdm.ib.home.activity.HomeActivity;
import org.pdm.ib.model.Account;
import org.pdm.ib.service.AccountService;
import org.pdm.ib.service.impl.AccountServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class AccountBalanceWidget extends AppWidgetProvider {

    public static final String NEW_PAYMENT_KEY = "NEW_PAYMENT_KEY";
    public static final String APP_WIDGET_ID_KEY = "WIDGET_ID_KEY";

    public static final String ACTION_REFRESH = "ACTION_REFRESH";

    private AccountService accountService = new AccountServiceImpl();

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        new Thread(() -> {
            // Construct the RemoteViews object

            Double balance = getBalance();

            new Handler(Looper.getMainLooper())
                    .post(() -> {
                        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.account_balance_widget);

                        views.setTextViewText(R.id.widgetTextViewTotalAmount, balance.toString());
                        views.setTextViewText(R.id.widgetTextViewLastUpdated, getTime());
                        views.setOnClickPendingIntent(R.id.widget_button_payment, getPaymentsIntent(context));
                        views.setOnClickPendingIntent(R.id.widgetImageViewRefresh, getRefreshIntent(context, appWidgetId));

                        appWidgetManager.updateAppWidget(appWidgetId, views);
                    });

            // Instruct the widget manager to update the widget
        }).start();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && intent.getAction().equals(ACTION_REFRESH)) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                updateAppWidget(context, AppWidgetManager.getInstance(context), extras.getInt(APP_WIDGET_ID_KEY));

                return;
            }
        }

        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private Double getBalance() {
        Double balance = 0.0;
        for (Account account : accountService.getAccounts(1L)) {
            if (account.getBalance() != null) {
                balance += account.getBalance().getAmount();
            }
        }

        return balance;
    }

    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss dd/MM", Locale.ENGLISH).format(new Date());
    }

    protected PendingIntent getPaymentsIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(NEW_PAYMENT_KEY, true);

        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    private PendingIntent getRefreshIntent(Context context, int appWidgetId) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(ACTION_REFRESH);
        intent.putExtra(APP_WIDGET_ID_KEY, appWidgetId);

        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
}


package com.ynaito.customnotificationsample;

import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);

        Notification notification = generateNotification(remoteViews);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this.getApplicationContext());

        manager.notify(generateNotificationId(), notification);
    }

    private Notification generateNotification(RemoteViews remoteViews) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContent(remoteViews);
        return builder.build();
    }

    private int generateNotificationId() {
        Random rnd = new Random();
        int ran = rnd.nextInt(Integer.MAX_VALUE);
        Log.d(TAG, "notificationId : " + ran);
        return ran;
    }
}

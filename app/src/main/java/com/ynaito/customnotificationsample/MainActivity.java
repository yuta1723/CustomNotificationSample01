package com.ynaito.customnotificationsample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private String ACTION_PAUSE = "action_pause";
    private String ACTION_SEEK_REWARD = "action_seek_reward";
    private String ACTION_SEEK_FORWARD = "action_seek_forward";

    private String ACTION_DELETE_NOTIFICATION = "action_delete_notification";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
        setActionForRemoteViews(remoteViews);
        Notification notification = generateNotification(remoteViews);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this.getApplicationContext());

        manager.notify(generateNotificationId(), notification);
    }

    private Notification generateNotification(RemoteViews remoteViews) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setVisibility(Notification.VISIBILITY_PUBLIC);
        Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.bigbuckbunny);
        builder.setLargeIcon(bmp1);
        builder.setContent(remoteViews);
        builder.setCustomBigContentView(remoteViews);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            Log.d(TAG, "this device is over lollipop");
//            MediaSessionCompat mediaSession = new MediaSessionCompat(getApplicationContext(), "naito");
//            builder.setStyle(new NotificationCompat.MediaStyle().setMediaSession(mediaSession.getSessionToken()).setShowCancelButton(true));
//        }
        builder.setPriority(Notification.PRIORITY_MAX);

        Intent deleteIntent = new Intent(this, MainActivity.class);
        deleteIntent.setAction(ACTION_DELETE_NOTIFICATION);
        PendingIntent deleteNotification = PendingIntent.getActivity(this, 0, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setDeleteIntent(deleteNotification);

        return builder.build();
    }

    private int generateNotificationId() {
        Random rnd = new Random();
        int ran = rnd.nextInt(Integer.MAX_VALUE);
        Log.d(TAG, "notificationId : " + ran);
        return ran;
    }

    private RemoteViews setActionForRemoteViews(RemoteViews remoteViews) {
        Intent pauseIntent = new Intent(this, MainActivity.class);
        pauseIntent.setAction(ACTION_PAUSE);
        PendingIntent pausePendingIntent = PendingIntent.getActivity(this, 0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button_play, pausePendingIntent);

        Intent seekRewardIntent = new Intent(this, MainActivity.class);
        seekRewardIntent.setAction(ACTION_SEEK_REWARD);
        PendingIntent seekRewardPendingIntent = PendingIntent.getActivity(this, 0, seekRewardIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button_rewind, seekRewardPendingIntent);

        Intent seekForwardIntent = new Intent(this, MainActivity.class);
        seekForwardIntent.setAction(ACTION_SEEK_FORWARD);
        PendingIntent seekForwardPendingIntent = PendingIntent.getActivity(this, 0, seekForwardIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button_foward, seekForwardPendingIntent);

        return remoteViews;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "[enter] onNewIntent");
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(ACTION_PAUSE)) {
            Log.d(TAG, "pause event");
        } else if (intent.getAction().equals(ACTION_SEEK_REWARD)) {
            Log.d(TAG, "seek reward event");
        } else if (intent.getAction().equals(ACTION_SEEK_FORWARD)) {
            Log.d(TAG, "seek forward event");
        } else if (intent.getAction().equals(ACTION_DELETE_NOTIFICATION)) {
            Log.d(TAG, "remove notification");
            finish();
        } else {
            Log.d(TAG, "unknown action ");
        }
    }
}

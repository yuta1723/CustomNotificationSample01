package com.ynaito.customnotificationsample;

import android.app.Notification;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private Button seekRewindButton;
    private Button playButton;
    private Button seekFastForwardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);

        Notification notification = generateNotification(remoteViews);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this.getApplicationContext());

        manager.notify(generateNotificationId(), notification);

        seekFastForwardButton = (Button) findViewById(R.id.button_foward);
        seekFastForwardButton.setOnClickListener(seekRewindButtonClickListener);
        playButton = (Button) findViewById(R.id.button_play);
        playButton.setOnClickListener(playButtonClickListener);
        seekRewindButton = (Button) findViewById(R.id.button_rewind);
        seekRewindButton.setOnClickListener(seekFastForwardButtonClickListener);
    }

    private Notification generateNotification(RemoteViews remoteViews) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setVisibility(Notification.VISIBILITY_PUBLIC);
        builder.setContent(remoteViews);
//        builder.setCustomBigContentView(remoteViews);
        builder.setStyle(new NotificationCompat.MediaStyle().setMediaSession(null));
        return builder.build();
    }

    private int generateNotificationId() {
        Random rnd = new Random();
        int ran = rnd.nextInt(Integer.MAX_VALUE);
        Log.d(TAG, "notificationId : " + ran);
        return ran;
    }

    private View.OnClickListener seekRewindButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "seekRewindButton is Clicked");
        }
    };

    private View.OnClickListener playButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "seekPlayButton is Clicked");
        }
    };

    private View.OnClickListener seekFastForwardButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "seekFastForwardButton is Clicked");
        }
    };
}

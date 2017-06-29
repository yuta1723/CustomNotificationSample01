package com.ynaito.customnotificationsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);
    }
}

package com.scode.notificationtest;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    private Button btn_normal,btn_zhedie,btn_xuangua;
    private RadioGroup rb_group;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_normal = (Button) findViewById(R.id.not_normal);
        btn_xuangua = (Button) findViewById(R.id.not_xuangua);
        btn_zhedie = (Button) findViewById(R.id.not_zhedie);

        rb_group = (RadioGroup) findViewById(R.id.rb_group);


        final NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://baidu.com"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentTitle("普通通知");
                setNotLevel(builder);
                builder.setAutoCancel(true);
                manager.notify(0, builder.build());
            }
        });
        btn_zhedie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://baidu.com"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentTitle("折叠通知");
                builder.setAutoCancel(true);
                setNotLevel(builder);
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_layout);
                Notification notification = builder.build();
                notification.bigContentView = remoteViews;
                manager.notify(1, notification);
            }
        });
        btn_xuangua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://baidu.com"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setFullScreenIntent(pendingIntent, true);
                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentTitle("悬挂通知");
                builder.setAutoCancel(true);
                setNotLevel(builder);
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_layout);
                Notification notification = builder.build();
                manager.notify(2, notification);
            }
        });
    }
        //可以为notification添加三种等级
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void setNotLevel(Notification.Builder builder){
        switch (rb_group.getCheckedRadioButtonId()) {
            case R.id.rb_private:
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("private");
                break;
            case R.id.rb_public:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
            case R.id.rb_secret:
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("secret");
                break;
        }
    }

    }


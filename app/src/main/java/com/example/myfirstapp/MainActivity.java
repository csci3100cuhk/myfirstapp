package com.example.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    EditText titleText;
    EditText contentText;
    Button button;
    NotificationManager manager;
    NotificationChannel channel;
    String strChannelID = "strChannelID1";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.notificationTitle);
        contentText = findViewById(R.id.notificationContent);
        button = findViewById(R.id.notificationButton);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        channel = new NotificationChannel(strChannelID, "Some Notification Channel", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    int notificationID = (int) (Math.random() * 100);
                    Notification.Builder builder = new Notification.Builder(MainActivity.this, strChannelID);
                    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                    builder.setContentTitle(titleText.getText().toString());
                    builder.setContentText(contentText.getText().toString());
                    manager.notify(notificationID, builder.build());
                }
            }
        });
    }

    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}

package com.example.androidnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //1. Notification Chanel
    //2. Notification Builder
    //3. Notifaction Manager

    public final String CHANNEL_ID = "notification_key";
    public final String CHANNEL_NAME ="motifcation_app";
    public final String CHANNEL_DESC = "notifaction_desc";
    private Button notif;
    private EditText edtnama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        edtnama = findViewById(R.id.editnamaku);
        notif = findViewById(R.id.buttonNotify);
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });
    }

    private void displayNotification(){

        if (TextUtils.isEmpty(edtnama.getText().toString())){
            Toast.makeText(this, "isi pesan anda terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("Notification")
                .setContentText(edtnama.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,builder.build());
    }
}
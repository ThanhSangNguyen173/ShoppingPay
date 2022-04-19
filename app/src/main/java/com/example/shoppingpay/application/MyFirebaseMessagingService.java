package com.example.shoppingpay.application;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.shoppingpay.R;
import com.example.shoppingpay.views.activity.DashboardActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification = message.getNotification();
        if(notification==null){
            return;
        }
        String strTitle = notification.getTitle();
        String strMessage = notification.getBody();

        sendNotification(strTitle,strMessage);

    }

    private void sendNotification(String strTitle, String strMessage) {

        Intent intent = new Intent(MyFirebaseMessagingService.this, DashboardActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,AppInfo.CHANNEL_ID)
                .setContentTitle(strTitle)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setSound(Uri.parse("uri://sadfasdfasdf.mp3"))
                .setContentText(strMessage)
                .setSmallIcon(R.mipmap.logointro)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logointro))
                .setContentIntent(pendingIntent);

        Notification notification = notificationBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!=null){
            notificationManager.notify(1,notification);
        }
    }
}

package com.abheri.sunaad.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.abheri.sunaad.R;
import com.abheri.sunaad.controller.ProgramController;
import com.abheri.sunaad.model.Program;
import com.abheri.sunaad.model.ProgramListDataCache;

/**
 * Created by prasanna.ramaswamy on 20/08/16.
 */


public class AlarmBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        String messageText = intent.getStringExtra("MessageText");
        Program prgObj = (Program)intent.getSerializableExtra("SelectedProgram");

        Bundle args = new Bundle();
        args.putSerializable("SelectedProgram", prgObj);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtras(args);
        PendingIntent pIntent = PendingIntent.getActivity(context,
                (int) System.currentTimeMillis(), notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle("Sunaad Notification")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(messageText))
                .setContentText(messageText)
                .setTicker("New Message Alert!")
                .setSmallIcon(R.drawable.sunaad_notification)
                .setContentIntent(pIntent).build();

        NotificationManager notificationManager = (NotificationManager)
                                        context.getSystemService(Context.NOTIFICATION_SERVICE);

        //notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.sound =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + context.getPackageName() + "/raw/mahadeva_shivashambho");
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        notificationManager.notify((int)prgObj.getId(), notification);

        UpdateAlarmFlag(context, prgObj);


        //Uri ringNotification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //MediaPlayer mp = MediaPlayer.create(context, ringNotification);
        //mp.start();

    }

    void UpdateAlarmFlag(Context context, Program prgObj){

        Context appContext = context.getApplicationContext();
        prgObj.alarm_millis = -1;

        ProgramController pc = new ProgramController(appContext);
        pc.updateProgramList(prgObj);
    }
}
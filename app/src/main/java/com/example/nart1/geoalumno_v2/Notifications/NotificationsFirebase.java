package com.example.nart1.geoalumno_v2.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.example.nart1.geoalumno_v2.Activities.MainActivity;
import com.example.nart1.geoalumno_v2.Activities.MapsActivity;
import com.example.nart1.geoalumno_v2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Created by nart1 on 23/11/2017.
 */

public class NotificationsFirebase extends FirebaseMessagingService {

    static public double NotiLongitud;
    static public double NotiLatitud;
    SharedPreferences sharedPreferences;
    int cont = 0,band=0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        sharedPreferences = getSharedPreferences("Preference",Context.MODE_PRIVATE);

        if(sharedPreferences.contains("KEY_CONT")){

            band = sharedPreferences.getInt("KEY_CONT",0);
        }

        if (remoteMessage.getData().size() > 0) {

            if(band == 0) {
                sendNotification(remoteMessage.getData().get("Nombre"), (remoteMessage.getData().get("Carrera")),
                        (remoteMessage.getData().get("Latitud")), (remoteMessage.getData().get("Longitud")));
                contador();
            }

                NotiLatitud = Double.parseDouble(remoteMessage.getData().get("Latitud"));
                NotiLongitud = Double.parseDouble(remoteMessage.getData().get("Longitud"));

                Intent intent = new Intent(MapsActivity.INTENT_FILTER);
                intent.putExtra(MapsActivity.KEY_LAT, NotiLatitud);
                intent.putExtra(MapsActivity.KEY_LONG, NotiLongitud);

                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

            if (NotiLongitud == 0 && NotiLatitud == 0){
                cont=0;
                contador();
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    void sendNotification(String nombre, String carrera, String lati, String longi) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                    .setContentTitle("Notificacion de Geoprofesor")
                    .setContentText("Nombre: " + nombre + " Carrera: " + carrera)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(getRequestCode(), notificationBuilder.build());
        cont++;

    }

    int getRequestCode() {
        Random random = new Random();
        return 1000 + random.nextInt(900000);
    }

    public void contador() {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("KEY_CONT",cont);
        editor.apply();
    }
}

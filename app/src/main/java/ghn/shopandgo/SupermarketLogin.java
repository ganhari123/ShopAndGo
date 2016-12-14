package ghn.shopandgo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by bukbukbukh on 11/13/16.
 */

public class SupermarketLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_market_login_activity);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("Result") != null) {
                Log.e("RESULT", intent.getStringExtra("Result"));
                NotificationCompat.Builder nBuilder = (android.support.v7.app.NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setContentTitle("Code")
                        .setContentText(intent.getStringExtra("Result"))
                        .setSmallIcon(R.mipmap.ic_launcher);
                String extra = intent.getStringExtra("Result");
                intent = new Intent(this, SupermarketLogin.class);
                intent.putExtra("Result", extra);
                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                this,
                                0,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );

                NotificationCompat.Action action = (android.support.v7.app.NotificationCompat.Action) new NotificationCompat.Action.Builder(R.mipmap.shopping_kart, "Hello", resultPendingIntent).build();

                android.support.v4.app.NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
                bigTextStyle.bigText("This is shop and go");

                nBuilder.setStyle(bigTextStyle);
                nBuilder.setContentIntent(resultPendingIntent);
                nBuilder.extend(new android.support.v4.app.NotificationCompat.WearableExtender().addAction(action));

// Sets an ID for the notification
                int mNotificationId = 001;
// Gets an instance of the NotificationManager service
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, nBuilder.build());
            }
        }



    }
}

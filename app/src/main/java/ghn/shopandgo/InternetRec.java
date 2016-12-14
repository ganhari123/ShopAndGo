package ghn.shopandgo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by bukbukbukh on 11/11/16.
 */

public class InternetRec extends BroadcastReceiver {

    public boolean isConnected;
    @Override
    public void onReceive(Context context, Intent intent) {
        isConnected = true;
        ConnectivityManager connManager =
                (ConnectivityManager)context.
                        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting() == true) {
        } else {
            isConnected = false;
            ((Activity) context).finish();
            Toast.makeText(context, "disconnected", Toast.LENGTH_SHORT).show();
        }
    }

}

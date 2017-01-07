package ghn.shopandgo;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import junit.framework.Test;

public class TestButtons extends FragmentActivity {

    private TextView mTextView;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "TAG";
    private boolean nodeConnected = false;
    private int yo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_buttons);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                Log.e("DATA", "DATA is changed");
                yo = 5;
                mGoogleApiClient = new GoogleApiClient.Builder(TestButtons.this)
                        .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected(Bundle connectionHint) {
                                Log.d(TAG, "onConnected: " + connectionHint);
                                // Now you can use the Data Layer API
                            }
                            @Override
                            public void onConnectionSuspended(int cause) {
                                Log.d(TAG, "onConnectionSuspended: " + cause);
                            }
                        })
                        .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.d(TAG, "onConnectionFailed: " + result);
                            }
                        })
                        // Request access only to the Wearable API
                        .addApi(Wearable.API)
                        .enableAutoManage(TestButtons.this, 0, new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.d(TAG, "onConnectionFailed: " + result);
                            }
                        })
                        .build();
                mGoogleApiClient.connect();


                Button button = (Button) findViewById(R.id.button2);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mGoogleApiClient.isConnected()) {
                            Log.e("yo", String.valueOf(yo));
                            PutDataMapRequest putDataMapRequest = PutDataMapRequest.create("/count");
                            putDataMapRequest.getDataMap().putInt("data", ++yo);
                            putDataMapRequest.getDataMap().putLong("TIME", SystemClock.uptimeMillis() );
                            PutDataRequest request = putDataMapRequest.asPutDataRequest();

                            PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi.putDataItem(mGoogleApiClient, request);

                            pendingResult.setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                                @Override
                                public void onResult(DataApi.DataItemResult dataItemResult) {
                                    Log.e("WEAR APP", "APPLICATION Result has come");
                                }
                            });

                        } else {
                            Log.e("WEAR APP", "No Google API Client connection");
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }
}

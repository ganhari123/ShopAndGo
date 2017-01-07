package ghn.shopandgo;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.Wearable;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class SuperMarketSearch extends AppCompatActivity {


    public static final String TAG = "TEST";
    private InternetRec internetRec;
    private String savedInstanceStateVariable;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_market_search);
        createButtonAndListeners();

    }


    private void createButtonAndListeners() {
        Button submitCode = (Button) findViewById(R.id.submit_code);
        Button qrCodeLauncher = (Button) findViewById(R.id.qr_code_launch);
        submitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialogBox.createBox(SuperMarketSearch.this);
                submitSuperMarketCode();
            }
        });
        qrCodeLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialogBox.createBox(SuperMarketSearch.this);
                checkPermissions();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        internetRec = new InternetRec();
        Log.e("RESUME", "RESUME");
        registerReceiver(internetRec, new IntentFilter(
                        ConnectivityManager.CONNECTIVITY_ACTION));


    }


    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(SuperMarketSearch.this, QrCodeScanner.class);
            ProgressDialogBox.hideBox(SuperMarketSearch.this);
            startActivity(intent);


        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA},
                    0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(SuperMarketSearch.this, QrCodeScanner.class);
                ProgressDialogBox.hideBox(SuperMarketSearch.this);
                startActivity(intent);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast toast = Toast.makeText(this, "Please Accept the Camera permissions", Toast.LENGTH_SHORT);
                    ProgressDialogBox.hideBox(SuperMarketSearch.this);
                    toast.show();

                } else {
                    Toast toast = Toast.makeText(this, "Go to settings to manually accept the permissions", Toast.LENGTH_SHORT);
                    ProgressDialogBox.hideBox(SuperMarketSearch.this);
                    toast.show();
                }
            }
        }
    }

    private void makeHttpRequest() {
        ProgressDialogBox.createBox(this);
        ShopAndGoHttpRequest.get("code", null, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                // Initiated the request
            }

            @Override
            public void onSuccess(int responseCode, Header[] headers, String responseBody) {
                // Successfully got a response
                Log.e("Response", responseBody);
            }

            @Override
            public void onFailure(int responsecode, Header[] headers, String responseBody, Throwable e) {
                // Response failed :(
            }

            @Override
            public void onFinish() {
                // Completed the request (either success or failure)
                ProgressDialogBox.hideBox(SuperMarketSearch.this);

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(internetRec);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void submitSuperMarketCode() {
        EditText code = (EditText) findViewById(R.id.super_market_code);
        Intent intent = new Intent(this, SupermarketItemScan.class);
        if (!code.getText().toString().equals("")) {
            intent.putExtra("Result", code.getText().toString());
            ProgressDialogBox.hideBox(SuperMarketSearch.this);
            startActivity(intent);
        } else {
            //Toast the error
            Toast.makeText(this, "No code typed in", Toast.LENGTH_LONG).show();
            ProgressDialogBox.hideBox(SuperMarketSearch.this);
        }

    }

}

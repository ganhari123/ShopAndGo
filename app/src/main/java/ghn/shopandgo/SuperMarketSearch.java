package ghn.shopandgo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SuperMarketSearch extends AppCompatActivity {


    public static final String TAG = "TEST";
    private InternetRec rec;
    private String savedInstanceStateVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_market_search);

        if (savedInstanceState != null) {
            Log.e("GOING IN", savedInstanceState.getString("SupermarketCode"));
            savedInstanceStateVariable = savedInstanceState.getString("SupermarketCode");
            EditText code = (EditText) findViewById(R.id.super_market_code);
            code.setText(savedInstanceStateVariable);

        } else {

        }

        Button submitCode = (Button) findViewById(R.id.submit_code);
        submitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitSuperMarketCode();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        rec = new InternetRec();
        Button btn = (Button) findViewById(R.id.qr_code_launch);

        registerReceiver(
                rec,
                new IntentFilter(
                        ConnectivityManager.CONNECTIVITY_ACTION));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperMarketSearch.this, QrCodeScanner.class);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(rec);
        //finish();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        EditText code = (EditText) findViewById(R.id.super_market_code);
        Log.e(TAG, code.getText().toString() + "In on saved");
        savedInstanceState.putString("SupermarketCode", code.getText().toString());
        Log.w("Hello", "Saving");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("Hello", "Destroy");
    }

    private void submitSuperMarketCode() {
        EditText code = (EditText) findViewById(R.id.super_market_code);
        Intent intent = new Intent(this, SupermarketLogin.class);
        Log.e("Code", code.getText().toString());
        if (!code.getText().toString().equals("")) {
            intent.putExtra("Result", code.getText().toString());
            startActivity(intent);
        } else {
            //Toast the error
            Toast.makeText(this, "No code typed in", Toast.LENGTH_LONG).show();
        }

    }

    // Method to manually check connection status

}

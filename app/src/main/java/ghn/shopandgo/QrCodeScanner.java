package ghn.shopandgo;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by bukbukbukh on 11/29/16.
 */

public class QrCodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_activity);
        Button qrCodeReader = (Button) findViewById(R.id.qr_code_start);
        qrCodeReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQrCodeScanner();
            }
        });
        startQrCodeScanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    // Method to manually check connection status
    private void startQrCodeScanner() {
        mScannerView = new ZXingScannerView(this);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {

            setContentView(mScannerView);
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
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
                setContentView(mScannerView);
                mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast toast = Toast.makeText(this, "Please Accept the Camera permissions", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(this, "Go to settings to manually accept the permissions", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }
    }

    @Override
    public void handleResult(Result result) {

        Intent intent = new Intent(this, SupermarketLogin.class);
        intent.putExtra("Result", result.getText());
        startActivity(intent);
    }
}

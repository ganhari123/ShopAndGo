package ghn.shopandgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        mScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.startCamera();
    }

    // Method to manually check connection status
    private void startQrCodeScanner() {
        mScannerView = new ZXingScannerView(this);


            setContentView(mScannerView);
            mScannerView.setResultHandler(this);

    }



    @Override
    public void handleResult(Result result) {

        Intent intent = new Intent(this, SupermarketItemScan.class);
        intent.putExtra("Result", result.getText());
        Log.e("Result", result.getText());
        startActivity(intent);
    }
}

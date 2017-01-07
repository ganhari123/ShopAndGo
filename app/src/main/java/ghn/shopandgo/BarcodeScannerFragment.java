package ghn.shopandgo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by bukbukbukh on 12/21/16.
 */

public class BarcodeScannerFragment extends Fragment implements ZXingScannerView.ResultHandler {

    // Store instance variables
    private String title;
    private int page;
    private boolean isBarcode = false;
    private ZXingScannerView mScannerView;

    // newInstance constructor for creating fragment with arguments
    public static Fragment newInstance() {
        BarcodeScannerFragment fragmentFirst = new BarcodeScannerFragment();
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());
        return mScannerView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ONPAUSE", "ONPAUSE");
        mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ONRESUME", "ONRESUME");
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("ONPAUSE", "ONSTART");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("ONPAUSE", "ONSTOP");
    }

    @Override
    public void handleResult(Result rawResult) {

    }
}

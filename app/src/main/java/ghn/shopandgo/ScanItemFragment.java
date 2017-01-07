package ghn.shopandgo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by bukbukbukh on 12/18/16.
 */

public class ScanItemFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private boolean isBarcode = false;
    private ZXingScannerView mScannerView;

    // newInstance constructor for creating fragment with arguments
    public static Fragment newInstance(int page, String title) {
        ScanItemFragment fragmentFirst = new ScanItemFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ONPAUSE", "ONPAUSE");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ONRESUME", "ONRESUME");
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


}

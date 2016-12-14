package ghn.shopandgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by bukbukbukh on 11/10/16.
 */

public class SplashScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.splash_screen);


    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException inter) {
                    finish();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, SuperMarketSearch.class);
                    startActivity(intent);
                }

            }
        }).start();
    }

}

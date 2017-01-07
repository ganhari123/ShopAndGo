package ghn.shopandgo;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;

/**
 * Created by bukbukbukh on 12/16/16.
 */

public class ProgressDialogBox extends Dialog {

    Context activity;
    private static ProgressDialogBox box;
    private static boolean isShow = false;

    public ProgressDialogBox(Context a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("GOING IN", "GOING IN");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.progress_bar_dialog);
        ProgressBar bar = (ProgressBar) findViewById(R.id.thinking_dialog);
        bar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#00A0A6"), android.graphics.PorterDuff.Mode.SRC_ATOP);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);

    }

    public static void createBox(Context context) {
        if (!isShow) {
            isShow = true;
            box = new ProgressDialogBox(context);

            box.show();
        } else {
            Log.e("HIDE", "ISSHown already");
            box.dismiss();
        }

    }

    public static void hideBox(Context context) {
        if (isShow) {
            isShow = false;
            box.dismiss();
        }
    }
}

package com.bdrk.myandroidtip.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 5u51_5 on 2017/3/27.
 */

public class ToastUtil {
    private static Toast mToast;

    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}

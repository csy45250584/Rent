package com.haokuo.housemanage.houserentmanage.Utils;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
    public final static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


}

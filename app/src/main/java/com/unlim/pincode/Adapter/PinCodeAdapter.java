package com.unlim.pincode.Adapter;

import android.content.Context;
import android.widget.LinearLayout;

import com.unlim.pincode.MyCircle;

public class PinCodeAdapter {
    private Context context;
    private int amn;
    private final static int MARGIN_16 = 16;

    public PinCodeAdapter(Context context, int amn) {
        this.context = context;
        this.amn = amn;
    }

    public void fillCodeLine(LinearLayout linearLayout) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(MARGIN_16, MARGIN_16, MARGIN_16, MARGIN_16);
        for (int i = 0; i < amn; i++) {
            MyCircle myCircle = new MyCircle(context);
            myCircle.setId(i);
            linearLayout.addView(myCircle, params);
        }
    }

    public void clearCodeLine(LinearLayout linearLayout) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ((MyCircle) linearLayout.getChildAt(i)).setOn(false);
        }
    }

    public void switchOff(LinearLayout linearLayout, int id) {
        ((MyCircle) linearLayout.getChildAt(id)).setOn(false);
    }

    public void switchOn(LinearLayout linearLayout, int id) {
        ((MyCircle) linearLayout.getChildAt(id)).setOn(true);
    }
}

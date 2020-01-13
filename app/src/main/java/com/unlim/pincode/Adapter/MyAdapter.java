package com.unlim.pincode.Adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class MyAdapter {

    private PinCodeAdapter pinCodeAdapter;
    private KeyboardAdapter keyboardAdapter;

    public MyAdapter(Context context, int amn) {
        pinCodeAdapter = new PinCodeAdapter(context, amn);
        keyboardAdapter = new KeyboardAdapter(context);
    }

    public void switchOffCircle(LinearLayout linearLayout, int id) {
        pinCodeAdapter.switchOff(linearLayout, id);
    }

    public void switchOnCircle(LinearLayout linearLayout, int id) {
        pinCodeAdapter.switchOn(linearLayout, id);
    }

    public void clearCodeLine(LinearLayout linearLayout) {
        pinCodeAdapter.clearCodeLine(linearLayout);
    }

    public void fillKeyboard(TableLayout tableLayout) {
        keyboardAdapter.fillKeyboard(tableLayout);
    }

    public void fillCodeLine(LinearLayout linearLayout) {
        pinCodeAdapter.fillCodeLine(linearLayout);
    }
}

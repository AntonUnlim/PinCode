package com.unlim.pincode;

import android.content.Intent;

public class MyPresenter {
    public final static int SYMBOLS_AMN = 4;
    private final static String PASS_CODE = "9510";
    private String enteredCode = "";
    private int currentSymbol = 0;
    private MainActivity view;

    public MyPresenter(MainActivity view) {
        this.view = view;
    }

    public void getClickedNumber(int number) {
        if (currentSymbol < SYMBOLS_AMN) {
            enteredCode += String.valueOf(number);
            view.switchOnCodeSymbol(currentSymbol);
            currentSymbol++;
        }
        if (currentSymbol == SYMBOLS_AMN) {
            if (enteredCode.equals(PASS_CODE)) {
                clearCodeSymbol();
                Intent intent = new Intent(view, NewActivity.class);
                view.startActivity(intent);
            } else {
                view.errAnimation();
            }
        }
    }

    public void clearCode() {
        enteredCode = "";
        currentSymbol = 0;
    }

    public void onDeleteCodeSymbol() {
        if (currentSymbol > 0) {
            view.switchOffCodeSymbol(currentSymbol);
            int len = enteredCode.length() - 1;
            enteredCode = enteredCode.substring(0, len);
            currentSymbol = currentSymbol - 1;
        }
    }

    private void clearCodeSymbol() {

    }
}

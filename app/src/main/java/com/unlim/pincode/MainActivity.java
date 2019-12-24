package com.unlim.pincode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    private final static int AMN = 4;
    private final static String PASS_CODE = "9510";
    private LinearLayout linearLayout;
    private String enteredCode = "";
    private int currentSymbol = 0;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAdapter = new MyAdapter(this, AMN);
        fillCodeCircles();
        fillKeyboard();
    }

    public void btnClickDelete(View view) {
        clearCodeSymbol();
    }

    private void clearCodeSymbol() {
        if(currentSymbol > 0) {
            myAdapter.switchOff(linearLayout, currentSymbol - 1);
            int len = enteredCode.length() - 1;
            enteredCode = enteredCode.substring(0, len);
            currentSymbol = currentSymbol - 1;
        }
    }

    private void clearCode() {
        myAdapter.clearCodeLine(linearLayout);
        enteredCode = "";
        currentSymbol = 0;
    }

    private void fillKeyboard() {
        TableLayout tableLayout = findViewById(R.id.table);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);
        myAdapter.fillKeyboard(tableLayout);
    }

    public void getClickedNumber(int number) {
        if (currentSymbol < AMN) {
            enteredCode += String.valueOf(number);
            ((MyCircle) linearLayout.getChildAt(currentSymbol)).setOn(true);
            currentSymbol++;
        }
        if (currentSymbol == AMN) {
            if (enteredCode.equals(PASS_CODE)) {
                clearCodeSymbol();
                Intent intent = new Intent(this, NewActivity.class);
                startActivity(intent);
            } else {
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);
                linearLayout.startAnimation(animation);
                clearCode();
            }
        }
    }

    private void fillCodeCircles() {
        linearLayout = findViewById(R.id.linear_layout);
        myAdapter.fillCodeLine(linearLayout);
    }

}

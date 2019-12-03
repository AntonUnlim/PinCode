package com.unlim.pincode;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int AMN = 4;
    private final String passCode = "9510";
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
        clearCode();
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
        tableLayout.setBackgroundColor(Color.LTGRAY);
        myAdapter.fillKeyboard(tableLayout);
    }

    public void getClickedNumber(int number) {
        if (currentSymbol < AMN) {
            enteredCode += String.valueOf(number);
            ((MyCircle) linearLayout.getChildAt(currentSymbol)).setOn(true);
            currentSymbol++;
        }
        if (currentSymbol == AMN) {
            if (enteredCode.equals(passCode)) {
                clearCode();
                Intent intent = new Intent(this, NewActivity.class);
                startActivity(intent);
            } else {
                //Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                myAdapter.animateErrorCode(linearLayout);
                clearCode();
            }
        }
    }

    private void fillCodeCircles() {
        linearLayout = findViewById(R.id.linear_layout);
        myAdapter.fillCodeLine(linearLayout);
    }

}

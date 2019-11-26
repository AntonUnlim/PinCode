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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillCodeCircles();
        fillKeyboard();
    }

    public void btnClickDelete(View view) {
        clearCode();
    }

    private void clearCode() {
        for(int i = 0; i < linearLayout.getChildCount(); i++) {
            ((MyCircle) linearLayout.getChildAt(i)).setOn(false);
            enteredCode = "";
            currentSymbol = 0;
        }
    }

    @SuppressLint("ResourceType")
    private void fillKeyboard() {
        TableLayout keyboard = findViewById(R.id.table);
        keyboard.setStretchAllColumns(true);
        keyboard.setShrinkAllColumns(true);
        keyboard.setBackgroundColor(Color.LTGRAY);
        TableRow row1 = new TableRow(this);
        row1.setGravity(Gravity.CENTER_HORIZONTAL);
        TableRow row2 = new TableRow(this);
        row2.setGravity(Gravity.CENTER_HORIZONTAL);
        TableRow row3 = new TableRow(this);
        row3.setGravity(Gravity.CENTER_HORIZONTAL);
        TableRow row4 = new TableRow(this);
        row4.setGravity(Gravity.CENTER_HORIZONTAL);

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((CircleButton)v).pressed(true);
                        getClickedNumber(v.getId());
                        break;
                    case MotionEvent.ACTION_UP:
                        ((CircleButton)v).pressed(false);
                        break;
                }
                return true;
            }
        };

        // Row 1
        TableRow.LayoutParams params1 = new TableRow.LayoutParams();
        params1.setMargins(32,32,32,32);
        // Btn 1
        CircleButton btn1 = new CircleButton(this);
        btn1.setId(1);
        btn1.setNumber("1");
        btn1.setOnTouchListener(touchListener);
        row1.addView(btn1, params1);

        // Btn 2
        CircleButton btn2 = new CircleButton(this);
        btn2.setId(2);
        btn2.setNumber("2");
        btn2.setLetters("ABC");
        btn2.setOnTouchListener(touchListener);
        row1.addView(btn2, params1);

        // Btn 3
        CircleButton btn3 = new CircleButton(this);
        btn3.setId(3);
        btn3.setNumber("3");
        btn3.setLetters("DEF");
        btn3.setOnTouchListener(touchListener);
        row1.addView(btn3, params1);

        // Row 2
        TableRow.LayoutParams params2 = new TableRow.LayoutParams();
        params2.setMargins(32,32,32,32);
        // Btn 4
        CircleButton btn4 = new CircleButton(this);
        btn4.setId(4);
        btn4.setNumber("4");
        btn4.setLetters("GHI");
        btn4.setOnTouchListener(touchListener);
        row2.addView(btn4, params2);

        // Btn 5
        CircleButton btn5 = new CircleButton(this);
        btn5.setId(5);
        btn5.setNumber("5");
        btn5.setLetters("JKL");
        btn5.setOnTouchListener(touchListener);
        row2.addView(btn5, params2);

        // Btn 6
        CircleButton btn6 = new CircleButton(this);
        btn6.setId(6);
        btn6.setNumber("6");
        btn6.setLetters("MNO");
        btn6.setOnTouchListener(touchListener);
        row2.addView(btn6, params2);

        // Row 3
        TableRow.LayoutParams params3 = new TableRow.LayoutParams();
        params3.setMargins(32,32,32,32);
        // Btn 7
        CircleButton btn7 = new CircleButton(this);
        btn7.setId(7);
        btn7.setNumber("7");
        btn7.setLetters("PQRS");
        btn7.setOnTouchListener(touchListener);
        row3.addView(btn7, params3);

        // Btn 8
        CircleButton btn8 = new CircleButton(this);
        btn8.setId(8);
        btn8.setNumber("8");
        btn8.setLetters("TUV");
        btn8.setOnTouchListener(touchListener);
        row3.addView(btn8, params3);

        // Btn 9
        CircleButton btn9 = new CircleButton(this);
        btn9.setId(9);
        btn9.setNumber("9");
        btn9.setLetters("WXYZ");
        btn9.setOnTouchListener(touchListener);
        row3.addView(btn9, params3);

        // Row 4
        TableRow.LayoutParams params4 = new TableRow.LayoutParams();
        params4.setMargins(32,32,32,32);
        params4.span = 3;
        // Btn 0
        CircleButton btn0 = new CircleButton(this);
        btn0.setId(0);
        btn0.setNumber("0");
        btn0.setOnTouchListener(touchListener);
        row4.addView(btn0, params4);

        keyboard.addView(row1);
        keyboard.addView(row2);
        keyboard.addView(row3);
        keyboard.addView(row4);
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
                animateErr();
                clearCode();
            }
        }
    }

    private void fillCodeCircles() {
        linearLayout = findViewById(R.id.linear_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(16,16,16,16);
        for (int i = 0; i < AMN; i++) {
            MyCircle myCircle = new MyCircle(this);
            myCircle.setId(i);
            linearLayout.addView(myCircle, params);
        }
    }

    private void animateErr() {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ValueAnimator animatorStart = ValueAnimator.ofInt(16, 50);
        animatorStart.setDuration(100);
        animatorStart.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int p = (int) animation.getAnimatedValue();
                params.setMargins(p, p, p, p);
                for (int i = 0; i < AMN; i++) {
                    linearLayout.getChildAt(i).setLayoutParams(params);
                }
            }
        });
        ValueAnimator animatorEnd = ValueAnimator.ofInt(50, 16);
        animatorEnd.setDuration(100);
        animatorEnd.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int p = (int) animation.getAnimatedValue();
                params.setMargins(p, p, p, p);
                for (int i = 0; i < AMN; i++) {
                    linearLayout.getChildAt(i).setLayoutParams(params);
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(animatorStart).before(animatorEnd);
        animatorSet.play(animatorEnd).after(animatorStart);

        animatorSet.start();
    }
}

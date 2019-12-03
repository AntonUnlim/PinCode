package com.unlim.pincode;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MyAdapter {
    private Context context;
    private int amn;

    public MyAdapter(Context context, int amn) {
        this.context = context;
        this.amn = amn;
    }

    public void fillCodeLine(LinearLayout linearLayout) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(16,16,16,16);
        for (int i = 0; i < amn; i++) {
            MyCircle myCircle = new MyCircle(context);
            myCircle.setId(i);
            linearLayout.addView(myCircle, params);
        }
    }
    public void clearCodeLine(LinearLayout linearLayout) {
        for(int i = 0; i < linearLayout.getChildCount(); i++) {
            ((MyCircle) linearLayout.getChildAt(i)).setOn(false);
        }
    }

    @SuppressLint("ResourceType")
    public void fillKeyboard(TableLayout tableLayout) {
        TableRow row4 = new TableRow(context);
        row4.setGravity(Gravity.CENTER_HORIZONTAL);

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ((CircleButton)v).pressed(true);
                        ((MainActivity)context).getClickedNumber(v.getId());
                        break;
                    case MotionEvent.ACTION_UP:
                        ((CircleButton)v).pressed(false);
                        break;
                }
                return true;
            }
        };
        String[] letters = new String[] {"","ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ",""};
        int num = 1;
        for (int row = 1; row <= 4; row++) {
            TableRow tbRow = new TableRow(context);
            tbRow.setGravity(Gravity.CENTER_HORIZONTAL);
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.setMargins(32,32,32,32);
            if (row == 4) {
                params.span = 3;
            }
            for (int col = 1; col <= 3; col++) {
                CircleButton btn = new CircleButton(context);
                if(num == 10 && row == 4) {
                    num = 0;
                }
                btn.setId(num);
                btn.setNumber(String.valueOf(num));
                btn.setLetters(letters[(num==0) ? 0 : num-1]);
                btn.setOnTouchListener(touchListener);
                tbRow.addView(btn, params);
                if (num == 0) break;
                num++;
            }
            tableLayout.addView(tbRow);
        }
    }

    public void animateErrorCode(final LinearLayout linearLayout) {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ValueAnimator animatorStart = ValueAnimator.ofInt(16, 50);
        animatorStart.setDuration(100);
        animatorStart.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int p = (int) animation.getAnimatedValue();
                params.setMargins(p, p, p, p);
                for (int i = 0; i < amn; i++) {
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
                for (int i = 0; i < amn; i++) {
                    linearLayout.getChildAt(i).setLayoutParams(params);
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorStart).before(animatorEnd);
        animatorSet.start();
    }
}

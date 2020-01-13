package com.unlim.pincode.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.unlim.pincode.CircleButton;
import com.unlim.pincode.MainActivity;

public class KeyboardAdapter {

    private Context context;
    private final static String[] LETTERS = new String[] {"", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ", ""};
    private final static int MARGIN_32 = 32;

    public KeyboardAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("ResourceType")
    public void fillKeyboard(final TableLayout tableLayout) {
        TableRow row4 = new TableRow(context);
        row4.setGravity(Gravity.CENTER_HORIZONTAL);

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        ((CircleButton) v).startAnimationGrow();
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ((CircleButton) v).startAnimationShrink();
                        if (x > 0 && y > 0 && x < v.getWidth() && y < v.getHeight()) {
                            ((MainActivity) context).getClickedNumber(v.getId());
                        }
                        break;
                }
                return true;
            }
        };

        int num = 1;
        for (int row = 1; row <= 4; row++) {
            TableRow tbRow = new TableRow(context);
            tbRow.setGravity(Gravity.CENTER_HORIZONTAL);
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.setMargins(MARGIN_32, MARGIN_32, MARGIN_32, MARGIN_32);
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
                btn.setLetters(LETTERS[(num == 0) ? 0 : num - 1]);
                btn.setOnTouchListener(touchListener);

                tbRow.addView(btn, params);
                if (num == 0) {
                    break;
                }
                num++;
            }
            tableLayout.addView(tbRow);
        }
    }
}

package com.unlim.pincode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class CircleButton extends ImageView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int size = 200;
    private String number = "";
    private String letters = "";
    private int numberFontSize = 110;
    private int lettersFontSize = 40;
    private float radius = 100;
    private float borderWidth = 3;
    private int fillColor = Color.TRANSPARENT;

    public CircleButton(Context context) {
        super(context);
    }

    public CircleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(size, size);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // draw circle
        paint.setColor(fillColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(radius, radius, radius, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(radius, radius, radius - borderWidth / 2f, paint);

        // draw text
        paint.setColor(Color.BLACK);
        paint.setTextSize(numberFontSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(number, 100,100, paint);

        paint.setTextSize(lettersFontSize);
        canvas.drawText(letters, 100, 150, paint);
    }

    public void pressed(boolean press) {
        if (press) {
            drawFilledCircle();
        } else {
            drawEmptyCircle();
        }
    }

    private void drawEmptyCircle() {
        fillColor = Color.TRANSPARENT;
        invalidate();
    }

    private void drawFilledCircle() {
        fillColor = Color.BLUE;
        invalidate();
    }

    public void setNumber(String number) {
        this.number = number;
        invalidate();
    }

    public void setLetters(String letters) {
        this.letters = letters.toUpperCase();
        invalidate();
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}

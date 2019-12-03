package com.unlim.pincode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class CircleButton extends TextView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final int SIZE = 100;
    private String number = "";
    private String letters = "";
    private final float RADIUS = 50;
    private final float BORDER_WIDTH = 3;
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
        setMeasuredDimension(SIZE, SIZE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        setPaintFill();
        canvas.drawCircle(RADIUS, RADIUS, RADIUS, paint);
        setPaintStroke();
        canvas.drawCircle(RADIUS, RADIUS, RADIUS - BORDER_WIDTH / 2f, paint);
        setPaintNumberText();
        canvas.drawText(number, 50,55, paint);
        setPaintLettersText();
        canvas.drawText(letters, 50, 80, paint);
    }

    public void pressed(boolean press) {
        if (press) {
            drawFilledCircle();
        } else {
            drawEmptyCircle();
        }
    }

    private void setPaintFill() {
        paint.setColor(fillColor);
        paint.setShader(new RadialGradient(getWidth()/2, getHeight()/2, RADIUS, Color.BLACK, Color.TRANSPARENT, Shader.TileMode.MIRROR));
    }

    private void setPaintStroke() {
        paint.setShader(null);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(BORDER_WIDTH);

    }

    private void setPaintNumberText() {
        paint.setColor(Color.BLACK);
        int numberFontSize = 60;
        paint.setTextSize(numberFontSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
    }

    private void setPaintLettersText() {
        int lettersFontSize = 20;
        paint.setTextSize(lettersFontSize);
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
        this.letters = letters;
        invalidate();
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}

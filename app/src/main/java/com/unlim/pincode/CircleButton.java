package com.unlim.pincode;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class CircleButton extends TextView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final static int SIZE = 100;
    private String number = "";
    private String letters = "";
    private MyDrawable myDrawable = new MyDrawable();
    private final static int NUMBER_TEXT_X = 50, NUMBER_TEXT_Y = 55, LETTERS_TEXT_X = 50, LETTERS_TEXT_Y = 80;
    private ObjectAnimator animatorGrow, animatorShrink;

    public CircleButton(Context context) {
        super(context);
        init();
    }
    public CircleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CircleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        animatorGrow = ObjectAnimator.ofFloat(this, "radius", 0, SIZE / 2);
        animatorGrow.setDuration(500);
        animatorShrink = ObjectAnimator.ofFloat(this, "radius", SIZE / 2, 0);
        animatorShrink.setDuration(500);
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(SIZE, SIZE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        myDrawable.draw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(SIZE/2, SIZE/2, SIZE/2, paint);
        setPaintNumberText();
        canvas.drawText(number, NUMBER_TEXT_X,NUMBER_TEXT_Y, paint);
        setPaintLettersText();
        canvas.drawText(letters, LETTERS_TEXT_X, LETTERS_TEXT_Y, paint);
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        myDrawable.setBounds(0, 0, w, h);
    }

    public void setRadius(float radius) {
        myDrawable.setRadius(radius);
        invalidate();
    }

    public void startAnimationGrow() {
        animatorGrow.start();
    }

    public void startAnimationShrink() {
        animatorShrink.start();
    }
}

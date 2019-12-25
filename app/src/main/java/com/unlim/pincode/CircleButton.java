package com.unlim.pincode;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class CircleButton extends TextView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final static int SIZE = 100;
    private String number = "";
    private String letters = "";
    private MyDrawable myDrawable = new MyDrawable();
    private final static int NUMBER_TEXT_X = 50;
    private final static int NUMBER_TEXT_Y = 55;
    private final static int LETTERS_TEXT_X = 50;
    private final static int LETTERS_TEXT_Y = 80;
    private ObjectAnimator animatorGrow;
    private ObjectAnimator animatorShrink;
    private final static String RADIUS_PROPERTY = "radius";
    private final static int ANIMATION_DURATION = 500;
    private final static int STROKE_WIDTH = 1;
    private final static int NUMBER_FONT_SIZE = 60;
    private final static int LETTERS_FONT_SIZE = 20;

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
        animatorGrow = ObjectAnimator.ofFloat(this, RADIUS_PROPERTY, 0, SIZE / 2);
        animatorGrow.setDuration(ANIMATION_DURATION);
        animatorShrink = ObjectAnimator.ofFloat(this, RADIUS_PROPERTY, SIZE / 2, 0);
        animatorShrink.setDuration(ANIMATION_DURATION);
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
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(SIZE / 2, SIZE / 2, SIZE / 2, paint);
        setPaintNumberText();
        canvas.drawText(number, NUMBER_TEXT_X, NUMBER_TEXT_Y, paint);
        setPaintLettersText();
        canvas.drawText(letters, LETTERS_TEXT_X, LETTERS_TEXT_Y, paint);
    }

    private void setPaintNumberText() {
        paint.setColor(Color.BLACK);
        paint.setTextSize(NUMBER_FONT_SIZE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
    }

    private void setPaintLettersText() {
        paint.setTextSize(LETTERS_FONT_SIZE);
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

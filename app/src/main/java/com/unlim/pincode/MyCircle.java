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
public class MyCircle extends ImageView {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final static int SIZE = 26;
    private final static float RADIUS = 13;
    private final static int POSITION = 13;
    private boolean isOn = false;
    private final static int STROKE_WIDTH = 2;

    public MyCircle(Context context) {
        super(context);
    }
    public MyCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MyCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawCircle(POSITION, POSITION, RADIUS, paint);
        setPaintStroke();
        canvas.drawCircle(POSITION, POSITION, RADIUS - 2 / 2f, paint);
    }

    private void setPaintFill() {
        paint.setColor((isOn) ? Color.BLACK : Color.TRANSPARENT);
        paint.setStyle(Paint.Style.FILL);
    }

    private void setPaintStroke() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
        invalidate();
    }
}

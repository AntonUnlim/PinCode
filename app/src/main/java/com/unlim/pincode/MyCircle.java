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
    private final int SIZE = 26;
    private final float RADIUS = 13;
    private boolean isOn = false;

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
        canvas.drawCircle(13, 13, RADIUS, paint);
        setPaintStroke();
        canvas.drawCircle(13, 13, RADIUS - 2 / 2f, paint);
    }

    private void setPaintFill() {
        paint.setColor((isOn)?Color.BLUE:Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    private void setPaintStroke() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
    }

    public void setOn (boolean isOn) {
        this.isOn = isOn;
        invalidate();
    }

}

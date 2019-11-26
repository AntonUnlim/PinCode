package com.unlim.pincode;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
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
    private int size = 100;
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
        setMeasuredDimension(size, size);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor((isOn)?Color.BLUE:Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        float radius = 50;
        canvas.drawCircle(50, 50, radius, paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawCircle(50, 50, radius - 3 / 2f, paint);
    }

    public void setOn (boolean isOn) {
        this.isOn = isOn;
        invalidate();
    }

}

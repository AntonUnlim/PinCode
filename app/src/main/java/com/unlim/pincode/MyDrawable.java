package com.unlim.pincode;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;

public class MyDrawable extends GradientDrawable {

    private Paint paint = new Paint();
    private int width;
    private int height;
    private float radius;

    @Override
    protected void onBoundsChange(Rect bounds) {
        width = bounds.width();
        height = bounds.height();
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.LTGRAY);
        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
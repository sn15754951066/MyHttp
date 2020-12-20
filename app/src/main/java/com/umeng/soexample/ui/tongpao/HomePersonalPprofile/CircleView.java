package com.umeng.soexample.ui.tongpao.HomePersonalPprofile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {

    private Paint paint;//渐变色
    private int x,y;
    private int r=50;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x=r;
        y=r;
        canvas.drawCircle(x,y,r,paint);
        paint.setColor(Color.BLUE);
//        paint.setTextSize(50);
//        paint.setTextSkewX(-1);
//        paint.setTextScaleX(1);
//        paint.setElegantTextHeight(true);
//        canvas.rotate(10f);
//        canvas.drawText("hollo",50,50,paint);

        //final Bitmap bmm = ImageLoader.getIconBitmap(getContext(), R.drawable.umbrella_0009);
        //Bitmap drawable = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        //canvas.drawBitmap(bmm,0,0,paint);


    }
}

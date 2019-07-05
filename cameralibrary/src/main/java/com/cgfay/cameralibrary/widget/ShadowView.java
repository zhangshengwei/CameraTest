package com.cgfay.cameralibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;

/**
 * @Description:遮罩区域
 * @Author: xianggu
 * @CreateDate: 2019/7/5 11:57 AM
 */
public class ShadowView extends View {


    private Paint mPaint;
    private Rect mRect = new Rect();
    private PorterDuffXfermode mXFerMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    public ShadowView(Context context,int width,int height) {
        super(context);
//        this.width = width;
//        this.height = height;

        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x = getMeasuredWidth() * 1.0f / 2;
        float y = getMeasuredHeight() * 1.0f / 2;
        float radius = Math.min(x, y);

        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        //离屏绘制
        int layerId = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(),
                null, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        //绘制遮罩
        mRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mPaint.setColor(Color.parseColor("#FFFFFFFF"));
        canvas.drawRect(mRect, mPaint);

        //绘制圆
        mPaint.setXfermode(mXFerMode);
        mPaint.setColor(Color.parseColor("#00000000"));
        canvas.drawCircle(x, y, radius, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);

//        Paint paint = new Paint();      //创建一个画笔
//        paint.setColor(Color.parseColor("#00BFFF"));     // 设置颜色
//        paint.setAntiAlias(true);       // 设置画笔的锯齿效果。 true是去除
//
//        Rect rect = new Rect();
//        rect.set(0,0,width,height);
//
//        canvas.drawRect(rect,paint);
//        canvas.drawCircle(0, 180, 180, p);
    }
}

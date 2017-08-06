package com.skopincev.testtaskdb2.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.skopincev.testtaskdb2.R;

/**
 * Created by skopi on 06.08.2017.
 */

public class NumberTag extends View {
    private int number = 0;

    public NumberTag(Context context) {
        super(context);
    }

    public NumberTag(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberTag(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NumberTag(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setNumber(int number){
        this.number = number;
        invalidate();
    }

    private void drawTextCenter(Canvas canvas, Paint paint, String text) {
        Rect r = new Rect();

        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.light_blue));
        float radius = canvas.getWidth() / 2;
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, radius, paint);
        String text = String.valueOf(number);
        paint.setColor(getResources().getColor(R.color.white));
        paint.setTextSize(getResources().getDimension(R.dimen.text_very_small));
        paint.setFakeBoldText(true);
        drawTextCenter(canvas, paint, text);
        super.onDraw(canvas);
    }
}

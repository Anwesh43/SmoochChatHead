package com.anwesome.uiview.smoochlikechathead;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.Button;

import io.smooch.ui.ConversationActivity;

/**
 * Created by anweshmishra on 09/12/16.
 */
public class SmoochLikeChatHead extends Button {
    private int w,h;
    private SmoochChatHeadDrawable smoochChatHeadDrawable;
    private Bitmap bitmap;
    private boolean shouldUpdate;
    public SmoochLikeChatHead(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.smooch_bot);
        w = bitmap.getWidth();
        h = bitmap.getHeight();
        setWidth(2*w);
        setHeight(h);
        smoochChatHeadDrawable = new SmoochChatHeadDrawable();
        setBackground(smoochChatHeadDrawable);
    }
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(shouldUpdate) {
            if(smoochChatHeadDrawable.isStopMoving()) {
                shouldUpdate = false;
                ConversationActivity.show(getContext());
            }
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            shouldUpdate = true;
            smoochChatHeadDrawable.startMoving();
            postInvalidate();
        }
        return true;
    }
    private class SmoochChatHeadDrawable extends Drawable {
        private int l = 0;
        private int k = 0;
        private int dir[] = {1,-1};
        private boolean stopMoving = false;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        public void draw(Canvas canvas) {
            Path path = new Path();
            canvas.save();
            canvas.translate(w,h/2);
            for(int i=0;i<2;i++) {
                canvas.save();
                path.addCircle(dir[i]* l * w / 30, 0, w / 3, Path.Direction.CCW);
                canvas.clipPath(path);
                canvas.drawBitmap(bitmap, -w / 2+dir[i]* l * w / 30, -h / 2, paint);
                canvas.restore();

            }
            canvas.restore();
            l+=k;
            if(l == 10) {
                k = 0;
                stopMoving = true;
            }
        }
        public void startMoving() {
            k = 1;
        }
        public boolean isStopMoving() {
            return stopMoving;
        }
        public void setColorFilter(ColorFilter colorFilter) {
            paint.setColorFilter(colorFilter);
        }
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }
        public int getOpacity() {
            return 0;
        }
    }
}

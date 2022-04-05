package com.example.shoppingpay.tagcastscan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.shoppingpay.R;

public class LoadingView extends View {

    private boolean flgStartDraw = false;
    private int animCount = 0;
    private float scale;

    private OnAnimationFinishListener onAnimationFinishListener = null;
    public interface OnAnimationFinishListener{
        void onFinish(View view);
    }

    public LoadingView(@NonNull final Context context, final float scale, @Nullable OnAnimationFinishListener listener) {
        super(context);
        this.scale = scale;
        this.onAnimationFinishListener = listener;
        initialize(context);
    }

    public void start(){
        animCount = 0;
        if (animationThread != null && animationThread.isAlive() && !animationThread.isInterrupted()) {
            animationThread.interrupt();
        }
        animationThread = new AnimationThread();
        animationThread.start();
    }

    private AnimationThread animationThread = null;
    private class AnimationThread extends Thread{
        @Override
        public void run() {
            boolean flgFirst = true;
            long startTime = 0;
            while (!isInterrupted()) {
                long nowTime = System.currentTimeMillis();
                if (flgStartDraw) {
                    if (flgFirst){
                        flgFirst = false;
                        startTime = System.currentTimeMillis();
                    }
                    animCount = (int) (nowTime - startTime);
                    postInvalidate();
                    if (animCount >= 10000) {
                        animCount = 10000;
                        break;
                    }
                }
                long sleepTime = (long) ((1000f / 60f) - (System.currentTimeMillis() - nowTime));
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            if (onAnimationFinishListener != null) {
                onAnimationFinishListener.onFinish(LoadingView.this);
            }
        }
    }

    private Rect src = new Rect();
    private RectF dst = new RectF();
    private Bitmap bmpChecking = null;
    private Bitmap bmpBuilding = null;
    private Paint paintDef = new Paint();
    private Paint paintBuilding = new Paint();

    private void initialize(Context context){
        setDrawingCacheEnabled(false);
        if (animationThread != null && animationThread.isAlive() && !animationThread.isInterrupted()) {
            animationThread.interrupt();
        }
        animationThread = null;
        animCount = 0;
        paintDef.setDither(false);
        paintDef.setFilterBitmap(true);
        paintDef.setAntiAlias(true);
        paintDef.setStyle(Paint.Style.FILL);
        paintBuilding.setDither(false);
        paintBuilding.setFilterBitmap(true);
        paintBuilding.setAntiAlias(true);
        paintBuilding.setColorFilter(new PorterDuffColorFilter(0xffb31011, PorterDuff.Mode.SRC_ATOP));
        bmpChecking = BitmapFactory.decodeResource(context.getResources(), R.drawable.caption_middle_a);
        bmpBuilding = BitmapFactory.decodeResource(context.getResources(), R.drawable.building_a);
        src.left = 0;
        src.top = 0;

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        flgStartDraw = true;
        final int width = getWidth();
        final int height = getHeight();
        final float cx = width/2f;
        final float cy = height/2f;

        int defAlpha = 255;
        if (animCount > 9700) {
            if (animCount < 10000) {
                defAlpha = Math.round(((10000 - animCount) / 300f) * 255f);
            } else  {
                defAlpha = 0;
            }
            if (defAlpha < 0) {
                defAlpha=0;
            }
        }

        // CircleBG
        if (animCount > 200) {
            paintDef.setColor(0xff999999);
            paintDef.setAlpha(defAlpha);
            final float circleBgRad;
            if (animCount < 500) {
                circleBgRad = (1f - (500 - animCount) / 300f) * 100f * scale;
            } else {
                circleBgRad = 100f * scale;
            }
            canvas.drawCircle(cx, cy - 68f * scale, circleBgRad, paintDef);
        }

        // CheckingBmp
        if (animCount > 500) {
            src.right = 154;
            src.bottom = 37;
            dst.left = cx - 77f * scale;
            dst.top = cy + 80f * scale;
            dst.right = dst.left + src.right * scale;
            dst.bottom = dst.top + src.bottom * scale;
            final float alpha;
            if (animCount < 600) {
                alpha = 0f;
            } else if (animCount < 1000) {
                alpha = 1f - (1000 - animCount) / 400f;
            } else if (animCount < 1300) {
                alpha = (1300 - animCount) / 300f;
            } else if (animCount < 1400) {
                alpha = 0f;
            } else if (animCount < 1800) {
                alpha = 1f - (1800 - animCount) / 400f;
            } else if (animCount < 2100) {
                alpha = (2100 - animCount) / 300f;
            } else if (animCount < 2200) {
                alpha = 0f;
            } else if (animCount < 2600) {
                alpha = 1f - (2600 - animCount) / 400f;
            } else if (animCount < 3000) {
                alpha = (3000 - animCount) / 300f;
            } else if (animCount < 3200) {
                alpha = 0f;
            } else if (animCount < 3600) {
                alpha = 1f - (3600 - animCount) / 400f;
            } else if (animCount < 4100) {
                alpha = (4100 - animCount) / 300f;
            } else if (animCount < 4200) {
                alpha = 0f;
            } else if (animCount < 4600) {
                alpha = 1f - (4600 - animCount) / 400f;
            } else if (animCount < 5000) {
                alpha = (5000 - animCount) / 300f;
            } else if (animCount < 5200) {
                alpha = 0f;
            } else if (animCount < 5600) {
                alpha = 1f - (5600 - animCount) / 400f;
            } else if (animCount < 6100) {
                alpha = (6100 - animCount) / 300f;
            } else if (animCount < 6200) {
                alpha = 0f;
            } else if (animCount < 6600) {
                alpha = 1f - (6600 - animCount) / 400f;
            } else if (animCount < 7000) {
                alpha = (7000 - animCount) / 300f;
            } else if (animCount < 7200) {
                alpha = 0f;
            } else if (animCount < 7600) {
                alpha = 1f - (7600 - animCount) / 400f;
            } else if (animCount < 8100) {
                alpha = (8100 - animCount) / 300f;
            } else if (animCount < 8200) {
                alpha = 0f;
            } else if (animCount < 8600) {
                alpha = 1f - (8600 - animCount) / 400f;
            } else if (animCount < 9000) {
                alpha = (9000 - animCount) / 300f;
            } else if (animCount < 9200) {
                alpha = 0f;
            } else {
                alpha = 1.0f;
            }
            if (defAlpha == 255) {
                paintDef.setAlpha(Math.round(255f * alpha));
            } else {
                paintDef.setAlpha(defAlpha);
            }
            canvas.drawBitmap(bmpChecking, src, dst, paintDef);
        }

        // CircleWhite
        if (animCount > 700) {
            dst.left = cx - 100f * scale;
            dst.top = cy - (100f + 68f) * scale;
            dst.right = cx + 100f * scale;
            dst.bottom = cy + (100f - 68f) * scale;
            paintDef.setColor(Color.parseColor("#D7CCC8"));
            paintDef.setAlpha(defAlpha);
            float angle;
            if (animCount < 500){
                angle = 18f ;
            } else if (animCount < 1000) {
                angle = 36f ;
            } else if (animCount < 1500) {
                angle = 54f ;
            } else if(animCount < 2000){
                angle = 72f;
            } else if (animCount < 2500) {
                angle =90f ;
            } else if (animCount < 3000) {
                angle = 108f;
            }else if (animCount < 3500) {
                angle =126f  ;
            } else if(animCount < 4000){
                angle = 144f;
            } else if (animCount < 4500) {
                angle = 162f;
            } else if (animCount < 5000) {
                angle =180f ;
            }else if (animCount < 5500) {
                angle = 198f;
            } else if (animCount < 6000) {
                angle = 216f ;
            } else if(animCount < 6500){
                angle =234f ;
            } else if (animCount < 7000) {
                angle = 252f;
            } else if (animCount < 7500) {
                angle =270f ;
            }else if (animCount < 8000) {
                angle = 288f ;
            } else if(animCount < 8500){
                angle =306f ;
            } else if (animCount < 9000) {
                angle =324f;
            } else if (animCount < 9500) {
                angle=342f;
            }
            else {
                angle = 360f;
            }
            canvas.drawArc(dst, -90, angle, true, paintDef);
        }

        // BuildingBmp
        if (animCount > 600) {
            src.right = 88;
            src.bottom = 88;
            dst.left = cx - 44f * scale;
            dst.top = cy - 68f * scale - 44f * scale;
            dst.right = dst.left + src.right * scale;
            dst.bottom = dst.top + src.bottom * scale;
            paintDef.setAlpha(defAlpha);
            paintBuilding.setAlpha(defAlpha);
            if (animCount < 9500) {
                canvas.drawBitmap(bmpBuilding, src, dst, paintDef);
            } else {
                canvas.drawBitmap(bmpBuilding, src, dst, paintBuilding);
            }
        }
    }
}

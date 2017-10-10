package com.shian.shianlife.view.piccorner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.shian.shianlife.R;


/**
 * Created by zm.
 */

public class RoundCornerImageView extends ImageView {
    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        int dp10 = getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
        clipPath.addRoundRect(new RectF(0, 0, w, h), dp10, dp10, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}

package com.shian.shianlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by zm.
 */

public class ScrollExpandableListView extends ExpandableListView {
    public ScrollExpandableListView(Context context) {
        super(context);
    }

    public ScrollExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

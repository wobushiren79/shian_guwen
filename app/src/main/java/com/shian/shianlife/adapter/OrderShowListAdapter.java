package com.shian.shianlife.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.mvp.order.bean.OrderShowResultBean;


/**
 * Created by zm.
 */

public class OrderShowListAdapter extends BaseRCAdapter<OrderShowResultBean.Item> {

    /**
     * 单布局初始化
     *
     * @param context
     */
    public OrderShowListAdapter(Context context) {
        super(context, R.layout.layout_order_show);
    }


    @Override
    public void convert(BaseViewHolder holder, OrderShowResultBean.Item orderShowItemBean, int index) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        LinearLayout llContent = holder.getView(R.id.ll_content);
        if (orderShowItemBean.getName() != null)
            tvTitle.setText(orderShowItemBean.getName());
        if (orderShowItemBean.getPicId() != 0)
            ivIcon.setImageResource(orderShowItemBean.getPicId());
        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setClickZoomEffect(v);
            }
        });
    }

    /**
     * 设置点击放大效果。
     */
    public static void setClickZoomEffect(final View view) {
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() {
                boolean cancelled;
                Rect rect = new Rect();

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            scaleTo(v, 0.8f);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (rect.isEmpty()) {
                                v.getDrawingRect(rect);
                            }
                            if (!rect.contains((int) event.getX(), (int) event.getY())) {
                                scaleTo(v, 1);
                                cancelled = true;
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            if (!cancelled) {
                                scaleTo(v, 1);
                            } else {
                                cancelled = false;
                            }
                            break;
                        }
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 对view进行缩放。
     */
    @SuppressLint("NewApi")
    public static void scaleTo(View v, float scale) {
        if (Build.VERSION.SDK_INT >= 11) {
            v.setScaleX(scale);
            v.setScaleY(scale);
        } else {
            float oldScale = 1;
            if (v.getTag(Integer.MIN_VALUE) != null) {
                oldScale = (Float) v.getTag(Integer.MIN_VALUE);
            }
            final ViewGroup.LayoutParams params = v.getLayoutParams();
            params.width = (int) ((params.width / oldScale) * scale);
            params.height = (int) ((params.height / oldScale) * scale);
            v.setTag(Integer.MIN_VALUE, scale);
        }
    }
}

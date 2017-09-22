package com.shian.shianlife.view.goods;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.WebActivity;
import com.shian.shianlife.activity.goods.GoodsShoppingCartActivity;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.thisenum.RoleEnum;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by zm.
 */

public class GoodsShoppingCartButton extends ImageView implements View.OnClickListener {

    private int groupWidth;
    private int groupHeigh;

    private int lastX;
    private int lastY;

    private float itemX;
    private float itemY;

    private boolean isMove = false;

    public GoodsShoppingCartButton(Context context) {
        super(context);
    }

    public GoodsShoppingCartButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initData();
    }


    private void initView() {
        this.setOnClickListener(this);

    }

    private void initData() {
        //获取屏幕宽高
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        groupWidth = manager.getDefaultDisplay().getWidth();
        groupHeigh = manager.getDefaultDisplay().getHeight();
    }


    public void setWHData(final Integer groupWidth, final Integer groupHeigh) {
        if (groupWidth != null)
            this.groupWidth = groupWidth;
        if (groupHeigh != null)
            this.groupHeigh = groupHeigh;
//        this.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN:
//                        isMove = false;
//                        lastX = (int) event.getRawX();
//                        lastY = (int) event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        isMove = true;
//                        int dx = (int) event.getRawX() - lastX;
//                        int dy = (int) event.getRawY() - lastY;
//
//                        int left = GoodsShoppingCartButton.this.getLeft() + dx;
//                        int top = GoodsShoppingCartButton.this.getTop() + dy;
//                        int right = GoodsShoppingCartButton.this.getRight() + dx;
//                        int bottom = GoodsShoppingCartButton.this.getBottom() + dy;
//                        if (left < 0) {
//                            left = 0;
//                            right = left + GoodsShoppingCartButton.this.getWidth();
//                        }
//                        if (right > groupWidth) {
//                            right = groupWidth;
//                            left = right - GoodsShoppingCartButton.this.getWidth();
//                        }
//                        if (top < 0) {
//                            top = 0;
//                            bottom = top + GoodsShoppingCartButton.this.getHeight();
//                        }
//                        if (bottom > groupHeigh) {
//                            bottom = groupHeigh;
//                            top = bottom - GoodsShoppingCartButton.this.getHeight();
//                        }
//                        GoodsShoppingCartButton.this.layout(left, top, right, bottom);
//                        lastX = (int) event.getRawX();
//                        lastY = (int) event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isMove = false;
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                itemX = GoodsShoppingCartButton.this.getX();
                itemY = GoodsShoppingCartButton.this.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                int left = GoodsShoppingCartButton.this.getLeft() + dx;
                int top = GoodsShoppingCartButton.this.getTop() + dy;
                int right = GoodsShoppingCartButton.this.getRight() + dx;
                int bottom = GoodsShoppingCartButton.this.getBottom() + dy;
                if (left < 0) {
                    left = 0;
                    right = left + GoodsShoppingCartButton.this.getWidth();
                }
                if (right > groupWidth) {
                    right = groupWidth;
                    left = right - GoodsShoppingCartButton.this.getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + GoodsShoppingCartButton.this.getHeight();
                }
                if (bottom > groupHeigh) {
                    bottom = groupHeigh;
                    top = bottom - GoodsShoppingCartButton.this.getHeight();
                }
                GoodsShoppingCartButton.this.layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                float moveX = GoodsShoppingCartButton.this.getX() - itemX;
                float moveY = GoodsShoppingCartButton.this.getY() - itemY;
                int dp30 = getResources().getDimensionPixelOffset(R.dimen.dimen_30dp);
                if (Math.abs(moveX) < dp30 && Math.abs(moveY) < dp30) {
                    isMove = false;
                } else {
                    isMove = true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (isMove)
            return;
        if (AppContansts.systemLoginInfo == null || AppContansts.systemLoginInfo.getResourceCodes() == null)
            return;
        boolean hasGoodAdviser = RoleEnum.checkHasRole(AppContansts.systemLoginInfo.getResourceCodes(), RoleEnum.Goods_Advisor);

        if (hasGoodAdviser) {
            Intent intent = new Intent(getContext(), GoodsShoppingCartActivity.class);
            getContext().startActivity(intent);
        } else {
            Intent intent = new Intent(getContext(), WebActivity.class);
            intent.putExtra(IntentName.INTENT_URL, AppContansts.Goods_BaseUrl);
            getContext().startActivity(intent);
        }

    }

}

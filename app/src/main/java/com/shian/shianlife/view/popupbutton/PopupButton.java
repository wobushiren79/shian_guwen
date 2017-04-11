package com.shian.shianlife.view.popupbutton;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shian.shianlife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class PopupButton extends RelativeLayout {
    View view;

    RelativeLayout mRLMain;
    ImageView mIVMianButton;
    ImageView mIVBack;

    List<PopupButtonItem> listHItem = new ArrayList<>();
    List<PopupButtonItem> listVitem = new ArrayList<>();

    boolean isOpen = false;
    PopupButtonCallBack callBack;

    int idHlast = 0;
    int idVlast = 1;
    boolean isHFirstAdd = true;
    boolean isVFirstAdd = true;

    public PopupButton(Context context) {
        this(context, null);
    }

    public PopupButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_popup_button, this);
        initView();
    }

    private void initView() {
        mRLMain = (RelativeLayout) view.findViewById(R.id.rl_main);
        mIVMianButton = (ImageView) view.findViewById(R.id.iv_buildnew);
        mIVBack = (ImageView) view.findViewById(R.id.iv_back);

        mIVMianButton.setOnClickListener(onClickListener);
        mIVBack.setOnClickListener(onClickListener);
    }


    public void setCallBack(PopupButtonCallBack callBack) {
        this.callBack = callBack;
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVMianButton) {
                mainButton();
            } else if (v == mIVBack) {
                mainButton();
            }
        }
    };

    public void mainButton() {
        if (isOpen) {
            closeAnim();
//            mRLMain.setBackgroundResource(0);
            mIVBack.setImageResource(0);
            mIVBack.setVisibility(GONE);
            isOpen = false;
        } else {
            openAnim();
//            mRLMain.setBackgroundResource(R.drawable.zhy_build_order_back);
            mIVBack.setImageResource(R.drawable.zhy_build_order_back);
            mIVBack.setVisibility(VISIBLE);
            isOpen = true;
        }
    }

    /**
     * 动画处理
     */
    private void openAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation
                (0, 45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        mIVMianButton.startAnimation(rotateAnimation);
        for (PopupButtonItem item : listVitem) {
            item.setVisibility(VISIBLE);
            item.startAnimation(getOpenButtonAnim());
        }
        for (PopupButtonItem item : listHItem) {
            item.setVisibility(VISIBLE);
            item.startAnimation(getOpenButtonAnim());
        }
    }

    private void closeAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation
                (45, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        mIVMianButton.startAnimation(rotateAnimation);
        for (PopupButtonItem item : listVitem) {
            item.startAnimation(getCloseButtonAnim());
            item.setVisibility(GONE);
        }
        for (PopupButtonItem item : listHItem) {
            item.startAnimation(getCloseButtonAnim());
            item.setVisibility(GONE);
        }
    }

    /**
     * 增加按钮
     */
    public void addHorizontalButton(String name, int iconId, int positionButton) {
        PopupButtonItem popupButtonItem = initButton(name, iconId, positionButton);
        listHItem.add(popupButtonItem);
        popupButtonItem.setId(idHlast + 2);
        LayoutParams layoutParams = new LayoutParams((int) popupButtonItem.itemW, (int) popupButtonItem.itemH);
        popupButtonItem.setLayoutParams(layoutParams);
        if (isHFirstAdd) {
            layoutParams.addRule(LEFT_OF, mIVMianButton.getId());
            layoutParams.addRule(ALIGN_BOTTOM, mIVMianButton.getId());
            isHFirstAdd = false;
        } else {
            layoutParams.addRule(LEFT_OF, idHlast);
            layoutParams.addRule(ALIGN_BOTTOM, idHlast);
        }
        idHlast = popupButtonItem.getId();
    }

    public void addVerticalButton(String name, int iconId, int positionButton) {
        PopupButtonItem popupButtonItem = initButton(name, iconId, positionButton);
        listVitem.add(popupButtonItem);
        popupButtonItem.setId(idVlast + 2);
        LayoutParams layoutParams = new LayoutParams((int) popupButtonItem.itemW, (int) popupButtonItem.itemH);
        popupButtonItem.setLayoutParams(layoutParams);
        if (isVFirstAdd) {
            layoutParams.addRule(BELOW, mIVMianButton.getId());
            layoutParams.addRule(ALIGN_RIGHT, mIVMianButton.getId());
            isVFirstAdd = false;
        } else {
            layoutParams.addRule(BELOW, idVlast);
            layoutParams.addRule(ALIGN_RIGHT, idVlast);
        }
        idVlast = popupButtonItem.getId();
    }

    /**
     * 初始化按钮
     *
     * @param name
     * @param iconId
     */
    private PopupButtonItem initButton(String name, int iconId, final int positionButton) {
        PopupButtonItem popupButtonItem = new PopupButtonItem(getContext());
        popupButtonItem.setData(name, iconId);
        popupButtonItem.setVisibility(GONE);
        popupButtonItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onClick(positionButton);
                }
            }
        });
        mRLMain.addView(popupButtonItem);
        return popupButtonItem;
    }

    public interface PopupButtonCallBack {
        void onClick(int positionButton);
    }

    /**
     * 动画制作
     */
    private AnimationSet getOpenButtonAnim() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);

        ScaleAnimation scaleAnimation = new ScaleAnimation
                (0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    private AnimationSet getCloseButtonAnim() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(500);

        ScaleAnimation scaleAnimation = new ScaleAnimation
                (1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);


        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }
}

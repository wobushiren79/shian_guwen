package com.shian.shianlife.view.popupbutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/30.
 */

public class PopupButtonItem extends LinearLayout implements View.OnClickListener {
    View view;

    ImageView mIVIcon;
    TextView mTVTitle;

    public float itemW;
    public float itemH;

    public PopupButtonItem(Context context) {
        this(context, null);
    }

    public PopupButtonItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_popup_button_item, this);

        initView();
        initData();
    }

    private void initData() {
        itemW=getContext().getResources().getDimension(R.dimen.dimen_163dp);
        itemH=getContext().getResources().getDimension(R.dimen.dimen_163dp);
    }

    private void initView() {
        mIVIcon = (ImageView) view.findViewById(R.id.iv_icon);
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    public void setData(String title, int iconId) {
        mIVIcon.setImageResource(iconId);
        mTVTitle.setText(title);
    }


    @Override
    public void onClick(View v) {

    }
}

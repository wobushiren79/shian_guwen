package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsQueryConditionButton extends BaseLayout implements View.OnClickListener {
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.iv_expandable)
    ImageView ivExpandable;

    public final static int Mode_DESC = 0;
    public final static int Mode_ASC = 1;

    private int modeType;
    private boolean isCheck;
    private CallBack callBack;

    public GoodsQueryConditionButton(Context context) {
        this(context, null);
    }

    public GoodsQueryConditionButton(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_query_condition_button, attrs);
        this.modeType = Mode_DESC;
        this.isCheck = false;
        setMode(Mode_DESC);
    }

    @Override
    protected void initView() {
        this.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setName(titleName);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onClick(View v) {
        if (modeType == Mode_ASC) {
            setMode(Mode_DESC);
        } else if (modeType == Mode_DESC) {
            setMode(Mode_ASC);
        }

        if (callBack != null)
            callBack.changeMode(this, modeType);
    }

    public void setMode(int modeType) {
        this.modeType = modeType;

        if (modeType == Mode_DESC) {
            ivExpandable.setRotation(90);
        } else if (modeType == Mode_ASC) {
            ivExpandable.setRotation(-90);
        }

        if (callBack != null)
            callBack.changeMode(this, modeType);
    }

    public void setCheckStatus(boolean isCheck) {
        if (isCheck) {
            tvName.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
            ivExpandable.setVisibility(VISIBLE);
        } else {
            tvName.setTextColor(getResources().getColor(R.color.zhy_text_color_22));
            ivExpandable.setVisibility(GONE);
        }

    }

    public void setName(String name) {
        tvName.setText(name);
    }

    public interface CallBack {
        void changeMode(View view, int mode);
    }
}

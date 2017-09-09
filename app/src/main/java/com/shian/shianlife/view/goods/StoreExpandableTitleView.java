package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.common.utils.AnimUtils;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class StoreExpandableTitleView extends BaseLayout implements View.OnClickListener {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.iv_expandable)
    ImageView ivExpandable;

    private CallBack callBack;
    private boolean isExpandable = false;

    public StoreExpandableTitleView(Context context) {
        super(context, R.layout.layout_expandable_title);
    }

    public StoreExpandableTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_expandable_title, attrs);
        this.setOnClickListener(this);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void initView() {
        tvTitle.setText(titleName);
        tvContent.setText(contentText);
    }

    public void setData(String data) {
        tvContent.setText(data);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (isExpandable) {
            isExpandable = false;
            AnimUtils.startRotateToSelf(ivExpandable, 200, 90, 0, null);
        } else {
            isExpandable = true;
            AnimUtils.startRotateToSelf(ivExpandable, 200, 0, 90, null);
        }
        if (callBack != null)
            callBack.onClickExpandable(this, isExpandable);
    }

    public interface CallBack {
        void onClickExpandable(View view, boolean isExpandable);
    }
}

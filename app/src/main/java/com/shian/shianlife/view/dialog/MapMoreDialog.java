package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MapMoreDialog extends Dialog {
    View view;

    TextView mTVTitle;
    Button mBTBack;
    Button mBTChangeLocation;

    public MapMoreDialog(Context context, int themeResId) {
        super(context, themeResId);
        view = View.inflate(context, R.layout.dialog_map_more, null);
        setContentView(view);
        initView();
    }

    private void initView() {
        mTVTitle = (TextView) view.findViewById(R.id.tv_title);
        mBTBack = (Button) view.findViewById(R.id.bt_back);
        mBTChangeLocation = (Button) view.findViewById(R.id.bt_changelocation);

        mBTBack.setOnClickListener(onClickListener);
        mBTChangeLocation.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTBack) {
                cancel();
            } else if (v == mBTChangeLocation) {
                ToastUtils.show(getContext(), "改变地址");
            }
        }
    };

}

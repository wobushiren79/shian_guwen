package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.updata.ContractDataActivity;
import com.shian.shianlife.common.view.finger.DrawView;

/**
 * Created by Administrator on 2017/3/19.
 */

public class SignDialog extends Dialog {
    View view;
    LinearLayout mLLDraw;
    TextView mTVSubmit;


    CallBack callBack;

    public SignDialog(Context context, int themeResId) {
        super(context, themeResId);
        view = View.inflate(context, R.layout.dialog_sign, null);
        setContentView(view);
        initView();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void initView() {
        mLLDraw = (LinearLayout) view.findViewById(R.id.ll_draw);
        mTVSubmit = (TextView) view.findViewById(R.id.tv_submit);

        final DrawView drawView = new DrawView(getContext());
        drawView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        drawView.changeColour(7);
        drawView.requestFocus();
        drawView.changeColour(5);
        mLLDraw.addView(drawView);

        mTVSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    Bitmap bitmapName = drawView.getBitamp();
                    callBack.signComplete(bitmapName);
                    SignDialog.this.cancel();
                }
            }
        });
    }


    public interface CallBack {
        void signComplete(Bitmap bitmapName);
    }
}

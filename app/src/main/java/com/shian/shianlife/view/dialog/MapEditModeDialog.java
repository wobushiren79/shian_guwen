package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.map.NewMapChoiceActivity;
import com.shian.shianlife.activity.map.NewRoutePlanActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpChangeLocation;

/**
 * Created by Administrator on 2017/2/28.
 */

public class MapEditModeDialog extends Dialog {
    EditText mETContent;
    Button mBTCancel;
    Button mBTSubmit;

    MapEditModeDialogCallBack callBack;


    View view;

    public MapEditModeDialog(Context context, int themeResId) {
        super(context, themeResId);
        view = View.inflate(context, R.layout.dialog_map_edit_mode, null);
        setContentView(view);

        initView();
    }

    public void setCallBack(MapEditModeDialogCallBack callBack) {
        this.callBack = callBack;
    }

    private void initView() {
        mETContent = (EditText) view.findViewById(R.id.et_content);
        mBTCancel = (Button) view.findViewById(R.id.bt_cancel);
        mBTSubmit = (Button) view.findViewById(R.id.bt_submit);

        mBTCancel.setOnClickListener(onClickListener);
        mBTSubmit.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTSubmit) {
                submitData();
            } else if (v == mBTCancel) {
                cancel();
            }

        }
    };


    /**
     * 提交数据
     */
    private void submitData() {
        if (mETContent.getText().toString().equals("")) {
            ToastUtils.show(getContext(), "地址内容不能为空");
            return;
        }
        callBack.changeData(mETContent.getText().toString());
        cancel();
    }

    public interface MapEditModeDialogCallBack {
        void changeData(String location);
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);

        //设置动画效果
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 1000, 0);
        translateAnimation.setDuration(500);
        view.startAnimation(translateAnimation);
    }
}

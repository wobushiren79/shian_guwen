package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.map.NewMapChoiceActivity;
import com.shian.shianlife.activity.map.NewRoutePlanActivity;
import com.shian.shianlife.common.utils.ToastUtils;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MapMoreDialog extends Dialog {
    View view;

    TextView mTVTitle;
    Button mBTBack;
    Button mBTChangeLocation;

    DialogCallBack dialogCallBack;

    public void setDialogCallBack(DialogCallBack dialogCallBack) {
        this.dialogCallBack = dialogCallBack;
    }

    public MapMoreDialog(Context context, int themeResId) {
        super(context, themeResId);
        view = View.inflate(context, R.layout.dialog_map_more, null);

        setContentView(view);
        initView();

        //设置长宽
        WindowManager.LayoutParams params = this.getWindow()
                .getAttributes();
        params.width = getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_500dp);
        params.height =  getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_300dp);
        this.getWindow().setAttributes(params);
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
                changeLocation();
            }
        }
    };

    private void changeLocation() {
        if(NewRoutePlanActivity.consultId==-1||NewRoutePlanActivity.locationType==-1){
            ToastUtils.show(getContext(),"不能更改当前地址");
        }else{
            Intent intent = new Intent(getContext(), NewMapChoiceActivity.class);
            getContext().startActivity(intent);
            dialogCallBack.changeLocation();
        }
        cancel();
    }

    public interface DialogCallBack{
        void changeLocation();
    }

}

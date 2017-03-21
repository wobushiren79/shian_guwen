package com.shian.shianlife.view.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.shian.shianlife.adapter.SetMealSelectAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.model.AddedCtgModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class SetMealSelectDialog extends BaseDialog {
    ListView contentListView;
    SetMealSelectAdapter adapter;

    private List<AddedCtgModel> mAddedCtgModels = new ArrayList<>();
    private List<AddedCtgModel> mSelectAddedList = new ArrayList<>();
    private CallBack callback;

    public SetMealSelectDialog(Context context, List<AddedCtgModel> mAddedCtgModels) {
        super(context);
        this.mAddedCtgModels = mAddedCtgModels;
        initDialog();
    }

    private void initDialog() {
        initListView();
        initOtherView();
    }

    private void initOtherView() {
        setTitle("选择增值服务产品");

        mTVCancel.setOnClickListener(onClickListener);
        mTVSubmit.setOnClickListener(onClickListener);
    }


    public void setCallback(CallBack callback) {
        this.callback = callback;
    }

    private void initListView() {
        contentListView = new ListView(getContext());
        contentListView.setDivider(null);
        contentListView.setDividerHeight(0);
        RelativeLayout.LayoutParams layoutParams;
        if (mAddedCtgModels.size() > 6) {
            layoutParams = new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (int) (BaseActivity.metrics.heightPixels * 0.6));
        } else {
            layoutParams = new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        contentListView.setLayoutParams(layoutParams);
        adapter = new SetMealSelectAdapter(getContext(), mAddedCtgModels);
        contentListView.setAdapter(adapter);
        setContent(contentListView);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVCancel) {
                SetMealSelectDialog.this.cancel();
            } else if (v == mTVSubmit) {
                saveData();
                SetMealSelectDialog.this.cancel();
            }
        }
    };

    private void saveData() {
        if (callback != null) {
            for (AddedCtgModel data : mAddedCtgModels) {
                if (data.isCheck()) {
                    mSelectAddedList.add(data);
                }
            }
            callback.submit(mSelectAddedList);
        }
        for (AddedCtgModel data : mAddedCtgModels) {
            data.setCheck(false);
        }
    }

    public interface CallBack {
        void submit(List<AddedCtgModel> listData);
    }

}

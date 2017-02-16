package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.local.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class EditOrderSetmealChangeDialog extends Dialog {
    ListView listView;
    View view;

    List<String> listData = new ArrayList<>();

    DialogCallBack dialogCallBack;

    public EditOrderSetmealChangeDialog(Context context, int themeResId, DialogCallBack dialogCallBack) {
        super(context, themeResId);
        this.dialogCallBack = dialogCallBack;
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_editordersetmealchange, null);
        super.setContentView(view);
        initView();
    }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.listview_dialog);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dialogCallBack != null) {
                    dialogCallBack.getSelectString(listData.get(position),position);
                    EditOrderSetmealChangeDialog.this.cancel();
                }
            }
        });
    }

    public void setData(List<String> listData) {
        this.listData = listData;
    }

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AbsListView.LayoutParams layoutparams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_50dp));


            TextView textView = new TextView(getContext());
            textView.setText(listData.get(position));
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutparams);
            return textView;
        }
    };


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

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 1000, 0);
        translateAnimation.setDuration(500);
        view.startAnimation(translateAnimation);

    }

    @Override
    public void cancel() {
        super.cancel();
    }

    public interface DialogCallBack {
        void getSelectString(String name,int position);
    }
}

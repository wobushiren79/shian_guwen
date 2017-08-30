package com.shian.shianlife.view.searchview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by zm.
 */

public class CustomSearchView extends android.widget.SearchView implements android.widget.SearchView.OnQueryTextListener {
    private CallBack callBack;

    public CustomSearchView(Context context) {
        this(context, null);
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void initView() {
        this.setIconifiedByDefault(false);
        this.setQueryHint(getContext().getString(R.string.store_search_hint));
        this.setOnQueryTextListener(this);
        initSearchButton();
        initSearchText();
        initBackground();
    }

    private void initBackground() {
        this.setBackgroundResource(R.drawable.zhy_search_view_background_white);
        int searchPlateId = getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        LinearLayout searchPlate = (LinearLayout) this.findViewById(searchPlateId);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);
    }

    private void initSearchButton() {
        //设置搜索图标
        //获取ImageView的id
        int searchButtonId = getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
        //获取ImageView
        ImageView searchButton = (ImageView) this.findViewById(searchButtonId);
        //设置图片
        searchButton.setImageResource(R.drawable.zhy_seach_icon);
        //设置imageView大小
        int dp36 = this.getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_36dp);
        LinearLayout.LayoutParams searchButtonLayout = new LinearLayout.LayoutParams(dp36, dp36);
        searchButtonLayout.gravity = Gravity.CENTER_VERTICAL;
        searchButton.setLayoutParams(searchButtonLayout);
    }

    private void initSearchText() {
        //设置字体样式
        //获取到TextView的ID
        int id = getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        //获取到TextView的控件
        TextView textView = (TextView) this.findViewById(id);
        //设置字体大小
        int dp28 = this.getContext().getResources().getDimensionPixelOffset(R.dimen.dimen_28dp);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dp28);//14sp
        //设置字体颜色
        textView.setTextColor(getResources().getColor(R.color.zhy_text_color_16));
        //设置提示文字颜色
        textView.setHintTextColor(getResources().getColor(R.color.zhy_text_color_15));
        LinearLayout.LayoutParams textViewLayout = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        textViewLayout.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(textViewLayout);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (callBack != null)
            callBack.onQueryTextSubmit(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (callBack != null)
            callBack.onQueryTextChange(newText);
        return false;
    }

    public void setData(String data) {
        int id = getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) this.findViewById(id);
        textView.setText(data);
    }


    public interface CallBack {
        boolean onQueryTextSubmit(String query);

        boolean onQueryTextChange(String newText);
    }
}

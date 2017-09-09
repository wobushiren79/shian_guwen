package com.shian.shianlife.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.LocationSelectorDialogBuilder;

import java.lang.reflect.Field;

/**
 * Created by zm.
 */

public class ViewUtils {
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }


    /**
     * 展开下拉
     *
     * @param listView
     * @param listAdapter
     */
    public static void expandGroup(ExpandableListView listView, BaseExpandableListAdapter listAdapter) {
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }


    /**
     * 获取地区选择器
     */
    public static void getLocationSelectDialog(Context context, LocationSelectorDialogBuilder.OnSaveLocationLister lister) {
        LocationSelectorDialogBuilder dialogBuilder = new LocationSelectorDialogBuilder(context, R.style.CustomDialog);
        dialogBuilder.setOnSaveLocationLister(lister);
        dialogBuilder.show();
    }

    /**
     * 获取地区选择器
     */
    public static void getTimeSelectDialog(Context context, DateTimeSelectorDialogBuilder.OnSaveListener listener, boolean isShowHour) {
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder.getInstance(context);
        dialog.setOnSaveListener(listener);
        dialog.setShowHour(isShowHour);
        dialog.show();
    }

}

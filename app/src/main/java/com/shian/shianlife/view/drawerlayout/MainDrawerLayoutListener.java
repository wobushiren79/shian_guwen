package com.shian.shianlife.view.drawerlayout;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.shian.shianlife.R;


/**
 * Created by zm.
 */

public class MainDrawerLayoutListener implements DrawerLayout.DrawerListener {
    private int windowW;
    private int windowH;
    private DrawerLayout mDrawerLayout;
    private Context context;

    public MainDrawerLayoutListener(Context context, DrawerLayout drawerLayout, int windowW, int windowH) {
        this.windowW = windowW;
        this.windowH = windowH;
        this.mDrawerLayout = drawerLayout;
        this.context = context;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        View mContent = mDrawerLayout.getChildAt(0);
        int dp120 = context.getResources().getDimensionPixelOffset(R.dimen.dimen_120dp);
        mContent.setTranslationX((windowW - dp120) * slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {


    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}

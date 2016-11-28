package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public abstract class BaseOrderView extends RelativeLayout{

	public BaseOrderView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public abstract void refresh();

}

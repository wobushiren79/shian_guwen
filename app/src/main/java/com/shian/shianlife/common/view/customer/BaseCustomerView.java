package com.shian.shianlife.common.view.customer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BaseCustomerView extends FrameLayout{

	public BaseCustomerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	private boolean isFlag;
	public void initData(){
		if(isFlag){
			isFlag=true;
			return;
		}
	}
}

package cn.ifavor.cycleviewpager.tools;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtils {

	public static Drawable createShape(int radius, int color) {
		GradientDrawable normalDrawable = new GradientDrawable();
		normalDrawable.setCornerRadius(radius);
		normalDrawable.setColor(color);
		return normalDrawable;
	}
	

	public static  Drawable createEnableDisableSelector(int radius, int disableColor, int enableColor) {

		StateListDrawable stateListDrawable = new StateListDrawable();
		stateListDrawable.addState(new int[] { android.R.attr.state_enabled },
				createShape(radius, enableColor ));
		stateListDrawable.addState(new int[] {}, createShape(radius,disableColor ) );
		return stateListDrawable;
	}

}

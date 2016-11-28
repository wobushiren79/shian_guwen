package com.shian.shianlife.common.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class ColorsTextView extends TextView {

	public ColorsTextView(Context context) {
		super(context);
	}

	public ColorsTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ColorsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void appendContent(CharSequence mCharSequence, int color) {
		SpannableString mSpannableString = new SpannableString(mCharSequence);
		mSpannableString.setSpan(new ForegroundColorSpan(color), 0, mCharSequence.length(),
				Spanned.SPAN_INCLUSIVE_INCLUSIVE);
		append(mSpannableString);
	}

}

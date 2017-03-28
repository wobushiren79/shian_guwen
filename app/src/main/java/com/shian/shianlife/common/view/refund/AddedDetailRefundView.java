package com.shian.shianlife.common.view.refund;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class AddedDetailRefundView extends FrameLayout {

	private LinearLayout parentLayout;

	@InjectView(R.id.tv_totalprice)
	TextView tv_totalprice;

	float totalPrice;

	public AddedDetailRefundView(Context context) {
		super(context);
		init();
	}

	private void init() {
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mParams.topMargin = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_1dp);
		setLayoutParams(mParams);
		parentLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.view_addeddetail, null);
		addView(parentLayout);
		ButterKnife.inject(this);
		findViewById(R.id.ll_xx_4).setVisibility(View.GONE);
	}

	public void addCtgItem(OrderCtgItemModel ctgItemModel) {
//		View line = getLineView();
//		parentLayout.addView(line, parentLayout.getChildCount() - 2);
		CtgDetailRefundView mCtgDetailView = new CtgDetailRefundView(getContext());
		mCtgDetailView.setLayoutParams(
				new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		parentLayout.addView(mCtgDetailView, parentLayout.getChildCount() - 2);
		mCtgDetailView.setData(ctgItemModel);
		totalPrice += mCtgDetailView.getTotalPrice();
	}

	private View getLineView() {
		View line = new View(getContext());
		line.setBackgroundColor(getContext().getResources().getColor(R.color.gray_common));
		line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
		return line;
	}

	public void setData(HrGetOrderDetailResult result) {
		totalPrice = 0;
		List<ProjectItemModel> projectItems = result.getProjectItems();
		for (ProjectItemModel projectItemModel : projectItems) {
			if ("增值项目".equals(projectItemModel.getName())) {
				for (OrderCtgItemModel ctgItemModel : projectItemModel.getCtgItems()) {
					addCtgItem(ctgItemModel);
				}
				break;
			}
		}
		tv_totalprice.append(totalPrice + "");
	}
}

package com.shian.shianlife.common.view.orderdetail;

import java.util.List;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class AddedDetailView extends FrameLayout {

	private LinearLayout parentLayout;

	@InjectView(R.id.tv_totalprice)
	TextView tv_totalprice;

	float totalPrice;

	public AddedDetailView(Context context) {
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
	}

	public void addCtgItem(OrderCtgItemModel ctgItemModel) {
//		View line = getLineView();
//		parentLayout.addView(line, parentLayout.getChildCount() - 2);
		CtgDetailView mCtgDetailView = new CtgDetailView(getContext());
		mCtgDetailView.setLayoutParams(
				new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		parentLayout.addView(mCtgDetailView, parentLayout.getChildCount() - 2);
		mCtgDetailView.setData(ctgItemModel);
		totalPrice += mCtgDetailView.getTotalPrice();
	}

	private View getLineView() {
		View line = new View(getContext());
		line.setBackgroundColor(getContext().getResources().getColor(R.color.zhy_line_3));
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams
				(LinearLayout.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dimen_1dp));
		layoutParams.leftMargin=getResources().getDimensionPixelOffset(R.dimen.dimen_32dp);
		line.setLayoutParams(layoutParams);
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

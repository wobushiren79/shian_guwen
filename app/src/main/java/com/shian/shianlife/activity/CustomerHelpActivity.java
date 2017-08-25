package com.shian.shianlife.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.kf5sdk.init.KF5SDKConfig;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.thisenum.HelpEnum;
import com.shian.shianlife.view.ScrollGridView;
import com.shian.shianlife.view.customview.HelpHotIssue;
import com.shian.shianlife.view.customview.HelpItemsLayout;
import com.shian.shianlife.view.customview.MainDynamic;

import static com.shian.shianlife.thisenum.HelpEnum.MONEY;

public class CustomerHelpActivity extends BaseActivity {

    LinearLayout mLLLineHelp;
    LinearLayout mLLPhoneHelp;

    ScrollGridView mGridView;
    HelpHotIssue mHelpHotIssue;

    HelpEnum[] helpEna = {
            HelpEnum.MONEY,
            HelpEnum.SYSTEMHELP,
            HelpEnum.PROJECTDETAILS,
            HelpEnum.TALK,
            HelpEnum.PLATFORM,
            HelpEnum.OTHER
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_help);

        setTitle("客服帮助");
        initView();
    }

    private void initView() {
        mLLLineHelp = (LinearLayout) findViewById(R.id.ll_linehelp);
        mLLPhoneHelp = (LinearLayout) findViewById(R.id.ll_phonehelp);
        mGridView = (ScrollGridView) findViewById(R.id.gridview);
        mHelpHotIssue = (HelpHotIssue) findViewById(R.id.help_hotissue);
        mHelpHotIssue.setVisibility(View.GONE);


        mLLPhoneHelp.setOnClickListener(onClickListener);
        mLLLineHelp.setOnClickListener(onClickListener);

        mGridView.setAdapter(helpAdapter);


        mHelpHotIssue.setCallBack(new MainDynamic.CallBack() {
            @Override
            public void loadingComplete() {
                mHelpHotIssue.setVisibility(View.VISIBLE);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLLPhoneHelp) {
                Utils.call(v, "4009679678");
            } else if (v == mLLLineHelp) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    KF5SDKConfig.INSTANCE.startKF5ChatActivity(CustomerHelpActivity.this);
                } else {
                    ToastUtils.show(CustomerHelpActivity.this, "安卓系统版本太低，无法使用此功能");
                }
            }
        }
    };

    BaseAdapter helpAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return helpEna.length;
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
            HelpItemsLayout helpItemsLayout = new HelpItemsLayout(CustomerHelpActivity.this);
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dimen_132dp));
            helpItemsLayout.setLayoutParams(layoutParams);
            helpItemsLayout.setData(helpEna[position].getName(), helpEna[position].getPicId(), helpEna[position].getCode());
            return helpItemsLayout;
        }
    };
}

package com.shian.shianlife.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.WebActivity;
import com.shian.shianlife.activity.goods.GoodsQueryActivity;
import com.shian.shianlife.base.BaseFragment;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsChannelPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsChannelPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsChannelView;
import com.shian.shianlife.thisenum.AppRolePermition;
import com.shian.shianlife.thisenum.BuildOrderEnum;
import com.shian.shianlife.view.searchview.CustomSearchView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zm.
 */

public class StoreFragment extends BaseFragment implements View.OnClickListener, IGoodsChannelView, CustomSearchView.CallBack {
    View mLayoutView;

    private CustomSearchView searchView;
    private View layoutBuildCemetery;
    private View layoutBuildFuneral;

    private IGoodsChannelPresenter goodsChannelPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(R.layout.fragment_store, null, false);
        initView();
        ButterKnife.inject(this, mLayoutView);
        return mLayoutView;
    }

    private void initView() {
        layoutBuildCemetery = mLayoutView.findViewById(R.id.layout_build_cemetery);
        layoutBuildFuneral = mLayoutView.findViewById(R.id.layout_build_funeral);
        searchView = (CustomSearchView) mLayoutView.findViewById(R.id.search_view);

        ImageView cemeteryIcon = (ImageView) layoutBuildCemetery.findViewById(R.id.iv_build_icon);
        ImageView funeralIcon = (ImageView) layoutBuildFuneral.findViewById(R.id.iv_build_icon);

        TextView cemeteryName = (TextView) layoutBuildCemetery.findViewById(R.id.tv_build_name);
        TextView funeralName = (TextView) layoutBuildFuneral.findViewById(R.id.tv_build_name);
        TextView cemeteryContent = (TextView) layoutBuildCemetery.findViewById(R.id.tv_build_content);
        TextView funeralContent = (TextView) layoutBuildFuneral.findViewById(R.id.tv_build_content);

        cemeteryIcon.setImageResource(R.drawable.zhy_build_cemetery_icon);
        funeralIcon.setImageResource(R.drawable.zhy_build_funeral_icon);

        cemeteryName.setText("选购公墓");
        funeralName.setText("选购治丧方案");
        cemeteryContent.setText("为客户选择墓地|专车接送");
        funeralContent.setText("为客户选择治丧方案|生前契约");

        layoutBuildCemetery.setOnClickListener(this);
        layoutBuildFuneral.setOnClickListener(this);
        searchView.setCallBack(this);

        //检测权限
        if (AppContansts.userCemetery != null) {
            for (int i = 0; i < AppContansts.userCemetery.getPermitionCodes().size(); i++) {
                if (AppContansts.userCemetery.getPermitionCodes().get(i).equals(AppRolePermition.ADVISOR.getCode())) {
                    layoutBuildCemetery.setVisibility(View.VISIBLE);
                }
            }
        }

        goodsChannelPresenter = new GoodsChannelPresenterImpl(this);
        goodsChannelPresenter.getGoodsChannelData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View v) {
        if (v == layoutBuildFuneral) {
//            Intent intent = new Intent(getContext(), BuildOrderEnum.BY.getActivity());
//            startActivity(intent);
            Intent intent = new Intent(getContext(), WebActivity.class);
            intent.putExtra(IntentName.INTENT_URL, AppContansts.Temp_Funeral_BaseUrl);
            startActivity(intent);
        } else if (v == layoutBuildCemetery) {
            Intent intent = new Intent(getContext(), BuildOrderEnum.GM.getActivity());
            startActivity(intent);
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsChannelDataSuccess(List<GoodsChannelResultBean> listData) {

    }

    @Override
    public void getGoodsChannelDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void setChannelId(Integer channelId) {
        AppContansts.goodsChannelId = channelId;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent = new Intent(getContext(), GoodsQueryActivity.class);
        intent.putExtra(IntentName.INTENT_CLASSATTR_ID, -1);
        intent.putExtra(IntentName.INTENT_GOODSNAME, query);
        getContext().startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

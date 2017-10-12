package com.shian.shianlife.view.goods;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.view.dialog.GoodsSelectDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsSpecSelectDialogView extends BaseLayout implements View.OnClickListener, GoodsSelectDialog.CallBack {

    @InjectView(R.id.tv_goods_spec_select_name)
    TextView tvGoodsSpecSelectName;
    @InjectView(R.id.tv_good_sprice_select)
    TextView tvGoodSpriceSelect;

    private List<GoodsDetailsResultBean.SpecpriceBean> data;
    private CallBack callBack;

    private String unit;//单位
    private String picUrl;//默認展示圖片
    private Integer selectPosition;//选择的商品编号
    private Integer selectNumber;//选择的商品数量

    public GoodsSpecSelectDialogView(Context context) {
        this(context, null);
    }

    public GoodsSpecSelectDialogView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_spec_select_dialog, attrs);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        this.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        unit = "件";
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onClick(View v) {
        showDialog();
    }

    public void showDialog() {
        callBack = null;
        GoodsSelectDialog dialog = new GoodsSelectDialog(getContext());
        dialog.setData(data);
        dialog.setUnit(unit);
        dialog.setPic(picUrl);
        if (selectPosition != null)
            dialog.setSelect(selectPosition, selectNumber);
        dialog.setCallBack(this);
        dialog.show();
    }


    public void setSelectNameText(String name) {
        tvGoodsSpecSelectName.setText("已选择 " + name);
    }

    public void setSelectNumberText(String number) {
        tvGoodSpriceSelect.setText("x" + number);
    }

    /**
     * 设置选择数据
     *
     * @param data
     */
    public void setData(List<GoodsDetailsResultBean.SpecpriceBean> data) {
        this.data = data;
    }

    /**
     * 设置单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 设置默认展示图片
     */
    public void setDefPic(String picUrl) {
        this.picUrl = picUrl;
    }

    public GoodsDetailsResultBean.SpecpriceBean getSelectData() {
        if (selectPosition == null) {
            return null;
        } else {
            if (selectPosition < data.size())
                return data.get(selectPosition);
            else
                return null;
        }
    }

    public Integer getSelectPosition() {
        return selectPosition;
    }

    public Integer getSelectNumber() {
        return selectNumber;
    }

    @Override
    public void onSubmitClick(Dialog dialog, Integer position, Integer number) {
        this.selectNumber = number;
        this.selectPosition = position;

        if (position != null) {
            GoodsDetailsResultBean.SpecpriceBean itemData = data.get(position);
            setSelectNameText(itemData.getSpec_name());
            setSelectNumberText(number + unit);
        }
        if (callBack != null)
            callBack.successSelect();
    }

    public interface CallBack {
        void successSelect();
    }
}

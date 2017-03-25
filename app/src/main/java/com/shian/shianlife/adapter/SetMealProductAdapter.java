package com.shian.shianlife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class SetMealProductAdapter extends BaseAdapter {
    Context context;
    List<CreateOrderProductItemModel> productItemModels;
    List<Integer> showItemModels = new ArrayList<>();
    AdapterCallBack callBack;

    private boolean isEdit = true;

    public SetMealProductAdapter(Context context, List<CreateOrderProductItemModel> productItemModels) {
        this.context = context;
        this.productItemModels = productItemModels;

    }

    public void setCallBack(AdapterCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getCount() {
        showItemModels.clear();
        for (int i = 0; i < productItemModels.size(); i++) {
            if (productItemModels.get(i).getStatusFlag() == 1) {
                showItemModels.add(i);
            }
        }
        return showItemModels.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_setmeal_product_item, null);
            holder = new ViewHolder();
            holder.tvDetail = (TextView) convertView.findViewById(R.id.tv_detail);
            holder.tvDelete = (TextView) convertView.findViewById(R.id.tv_delete);
            holder.tvTitleName = (TextView) convertView.findViewById(R.id.tv_titlename);
            holder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
            holder.tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
            holder.ivAdd = (ImageView) convertView.findViewById(R.id.iv_add);
            holder.ivReduce = (ImageView) convertView.findViewById(R.id.iv_reduce);
            holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipelayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CreateOrderProductItemModel data = productItemModels.get(showItemModels.get(position));
        holder.tvTitleName.setText(data.getName());
        holder.tvNumber.setText("" + data.getNumber());
        holder.tvMoney.setText("￥ " + data.getTotalPrice());
        holder.tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getSKUDetails(context, data.getSkuId());
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeLayout.close();
                if (data.getId() == null) {
                    productItemModels.remove(data);
                } else {
                    data.setStatusFlag(2);
                }
                SetMealProductAdapter.this.notifyDataSetChanged();
                if (callBack != null) {
                    callBack.dataChange();
                    callBack.dataDelete(data);
                }
            }
        });
        holder.ivReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = data.getNumber();
                if (temp == 0) {
                    data.setNumber(temp);
                } else {
                    data.setNumber(temp - 1);
                }
                data.setTotalPrice(data.getNumber() * data.getPrice());
                SetMealProductAdapter.this.notifyDataSetChanged();
                if (callBack != null)
                    callBack.dataChange();
            }
        });
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = data.getNumber();
                data.setNumber(temp + 1);
                data.setTotalPrice(data.getNumber() * data.getPrice());
                SetMealProductAdapter.this.notifyDataSetChanged();
                if (callBack != null)
                    callBack.dataChange();
            }
        });
        if (isEdit) {
            holder.ivAdd.setVisibility(View.VISIBLE);
            holder.ivReduce.setVisibility(View.VISIBLE);
        } else {
            holder.ivAdd.setVisibility(View.INVISIBLE);
            holder.ivReduce.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    /**
     * 设置是否可编辑
     *
     * @param isEdit
     */
    public void isEdit(boolean isEdit) {
        this.isEdit = isEdit;
        this.notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tvDetail;
        TextView tvDelete;
        TextView tvTitleName;
        TextView tvNumber;
        TextView tvMoney;

        ImageView ivAdd;
        ImageView ivReduce;
        SwipeLayout swipeLayout;
    }

    public interface AdapterCallBack {
        void dataChange();
        void dataDelete(CreateOrderProductItemModel data);
    }
}

package com.shian.shianlife.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zm.
 */

public abstract class BaseRCAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements IBaseRCAdapter<T> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas = new ArrayList<>();
    protected LayoutInflater mInflater;

    /**
     * 单布局初始化
     *
     * @param context
     * @param layoutId
     */
    public BaseRCAdapter(Context context, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public List setData(List mDatas) {
        this.mDatas = mDatas;
        this.notifyDataSetChanged();
        return this.mDatas;
    }

    @Override
    public List setData(T[] mDatas) {
        this.mDatas = Arrays.asList(mDatas);
        this.notifyDataSetChanged();
        return this.mDatas;
    }

    @Override
    public List addData(List mDatas) {
        this.mDatas.addAll(mDatas);
        this.notifyDataSetChanged();
        return this.mDatas;
    }

    @Override
    public List addData(T[] mDatas) {
        this.mDatas.addAll(Arrays.asList(mDatas));
        this.notifyDataSetChanged();
        return this.mDatas;
    }

    @Override
    public List removeData(List mDatas) {
        this.mDatas.removeAll(mDatas);
        this.notifyDataSetChanged();
        return this.mDatas;
    }

    @Override
    public List removeData(T[] mDatas) {
        this.mDatas.removeAll(Arrays.asList(mDatas));
        this.notifyDataSetChanged();
        return this.mDatas;
    }

    public abstract void convert(BaseViewHolder holder, T t, int index);
}

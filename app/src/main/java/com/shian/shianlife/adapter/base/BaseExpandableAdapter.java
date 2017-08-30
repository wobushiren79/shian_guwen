package com.shian.shianlife.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zm.
 */

public abstract class BaseExpandableAdapter<T, E> extends BaseExpandableListAdapter {
    protected Map<T, List<E>> listData = new HashMap<>();

    protected List<T> groupData = new ArrayList<>();
    protected List<List<E>> itemData = new ArrayList<>();

    protected Context mContext;
    private int groupLayout;
    private int itemLayout;

    public BaseExpandableAdapter(Context context, int groupLayout, int itemLayout) {
        this.mContext = context;
        this.groupLayout = groupLayout;
        this.itemLayout = itemLayout;
        groupData = new ArrayList<>();
        itemData = new ArrayList<>();
    }

    public void setData(Map<T, List<E>> listData) {
        this.listData = listData;
        groupData = new ArrayList<>();
        itemData = new ArrayList<>();
        for (Map.Entry<T, List<E>> entry : listData.entrySet()) {
            groupData.add(entry.getKey());
            itemData.add(entry.getValue());
        }
        this.notifyDataSetChanged();
    }

    public void addData(Map<T, List<E>> listData) {
        listData.putAll(listData);
        for (Map.Entry<T, List<E>> entry : listData.entrySet()) {
            groupData.add(entry.getKey());
            itemData.add(entry.getValue());
        }
        this.notifyDataSetChanged();
    }

    public E getChildData(int groupPosition, int childPosition) {
        return itemData.get(groupPosition).get(childPosition);
    }

    public T getGroupData(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(groupLayout, null);
        }
        setGroupView(groupData.get(groupPosition), convertView, groupPosition, isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(itemLayout, null);
        }
        setItemView(itemData.get(groupPosition).get(childPosition), convertView, groupPosition, childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public abstract void setGroupView(T groupData, View convertView, int groupPosition, boolean isExpanded);

    public abstract void setItemView(E itemData, View convertView, int groupPosition, int childPosition);
}

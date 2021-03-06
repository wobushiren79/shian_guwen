package com.shian.shianlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.CemeteryStructureModel;
import com.shian.shianlife.provide.params.HpCemeteryStructureParams;
import com.shian.shianlife.provide.result.HrGetCemeteryStructure;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CetemeryLocationSelectLayoutView extends CetemeryTextSelectLayoutView {
    long locationId = -1;//填写的上一级ID
    int itemType = -1;//公墓结构项类型，值：0,公墓。1,苑，2,区，3排，4 号
    long parkIdTemp=-1;//园区id，注：仅类型为4墓位号时，才启用园区id
    long thisLocationId = -1;//本级选中ID

    boolean isFirstSet = true;
    OnLocationSelectedListener onLocationSelectedListener;
    List<CemeteryStructureModel> listData = new ArrayList<>();

    public CetemeryLocationSelectLayoutView(Context context) {
        this(context, null);
    }

    public CetemeryLocationSelectLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setListDataClear() {
        listData.clear();
        data.clear();
//        mSPContent.setSelection(0);
        initSp();
        setStateClick(false);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVSelect) {
                getData();
            }
        }
    };

    public long getThisLocationId() {
        return thisLocationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setParkIdTemp(long parkIdTemp) {
        this.parkIdTemp = parkIdTemp;
    }

    public void setLocationIdAndType(long locationId, int itemType) {
        this.locationId = locationId;
        this.itemType = itemType;
        setIsClick();
        setStateClick(false);
    }


    public boolean isFirstSet() {
        return isFirstSet;
    }

    public void setFirstSet(boolean firstSet) {
        isFirstSet = firstSet;
    }

    public void setIsClick() {
        if (itemType == 0) {
            mIVSelect.setOnClickListener(onClickListener);
        } else {
            if (locationId != -1) {
                mIVSelect.setOnClickListener(onClickListener);
            } else {
                return;
            }
        }
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectedListener.onItemSelected(view, i, l, num);
            selectNum = i;
            thisLocationId = listData.get(i).getId();
            onLocationSelectedListener.onItemSelected(view, i, l, num, thisLocationId);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    public void getData() {
        HpCemeteryStructureParams params = new HpCemeteryStructureParams();
        params.setItemId(locationId);
        params.setItemType(itemType);
        //查号的时候传区ID
        if(itemType==4){
            params.setParkIdTemp(parkIdTemp);
        }
        MHttpManagerFactory.getCemeteryManager().getCemeteryStructure(getContext(), params, new HttpResponseHandler<HrGetCemeteryStructure>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryStructure result) {
                if (result.getItems().size() == 0) {
                    setStateClick(false);
                } else {
                    setStateClick(true);
                }

                listData.clear();
                listData = result.getItems();

                List<String> list = new ArrayList<String>();
                for (CemeteryStructureModel data : result.getItems()) {
                    list.add(data.getName());
                }
                data = list;
                //适配器
                initSp();
                mSPContent.performClick();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    public interface OnLocationSelectedListener {
        void onItemSelected(View view, int i, long l, int num, long thisID);
    }


    public void setString(String name, long id) {
        super.setString(name);
        data.add(name);
        CemeteryStructureModel model = new CemeteryStructureModel();
        model.setId(id);
        model.setName(name);
        listData.add(model);
        initSp();
    }

    private void initSp() {
        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_1, data);
        //设置样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        mSPContent.setOnItemSelectedListener(onItemSelectedListener);
        mSPContent.setAdapter(adapter);
    }

    public void setData(List<String> data, int num, onSelectedListener selectedListener, OnLocationSelectedListener onLocationSelectedListener) {
        super.setData(data, num, selectedListener);
        this.onLocationSelectedListener = onLocationSelectedListener;
    }
}

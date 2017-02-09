package com.shian.shianlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.CemeteryStructureModel;
import com.shian.shianlife.provide.params.HpCemeteryStructureParams;
import com.shian.shianlife.provide.result.HrGetCemeteryStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CetemeryLocationSelectLayoutView extends CetemeryTextSelectLayoutView {
    long locationId = -1;
    int itemType = -1;//公墓结构项类型，值：0,公墓。1,苑，2,区，3排，4 号

    public CetemeryLocationSelectLayoutView(Context context) {
        this(context, null);
    }

    public CetemeryLocationSelectLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVSelect) {
                getData();
            }
        }
    };

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setLocationIdAndType(long locationId, int itemType) {
        Log.v("this", "setLocationIdAndType");
        this.locationId = locationId;
        this.itemType = itemType;
        setIsClick();
        setStateClick(false);
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

    public void getData() {

        HpCemeteryStructureParams params = new HpCemeteryStructureParams();
        params.setItemId(locationId);
        params.setItemType(itemType);
        MHttpManagerFactory.getAccountManager().getCemeteryStructure(getContext(), params, new HttpResponseHandler<HrGetCemeteryStructure>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryStructure result) {
                setStateClick(true);
                List<String> list = new ArrayList<String>();
                for (CemeteryStructureModel data : result.getItems()) {
                    list.add(data.getName());
                }
                data =list;
                //适配器
                adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_1, data);
                //设置样式
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                mSPContent.setAdapter(adapter);
                mSPContent.performClick();
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}

package com.shian.shianlife.view.writeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpGetDictSelectParams;
import com.shian.shianlife.provide.result.HrGetDictSelectData;

import okhttp3.Request;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SpinnerViewNormal extends BaseWriteView {
    View view;
    TextView mTVIsImportant;
    TextView mTVTitleName;
    TextView mTVMask;
    TextView mTVMask2;
    Spinner mSpinner;

    private String dictCode;
    public boolean hasData = false;//是否有预加载数据
    private SpinnerCallBack spinnerCallBack;
    private ArrayAdapter<CharSequence> province_adapter;

    public SpinnerViewNormal(Context context) {
        this(context, null);
    }

    public SpinnerViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);

        view = View.inflate(context, R.layout.zhy_write_spinnerview_normal, this);
        initView();
        initData();
    }

    public SpinnerCallBack getSpinnerCallBack() {
        return spinnerCallBack;
    }

    public void setSpinnerCallBack(SpinnerCallBack spinnerCallBack) {
        this.spinnerCallBack = spinnerCallBack;
    }

    private void initView() {
        mTVIsImportant = (TextView) view.findViewById(R.id.tv_important);
        mTVTitleName = (TextView) view.findViewById(R.id.tv_titlename);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mTVMask = (TextView) view.findViewById(R.id.tv_mask);
        mTVMask2 = (TextView) view.findViewById(R.id.tv_mask2);
    }

    private void initData() {
        mTVTitleName.setText(titleName);
        mTVTitleName.setEms(ems);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }
    }


    /**
     * 获取后台字典数据
     *
     * @param dictCode
     */
    private void getSelectData(String dictCode, final String isShowSpinnerData) {
        HpGetDictSelectParams params = new HpGetDictSelectParams();
        params.setDictCode(dictCode);
        MHttpManagerFactory.getCemeteryManager().getDictSelect(getContext(), params, new HttpResponseHandler<HrGetDictSelectData>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetDictSelectData result) {
                if (result.getItems() != null) {
                    String[] array = new String[result.getItems().size()];
                    for (int i = 0; i < result.getItems().size(); i++) {
                        array[i] = result.getItems().get(i).getText();
                    }
                    initSpinner(array);
                    if (isShowSpinnerData != null) {
                        setData(isShowSpinnerData);
                    } else {
                        showSpinner();
                    }
                    mTVMask.setVisibility(GONE);
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }


    /**
     * 有数据时加载字典
     */
    public void setDataDict(String data) {
        if (data != null && dictCode != null)
            getSelectData(dictCode, data);
    }


    /**
     * 初始化数据
     */
    public void initSpinner() {
        mTVMask.setVisibility(VISIBLE);
        mTVMask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerCallBack != null)
                    spinnerCallBack.check(SpinnerViewNormal.this);
            }
        });
    }

    /**
     * 初始化数据
     */
    public void initSpinner(final String dictCode) {
        this.dictCode = dictCode;
        mTVMask.setVisibility(VISIBLE);
        mTVMask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getSelectData(dictCode, null);
            }
        });
    }

    /**
     * 初始化数据
     *
     * @param arrayId
     */
    public void initSpinner(int arrayId) {
        province_adapter = ArrayAdapter.createFromResource(getContext(), arrayId,
                R.layout.textview_spinner);
        initString();
    }

    /**
     * 初始化数据
     *
     * @param array
     */
    public void initSpinner(String[] array) {
        province_adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.textview_spinner, array);
        initString();

    }

    /**
     * 初始化数据
     *
     * @param array
     */
    public void initSpinner(String[] array, String data) {
        initSpinner(array);
        if (data == null) {
            showSpinner();
            return;
        }
        this.hasData = true;
        setData(data);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        province_adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.textview_spinner, new String[]{});
        initString();
        setMaskVis(VISIBLE);
    }

    /**
     * 初始化spinner
     */
    private void initString() {
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(province_adapter);
        mSpinner.setSelection(0);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerCallBack != null) {
                    spinnerCallBack.itemSelected(position, province_adapter.getItem(position).toString(), SpinnerViewNormal.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * 获取数据
     *
     * @return
     */
    public String getData() {
        if (province_adapter == null) {
            return "";
        }
        return province_adapter.getItem(mSpinner.getSelectedItemPosition()).toString();
    }

    /**
     * 获取选项
     *
     * @return
     */
    public int getSelectPosition() {
        return mSpinner.getSelectedItemPosition();
    }

    /**
     * 设置数据
     *
     * @param name
     */
    public void setData(String name) {
        if (name != null)
            for (int i = 0; i < province_adapter.getCount(); i++) {
                if (name.equals(province_adapter.getItem(i))) {
                    mSpinner.setSelection(i);
                    return;
                }
            }
    }

    /**
     * 设置数据
     *
     * @param position
     */
    public void setData(int position) {
        mSpinner.setSelection(position);
    }

    /**
     * 设置遮罩层
     *
     * @param state
     */
    public void setMaskVis(int state) {
        mTVMask.setVisibility(state);
    }

    /**
     * 监听
     */
    public interface SpinnerCallBack {
        void itemSelected(int position, String name, SpinnerViewNormal viewNormal);

        void check(SpinnerViewNormal view);
    }

    /**
     * 展示下拉
     */
    public void showSpinner() {
        mSpinner.performClick();
    }

    public void setDisable(Boolean isDisable) {
        mTVMask2.setVisibility(VISIBLE);
        mTVMask2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}

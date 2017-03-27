package com.shian.shianlife.view.writeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SpinnerViewNormal extends BaseWriteView {
    View view;
    TextView mTVIsImportant;
    TextView mTVTitleName;
    Spinner mSpinner;


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
    }

    public void initSpinner(int arrayId) {
        province_adapter = ArrayAdapter.createFromResource(getContext(), arrayId,
                R.layout.textview_spinner);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(province_adapter);
        mSpinner.setSelection(0);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerCallBack != null) {
                    spinnerCallBack.itemSelected(position, province_adapter.getItem(position).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initData() {
        mTVTitleName.setText(titleName);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }
    }

    public String getData() {
        return province_adapter.getItem(mSpinner.getSelectedItemPosition()).toString();
    }

    public int getSelectPosition() {
        return mSpinner.getSelectedItemPosition();
    }

    public void setData(String name) {
        if (name != null)
            for (int i = 0; i < province_adapter.getCount(); i++) {
                if (name.equals(province_adapter.getItem(i))) {
                    mSpinner.setSelection(i);
                    return;
                }
            }
    }

    public void setData(int position) {
        mSpinner.setSelection(position);
    }

    public interface SpinnerCallBack {
        void itemSelected(int position, String name);
    }
}

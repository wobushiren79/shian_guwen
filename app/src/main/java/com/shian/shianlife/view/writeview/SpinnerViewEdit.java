package com.shian.shianlife.view.writeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/17.
 */

public class SpinnerViewEdit extends BaseWriteView {

    View view;
    TextView mTVIsImportant;
    TextView mTVTitleName;
    Spinner mSpinner;
    EditText mETInput;

    SpinnerEditCallBack spinnerEditCallBack;
    ArrayAdapter<CharSequence> province_adapter;

    public SpinnerViewEdit(Context context) {
        this(context, null);
    }

    public SpinnerViewEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.zhy_write_spinnerview_edit, this);
        initView();
        initData();
    }

    public void setSpinnerEditCallBack(SpinnerEditCallBack spinnerEditCallBack) {
        this.spinnerEditCallBack = spinnerEditCallBack;
    }

    private void initView() {
        mTVIsImportant = (TextView) view.findViewById(R.id.tv_important);
        mTVTitleName = (TextView) view.findViewById(R.id.tv_titlename);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mETInput = (EditText) view.findViewById(R.id.et_input);
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
                if (spinnerEditCallBack != null) {
                    spinnerEditCallBack.itemSelected(position, province_adapter.getItem(position).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initSpinner(String[] array) {
        province_adapter=new ArrayAdapter<CharSequence>(getContext(),R.layout.textview_spinner,array);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(province_adapter);
        mSpinner.setSelection(0);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerEditCallBack != null) {
                    spinnerEditCallBack.itemSelected(position, province_adapter.getItem(position).toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public String getData() {
        if (mSpinner.getSelectedItemPosition() == 0) {
            return mETInput.getText().toString();
        } else {
            if (province_adapter != null) {
                return province_adapter.getItem(mSpinner.getSelectedItemPosition()).toString();
            } else {
                return "";
            }
        }

    }

    public void setData(String name) {
        boolean isChange = false;
        for (int i = 0; i < province_adapter.getCount(); i++) {
            if (name.equals(province_adapter.getItem(i))) {
                mSpinner.setSelection(i);
                isChange = true;
                return;
            }
        }
        if (!isChange) {
            mETInput.setText(name);
        }
    }

    public void setData(int position) {
        mSpinner.setSelection(position);
    }


    private void initData() {
        mTVTitleName.setText(titleName);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }
        if (isLonger) {
            mETInput.setLines(3);
        }
    }

    public interface SpinnerEditCallBack {
        void itemSelected(int position, String name);
    }
}

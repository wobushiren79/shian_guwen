package com.shian.shianlife.view.writeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryStructureParams;
import com.shian.shianlife.provide.result.HrGetCemeteryStructure;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessContract;
import com.shian.shianlife.thisenum.CemeteryLocationEnum;


/**
 * Created by Administrator on 2017/4/13.
 */

public class SpinnerCemeteryLocation extends BaseWriteView {
    View view;
    SpinnerViewNormal mWriteCemeteryName;
    SpinnerViewNormal mWriteLocationGarden;
    SpinnerViewNormal mWriteLocationArea;
    SpinnerViewNormal mWriteLocationRow;
    SpinnerViewNormal mWriteLocationNum;

    HrGetCemeteryStructure resultCemeteryName;
    HrGetCemeteryStructure resultLocationGarden;
    HrGetCemeteryStructure resultLocationArea;
    HrGetCemeteryStructure resultLocationRow;
    HrGetCemeteryStructure resultLocationNum;

    CallBack callBack;


    public SpinnerCemeteryLocation(Context context) {
        this(context, null);
    }

    public SpinnerCemeteryLocation(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.zhy_write_spinnercemeterylocation, this);
        initView();
        initData();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void initView() {
        mWriteCemeteryName = (SpinnerViewNormal) view.findViewById(R.id.write_cemetery_name);
        mWriteLocationGarden = (SpinnerViewNormal) view.findViewById(R.id.write_location_garden);
        mWriteLocationArea = (SpinnerViewNormal) view.findViewById(R.id.write_location_area);
        mWriteLocationRow = (SpinnerViewNormal) view.findViewById(R.id.write_location_row);
        mWriteLocationNum = (SpinnerViewNormal) view.findViewById(R.id.write_location_num);
    }

    private void initData() {
        mWriteCemeteryName.initSpinner();
        mWriteCemeteryName.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationGarden.initSpinner();
        mWriteLocationGarden.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationArea.initSpinner();
        mWriteLocationArea.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationRow.initSpinner();
        mWriteLocationRow.setSpinnerCallBack(spinnerCallBack);
        mWriteLocationNum.initSpinner();
        mWriteLocationNum.setSpinnerCallBack(spinnerCallBack);
    }

    SpinnerViewNormal.SpinnerCallBack spinnerCallBack = new SpinnerViewNormal.SpinnerCallBack() {


        @Override
        public void itemSelected(int position, String name, SpinnerViewNormal viewNormal) {
            if (viewNormal.hasData) {
                viewNormal.hasData = false;
                return;
            }

            if (viewNormal == mWriteLocationNum) {
                if (callBack != null)
                    callBack.changePrice(resultLocationNum.getItems().get(position).getTombSalePrice() + "");
                return;
            }

            mWriteLocationNum.clearData();
            resultLocationNum = null;
            numId = 0;
            numName = null;
            if (viewNormal == mWriteLocationRow)
                return;
            mWriteLocationRow.clearData();
            resultLocationRow = null;
            rowId = null;
            numId = 0;
            rowName = null;
            if (viewNormal == mWriteLocationArea)
                return;
            mWriteLocationArea.clearData();
            resultLocationArea = null;
            areaId = 0;
            areaName = null;
            if (viewNormal == mWriteLocationGarden)
                return;
            mWriteLocationGarden.clearData();
            resultLocationGarden = null;
            gardenId = 0;
            gardenName = null;
            if (viewNormal == mWriteCemeteryName)
                return;


        }

        @Override
        public void check(SpinnerViewNormal view) {
            long locationId = 0;
            long parkIdTemp = 0;
            if (view == mWriteCemeteryName) {
                getDataInfo(CemeteryLocationEnum.CEMETERYNAME.getCode(), locationId, parkIdTemp, mWriteCemeteryName);
            } else if (view == mWriteLocationGarden) {
                if (resultCemeteryName == null) {
                    ToastUtils.show(getContext(), "请先选择公墓");
                    return;
                }
                locationId = resultCemeteryName.getItems().get(mWriteCemeteryName.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONGARDEN.getCode(), locationId, parkIdTemp, mWriteLocationGarden);
            } else if (view == mWriteLocationArea) {
                if (resultLocationGarden == null) {
                    ToastUtils.show(getContext(), "请先选择苑");
                    return;
                }
                locationId = resultLocationGarden.getItems().get(mWriteLocationGarden.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONAREA.getCode(), locationId, parkIdTemp, mWriteLocationArea);
            } else if (view == mWriteLocationRow) {
                if (resultLocationArea == null) {
                    ToastUtils.show(getContext(), "请先选择区");
                    return;
                }
                locationId = resultLocationArea.getItems().get(mWriteLocationArea.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONROW.getCode(), locationId, parkIdTemp, mWriteLocationRow);
            } else if (view == mWriteLocationNum) {
                if (resultLocationRow == null) {
                    ToastUtils.show(getContext(), "请先选择排");
                    return;
                }
                locationId = resultLocationRow.getItems().get(mWriteLocationRow.getSelectPosition()).getId();
                parkIdTemp = resultLocationArea.getItems().get(mWriteLocationArea.getSelectPosition()).getId();
                getDataInfo(CemeteryLocationEnum.LOCATIONNUM.getCode(), locationId, parkIdTemp, mWriteLocationNum);
            }
        }


    };

    /**
     * 获取网络数据
     *
     * @param itemType
     * @param locationId
     * @param parkIdTemp
     * @param spinnerViewNormal
     */
    private void getDataInfo(int itemType, long locationId, long parkIdTemp, final SpinnerViewNormal spinnerViewNormal) {
        HpCemeteryStructureParams params = new HpCemeteryStructureParams();
        params.setItemId(locationId);
        params.setItemType(itemType);
        //查号的时候传区ID
        if (itemType == CemeteryLocationEnum.LOCATIONNUM.getCode()) {
            params.setParkIdTemp(parkIdTemp);
        }
        MHttpManagerFactory.getAccountManager().getCemeteryStructure(getContext(), params, new HttpResponseHandler<HrGetCemeteryStructure>() {


            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryStructure result) {
                if (result == null || result.getItems().size() == 0) {
                    ToastUtils.show(getContext(), "没有找到数据");
                    return;
                }
                String location = null;
                if (spinnerViewNormal == mWriteCemeteryName) {
                    resultCemeteryName = result;
                    location = cemeteryName;
                } else if (spinnerViewNormal == mWriteLocationGarden) {
                    resultLocationGarden = result;
                    location = gardenName;
                } else if (spinnerViewNormal == mWriteLocationArea) {
                    resultLocationArea = result;
                    location = areaName;
                } else if (spinnerViewNormal == mWriteLocationRow) {
                    resultLocationRow = result;
                    location = rowName;
                } else if (spinnerViewNormal == mWriteLocationNum) {
                    resultLocationNum = result;
                    location = numName;
                }

                String[] data = new String[result.getItems().size()];
                for (int i = 0; i < result.getItems().size(); i++) {
                    data[i] = result.getItems().get(i).getName();
                }
                spinnerViewNormal.initSpinner(data, location);
                spinnerViewNormal.setMaskVis(View.GONE);
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    long cemeteryId = 0;
    long gardenId = 0;
    long areaId = 0;
    String rowId = null;
    long numId = 0;
    String cemeteryName;
    String gardenName;
    String areaName;
    String rowName;
    String numName;

    /**
     * 获取地址数据
     */
    public long getData(int type) {
        long dataID = 0;
        try {
            if (type == CemeteryLocationEnum.CEMETERYNAME.getCode()) {
                cemeteryId = resultCemeteryName.getItems().get(mWriteCemeteryName.getSelectPosition()).getId();
            } else if (type == CemeteryLocationEnum.LOCATIONGARDEN.getCode()) {
                gardenId = resultLocationGarden.getItems().get(mWriteLocationGarden.getSelectPosition()).getId();
            } else if (type == CemeteryLocationEnum.LOCATIONAREA.getCode()) {
                areaId = resultLocationArea.getItems().get(mWriteLocationArea.getSelectPosition()).getId();
            } else if (type == CemeteryLocationEnum.LOCATIONROW.getCode()) {
                rowId = resultLocationRow.getItems().get(mWriteLocationRow.getSelectPosition()).getId() + "";
            } else if (type == CemeteryLocationEnum.LOCATIONNUM.getCode()) {
                numId = resultLocationNum.getItems().get(mWriteLocationNum.getSelectPosition()).getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (type == CemeteryLocationEnum.CEMETERYNAME.getCode()) {
                dataID = cemeteryId;
            } else if (type == CemeteryLocationEnum.LOCATIONGARDEN.getCode()) {
                dataID = gardenId;
            } else if (type == CemeteryLocationEnum.LOCATIONAREA.getCode()) {
                dataID = areaId;
            } else if (type == CemeteryLocationEnum.LOCATIONROW.getCode()) {
                dataID = Long.valueOf(rowId);
            } else if (type == CemeteryLocationEnum.LOCATIONNUM.getCode()) {
                dataID = numId;
            }
        }
        return dataID;
    }

    public void setData(HrGetCemeteryTalkSuccessContract data) {
        if (checkData(data)) return;

        cemeteryId = data.getCemeteryId();
        gardenId = data.getTombId();
        areaId = data.getParkId();
        rowId = data.getRowNumber();
        numId = data.getTombPositionId();

        cemeteryName = data.getCemeteryName();
        gardenName = data.getTombName();
        areaName = data.getParkName();
        rowName = data.getRowNumber() + "";
        numName = data.getNum() + "";

        getDataInfo(CemeteryLocationEnum.CEMETERYNAME.getCode(), 0, 0, mWriteCemeteryName);
        getDataInfo(CemeteryLocationEnum.LOCATIONGARDEN.getCode(), cemeteryId, 0, mWriteLocationGarden);
        getDataInfo(CemeteryLocationEnum.LOCATIONAREA.getCode(), gardenId, 0, mWriteLocationArea);
        getDataInfo(CemeteryLocationEnum.LOCATIONROW.getCode(), areaId, 0, mWriteLocationRow);
        getDataInfo(CemeteryLocationEnum.LOCATIONNUM.getCode(), Long.valueOf(rowId), areaId, mWriteLocationNum);
    }

    public void setData(HrGetCemeteryTalkSuccessContract data, boolean isInit) {
        if (checkData(data)) return;
        cemeteryName = data.getCemeteryName();
        gardenName = data.getTombName();
        areaName = data.getParkName();
        rowName = data.getRowNumber() + "";
        numName = data.getNum() + "";

        mWriteCemeteryName.initSpinner(new String[]{cemeteryName}, cemeteryName);
        mWriteLocationGarden.initSpinner(new String[]{gardenName}, gardenName);
        mWriteLocationArea.initSpinner(new String[]{areaName}, areaName);
        mWriteLocationRow.initSpinner(new String[]{rowName}, rowName);
        mWriteLocationNum.initSpinner(new String[]{numName}, numName);
    }

    private boolean checkData(HrGetCemeteryTalkSuccessContract data) {
        if (data.getCemeteryName() == null)
            return true;
        if (data.getTombName() == null)
            return true;
        if (data.getParkName() == null)
            return true;
        if (data.getRowNumber() == null)
            return true;
        if (data.getNum() == null)
            return true;
        return false;
    }


    public interface CallBack {
        void changePrice(String price);
    }
}

package com.shian.shianlife.mvp.pay.bean;


import com.google.gson.annotations.SerializedName;

/**
 * Created by zm.
 */
public class WeChatPrePayResultBean {


    /**
     * timeStamp : 1506569408
     * appId : null
     * nonceStr : null
     * package : prepay_id=
     * signType : MD5
     * paySign : 8D79B840FC2682E57FB5652637680954
     */

    private Long timeStamp;
    private String appId;
    private String nonceStr;
    @SerializedName("package")
    private String prepayId;
    private String signType;
    private String paySign;
    private Result result;//返回
    private String out_trade_no;//订单编号
    private String mch_id;//商户号

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }


    public static class Result {

        /**
         * return_code : FAIL
         * return_msg : 商户号该产品权限未开通，请前往商户平台>产品中心检查后重试
         */

        private String return_code;
        private String return_msg;

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }
    }
}

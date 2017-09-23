package com.shian.shianlife.mvp.userinfo.bean;

import java.util.List;

/**
 * Created by zm.
 */

public class UserInfoIntegralListResultBean {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPage;//	int	总页数
    private Integer total;// int 总记录数
    private List<Content> content;//  list 数据集

    public static class Content {
        private String created_at;//创建时间
        private Long credit_amount;//	long	本次积分
        private Long credit_before;//long	原积分
        private Long credit_after;//long	现积分
        private String ope_type;//string	操作类型
        private String trans_type;//string	业务类型
        private String conform;//	string	确认状态
        private String seq_no;//string	流水号
        private String result;//string	交易结果
        private String relate_tran;//	string	交易信息

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Long getCredit_amount() {
            return credit_amount;
        }

        public void setCredit_amount(Long credit_amount) {
            this.credit_amount = credit_amount;
        }

        public Long getCredit_before() {
            return credit_before;
        }

        public void setCredit_before(Long credit_before) {
            this.credit_before = credit_before;
        }

        public Long getCredit_after() {
            return credit_after;
        }

        public void setCredit_after(Long credit_after) {
            this.credit_after = credit_after;
        }

        public String getOpe_type() {
            return ope_type;
        }

        public void setOpe_type(String ope_type) {
            this.ope_type = ope_type;
        }

        public String getTrans_type() {
            return trans_type;
        }

        public void setTrans_type(String trans_type) {
            this.trans_type = trans_type;
        }

        public String getConform() {
            return conform;
        }

        public void setConform(String conform) {
            this.conform = conform;
        }

        public String getSeq_no() {
            return seq_no;
        }

        public void setSeq_no(String seq_no) {
            this.seq_no = seq_no;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getRelate_tran() {
            return relate_tran;
        }

        public void setRelate_tran(String relate_tran) {
            this.relate_tran = relate_tran;
        }
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}

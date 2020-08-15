package com.fujiang.weiji.entity.runoob;

import java.math.BigDecimal;
import java.util.Date;

public class RunoobTbl {
    private Integer runoobId;

    private String runoobTitle;

    private String runoobAuthor;

    private Date submissionDate;

    private Float qweqwe;

    private Double sdfsdfsdf;

    private BigDecimal retert;

    public Integer getRunoobId() {
        return runoobId;
    }

    public void setRunoobId(Integer runoobId) {
        this.runoobId = runoobId;
    }

    public String getRunoobTitle() {
        return runoobTitle;
    }

    public void setRunoobTitle(String runoobTitle) {
        this.runoobTitle = runoobTitle == null ? null : runoobTitle.trim();
    }

    public String getRunoobAuthor() {
        return runoobAuthor;
    }

    public void setRunoobAuthor(String runoobAuthor) {
        this.runoobAuthor = runoobAuthor == null ? null : runoobAuthor.trim();
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Float getQweqwe() {
        return qweqwe;
    }

    public void setQweqwe(Float qweqwe) {
        this.qweqwe = qweqwe;
    }

    public Double getSdfsdfsdf() {
        return sdfsdfsdf;
    }

    public void setSdfsdfsdf(Double sdfsdfsdf) {
        this.sdfsdfsdf = sdfsdfsdf;
    }

    public BigDecimal getRetert() {
        return retert;
    }

    public void setRetert(BigDecimal retert) {
        this.retert = retert;
    }
}
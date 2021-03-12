package com.fujiang.weiji.entity.runoob;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RunoobTbl {
    private Integer runoobId;

    private String runoobTitle;

    private String runoobAuthor;

    private Date submissionDate;

    private Float qweqwe;

    private Double sdfsdfsdf;

    private BigDecimal retert;

}
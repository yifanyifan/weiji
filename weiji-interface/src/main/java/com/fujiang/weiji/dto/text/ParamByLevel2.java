package com.fujiang.weiji.dto.text;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamByLevel2 implements Serializable {
    /**
     * 站点URL
     */
    private String webUrl;
    /**
     * 1层
     */
    private String l1;
    /**
     * 2层
     */
    private String l2;
    /**
     * 标题字段
     */
    private String titleField;
    /**
     * 时间字段
     */
    private String timeField;
    /**
     * 内容字段
     */
    private String contextField;

}

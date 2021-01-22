package com.fujiang.weiji.dto.text;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamByRegular implements Serializable {
    /**
     * 站点URL
     */
    private String webUrl;
    /**
     * 内容页URL组装【部分网站访问内容页面需要】
     */
    String mainUrl;
    /**
     * 列表Start
     */
    String listStart;
    /**
     * 列表End
     */
    String listEnd;
    /**
     * 内容URL Start
     */
    String urlStart;
    /**
     * 内容URL End
     */
    String urlEnd;
    /**
     * 标题 Start
     */
    String titleStart;
    /**
     * 标题 End
     */
    String titleEnd;
    /**
     * 时间 Start
     */
    String timeStart;
    /**
     * 时间 End
     */
    String timeEnd;
    /**
     * 来源 Start
     */
    String sourceStart;
    /**
     * 来源 End
     */
    String sourceEnd;
    /**
     * 内容 Start
     */
    String contextStart;
    /**
     * 内容 End
     */
    String contextEnd;


}

package com.fujiang.weiji.dto.text;

public class ParamByRegular {
    /**
     * 站点URL
     */
    private String webListUrl;

    //页面前缀【部分网站打开网页需要加】
    String mainUrl = "";
    //列表范围class
    String listClass = "newsTr0";
    //url规则开始
    String ruleStart = "<a href=\"";
    //url规则结束
    String ruleEnd = "\" target=\"_blank\">";

    String idOrClass;
}

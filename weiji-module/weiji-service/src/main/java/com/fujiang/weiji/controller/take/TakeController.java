package com.fujiang.weiji.controller.take;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TakeController {
    /**
     * 获取抓取网站集合
     * 1. 网站名称
     * 2. 网站抓取列表地址
     */
    public void getWebSiteList() {
        List<String> str = new ArrayList<String>();
        String webSite = "https://www.lianshu.com/index/main";
    }

    public static void main(String[] args) {
        List<String> str = new ArrayList<String>();
        String webSite = "https://www.lianshu.com/index/main";
        getWebSiteRule(webSite);
    }

    /**
     * 获取抓取网站规则
     * 1. 通过列表 获取 网址
     * 2. 通过网络 抓去 网址内容
     */
    public static void getWebSiteRule(String web) {
        try {
            //1. 分页开始
            String pageStart = "<!-- 列表循环开始 -->";
            //2. 分布结束
            String pageEnd = "<!-- 列表循环结束 -->";
            //3. 页面网址开始
            String urlStart = "<a target=\"_blank\" class=\"\" href=\"";
            //4. 页面网址结束
            String urlEnd = "\">";
            //5. 页面网址前缀
            String webPrefix = "https://www.lianshu.com";

            GetJson getJson = new GetJson();
            getJson.getHttpJson(web, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 抓取数据
     * 1. 标题
     * 2. 日期
     * 3. 作者
     * 4. 内容
     */
    public void getWebSiteData() {

    }
}

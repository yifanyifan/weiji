package com.fujiang.weiji.service.take;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZzbdsScheduling {
    public static void main(String[] args) {
        String web = "http://finance.eastmoney.com/a/cgjjj.html";
        getWebListData(web);
    }


    /**
     * 获取抓取网站规则
     * 1. 通过列表 获取 网址
     */
    private static void getWebListData(String web) {
        try {
            //解析网页(Jsoup返回浏览器Document对象，可以使用Js的方法)
            Document document = Jsoup.parse(new URL(web), 60000);//设置60s超时

            //页面前缀【部分网站打开网页需要加】
            String mainUrl = "";
            //列表范围class
            String listClass = "newsTr0";
            //url规则开始
            String ruleStart = "<a href=\"";
            //url规则结束
            String ruleEnd = "\" target=\"_blank\">";
            //扫描范围列表
            //Elements elements = document.getElementsByClass(listClass);
            Element elements = document.getElementById(listClass);
            String listString = elements.toString();
            //规则
            Pattern pattern = Pattern.compile("(?<=" + ruleStart + ").*?(?=(" + ruleEnd + "|$))");
            Matcher m = pattern.matcher(listString);
            while (m.find()) {
                System.out.println(mainUrl + m.group());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

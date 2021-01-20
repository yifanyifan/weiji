package com.fujiang.weiji.controller.take;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fujiang.weiji.controller.take.JsoupUtils.replaceChar;

@Controller
public class TakeController {
    /**
     * 获取抓取网站集合
     * 1. 网站名称
     * 2. 网站抓取列表地址
     */
    public static void main(String[] args) {
        String jscj = "https://api.jinse.com/noah/v2/lives?limit=20&reading=false&source=web&flag=up&id=220520&category=0";
        getWebPageDataByPOST(jscj);
    }

    public static void getWebPageDataByPOST(String web) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            JSONObject map1 = restTemplate.getForObject(web, JSONObject.class);

            String ruleStr = "list,lives";
            List<String> ruleList = new ArrayList<String>(Arrays.asList(ruleStr.split(",")));

            JSONArray jsonArray1 = map1.getJSONArray(ruleList.get(0));
            for (int i = 0; i < jsonArray1.size(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                JSONArray jsonArray2 = jsonObject1.getJSONArray(ruleList.get(1));
                for (int j = 0; j < jsonArray2.size(); j++) {
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                    System.out.println(jsonObject2);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取抓取网站规则
     * 1. 通过列表 获取 网址
     */
    private static void getWebListData(String web) {
        try {
            //解析网页(Jsoup返回浏览器Document对象，可以使用Js的方法)
            Document document = Jsoup.parse(new URL(web), 60000);//设置60s超时

            //主前缀
            String mainUrl = "https://www.lianshu.com";
            //列表范围class
            String listClass = "am-article-box";
            //url规则开始
            String ruleStart = "<a target=\"_blank\" class=\"\" href=\"";
            //url规则结束
            String ruleEnd = "\">";
            //扫描范围列表
            Elements elements = document.getElementsByClass(listClass);
            String listString = elements.toString();
            //规则
            Pattern pattern = Pattern.compile("(?<=" + ruleStart + ").*?(?=(" + ruleEnd + "|$))");
            Matcher m = pattern.matcher(listString);
            while (m.find()) {
                System.out.println(mainUrl + replaceChar(m.group()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

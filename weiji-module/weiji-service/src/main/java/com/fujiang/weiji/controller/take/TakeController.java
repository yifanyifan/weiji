package com.fujiang.weiji.controller.take;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    public static void main1(String[] args) {
        //获取请求（需联网）
        String url = "https://www.lianshu.com/index/main";
        getWebListData(url);
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

    public static void main(String[] args) {
        //获取请求（需联网）
        String url = "https://www.lianshu.com/assets/blog.html?id=21718&topicid=399";
        getWebPageDataByPOST(url);
    }

    /**
     * 抓取数据
     * 1. 标题
     * 2. 日期
     * 3. 作者
     * 4. 内容
     */
    public static void getWebPageDataByPOST(String web) {
        try {
            String postUrl = "https://www.lianshu.com/article/detail";
            String param = "articleId=21727";
            Map<String,String> map = new HashMap<String,String>();
            map.put("articleId", "21727");

            RestTemplate restTemplate = new RestTemplate();
            String map1 = restTemplate.postForObject(postUrl, map, String.class);
            System.out.println(map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

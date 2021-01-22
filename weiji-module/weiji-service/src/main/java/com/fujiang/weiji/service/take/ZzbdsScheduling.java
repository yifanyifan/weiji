package com.fujiang.weiji.service.take;

import com.fujiang.weiji.dto.text.ParamByRegular;
import com.fujiang.weiji.entity.text.TextInfo;
import com.fujiang.weiji.enumeration.ModuleEnum;
import com.fujiang.weiji.mapper.text.TextInfoMapper;
import com.fujiang.weiji.utils.MD5Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@EnableScheduling
public class ZzbdsScheduling {
    @Autowired
    private TextInfoMapper textInfoMapper;

    /**
     * 网站抓取列表地址
     */
    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void main2() {
        ParamByRegular paramByRegular = new ParamByRegular();
        paramByRegular.setWebUrl("http://finance.eastmoney.com/a/cgjjj.html");
        paramByRegular.setMainUrl("");
        paramByRegular.setListStart("<ul id=\"newsListContent\">");
        paramByRegular.setListEnd("<div class=\"pagerbox\">");
        paramByRegular.setUrlStart("<a href=\"");
        paramByRegular.setUrlEnd("\" target=\"_blank\">");
        paramByRegular.setTitleStart("<h1>");
        paramByRegular.setTitleEnd("</h1>");
        paramByRegular.setTimeStart("<div class=\"time\">");
        paramByRegular.setTimeEnd("</div>");
        paramByRegular.setSourceStart("<div class=\"source data-source\" data-source=\"");
        paramByRegular.setSourceEnd("\">");
        paramByRegular.setContextStart("<!--文章主体-->");
        paramByRegular.setContextEnd("<!--责任编辑-->");

        getWebListData(paramByRegular);
    }


    /**
     * 获取抓取网站规则
     * 1. 通过列表 获取 网址
     */
    private void getWebListData(ParamByRegular paramByRegular) {
        try {
            //解析网页(Jsoup返回浏览器Document对象，可以使用Js的方法)
            Document document = Jsoup.parse(new URL(paramByRegular.getWebUrl()), 60000);//设置60s超时
            String documentStr = document.toString();

            //页面前缀【部分网站打开网页需要加】
            Pattern listPattern = Pattern.compile("(?<=" + paramByRegular.getListStart() + ")[\\s\\S]*?(?=(" + paramByRegular.getListEnd() + "|$))");
            Matcher listMatcher = listPattern.matcher(documentStr);
            while (listMatcher.find()) {
                String list = listMatcher.group();

                //规则
                Pattern urlPattern = Pattern.compile("(?<=" + paramByRegular.getUrlStart() + ")[\\s\\S]*?(?=(" + paramByRegular.getUrlEnd() + "|$))");
                Matcher urlMatcher = urlPattern.matcher(list);
                while (urlMatcher.find()) {
                    String pageUrl = paramByRegular.getMainUrl() + urlMatcher.group();
                    savePageMsg(pageUrl, paramByRegular);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savePageMsg(String pageUrl, ParamByRegular paramByRegular) throws Exception {
        //解析网页(Jsoup返回浏览器Document对象，可以使用Js的方法)
        Document documentPage = Jsoup.parse(new URL(pageUrl), 60000);//设置60s超时
        String documentPageStr = documentPage.toString();

        //页面前缀【部分网站打开网页需要加】

        String title = method(paramByRegular.getTitleStart(), paramByRegular.getTitleEnd(), documentPageStr);
        String time = method(paramByRegular.getTimeStart(), paramByRegular.getTimeEnd(), documentPageStr);
        String source = method(paramByRegular.getSourceStart(), paramByRegular.getSourceEnd(), documentPageStr);
        String context = method(paramByRegular.getContextStart(), paramByRegular.getContextEnd(), documentPageStr);

        String md5 = MD5Utils.md5(title);
        Integer textInfoCount = textInfoMapper.getCountByTitleMd5(md5);
        if (textInfoCount == 0) {
            TextInfo textInfo = new TextInfo();
            textInfo.setTitle(title.trim());
            textInfo.setTitleMd5(md5);
            textInfo.setSource(source);
            textInfo.setTime(time.trim());
            textInfo.setContext(context.trim());
            textInfo.setUrl(pageUrl);
            textInfo.setModule(ModuleEnum.FINANEW.getCode());
            textInfoMapper.insert(textInfo);
        }
        Thread.sleep(50);
    }

    public String method(String start, String end, String documentStr) {
        Pattern pattern = Pattern.compile("(?<=" + start + ")[\\s\\S]*?(?=(" + end + "|$))");
        Matcher matcher = pattern.matcher(documentStr);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
}

package com.fujiang.weiji.service.take;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fujiang.weiji.entity.module.ModuleInfo;
import com.fujiang.weiji.entity.module.ModuleScanRule;
import com.fujiang.weiji.entity.module.ScanRuleLevel;
import com.fujiang.weiji.entity.module.ScanRuleRegular;
import com.fujiang.weiji.entity.text.TextInfo;
import com.fujiang.weiji.enumeration.ModuleEnum;
import com.fujiang.weiji.mapper.module.ModuleInfoMapper;
import com.fujiang.weiji.mapper.module.ModuleScanRuleMapper;
import com.fujiang.weiji.mapper.module.ScanRuleLevelMapper;
import com.fujiang.weiji.mapper.module.ScanRuleRegularMapper;
import com.fujiang.weiji.mapper.text.TextInfoMapper;
import com.fujiang.weiji.utils.MD5Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class mainScheduling {
    @Autowired
    private ModuleInfoMapper moduleInfoMapper;
    @Autowired
    private ModuleScanRuleMapper moduleScanRuleMapper;
    @Autowired
    private ScanRuleLevelMapper scanRuleLevelMapper;
    @Autowired
    private ScanRuleRegularMapper scanRuleRegularMapper;
    @Autowired
    private TextInfoMapper textInfoMapper;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 网站抓取列表地址
     */
    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void mainScheduling() {
        //1. 获取模块
        List<ModuleInfo> moduleInfoList = moduleInfoMapper.selectList(null);
        List<String> moduleInfoIdList = moduleInfoList.stream().map(m -> m.getId()).collect(Collectors.toList());
        List<ModuleScanRule> moduleScanRuleList = moduleScanRuleMapper.selectBatchIds(moduleInfoIdList);
        Map<String, List<ModuleScanRule>> moduleScanRuleMap = moduleScanRuleList.stream().collect(Collectors.groupingBy(moduleScanRule -> moduleScanRule.getModuleId()));

        for (ModuleInfo moduleInfo : moduleInfoList) {
            //2. 获取模块采集站点集
            List<ModuleScanRule> moduleList = moduleScanRuleMap.get(moduleInfo.getId());
            for (ModuleScanRule moduleScanRule : moduleList) {
                // 3.1 获取站点规则
                String scanRuleName = moduleScanRule.getScanRuleName();
                String scanRuleId = moduleScanRule.getScanRuleId();
                String webUrl = moduleScanRule.getWebUrl();
                if ("scan_rule_level".equals(scanRuleName)) {
                    ScanRuleLevel scanRuleLevel = scanRuleLevelMapper.selectById(scanRuleId);
                    //3.2 站点下的内容站点列表
                    scanRuleLevel.setWebUrl(webUrl);
                    getDataByLevel2(scanRuleLevel);
                } else if ("scan_rule_regular".equals(scanRuleName)) {
                    ScanRuleRegular scanRuleRegular = scanRuleRegularMapper.selectById(scanRuleId);
                    //3.2 站点下的内容站点列表
                    scanRuleRegular.setWebUrl(webUrl);
                    getDataByRegular(scanRuleRegular);
                }


            }
        }

        //4. 采集内容页面
        System.out.println("111");
    }


    /**
     * 2层信息获取接口
     *
     * @param scanRuleLevel
     */
    public void getDataByLevel2(ScanRuleLevel scanRuleLevel) {
        try {
            JSONObject msgAll = restTemplate.getForObject(scanRuleLevel.getWebUrl(), JSONObject.class);

            //分层获取信息
            JSONArray jsonArray1 = msgAll.getJSONArray(scanRuleLevel.getL1());
            for (int i = 0; i < jsonArray1.size(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                JSONArray jsonArray2 = jsonObject1.getJSONArray(scanRuleLevel.getL2());
                for (int j = 0; j < jsonArray2.size(); j++) {
                    //参数信息
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);

                    String title = jsonObject2.getString(scanRuleLevel.getTitleField());
                    String titleMd5 = MD5Utils.md5(title);
                    Integer textInfoCount = textInfoMapper.getCountByTitleMd5(titleMd5);
                    if (textInfoCount == 0) {
                        TextInfo textInfo = new TextInfo();
                        textInfo.setTitle(title);
                        textInfo.setTitleMd5(titleMd5);
                        textInfo.setSource("-");
                        textInfo.setTime(jsonObject2.getString(scanRuleLevel.getTimeField()));
                        textInfo.setContext(jsonObject2.getString(scanRuleLevel.getContextField()));
                        textInfo.setUrl(scanRuleLevel.getWebUrl());
                        textInfo.setModuleId(ModuleEnum.FAST.getCode());
                        textInfoMapper.insert(textInfo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 规则抓取网站
     *
     * @param scanRuleRegular
     */
    private void getDataByRegular(ScanRuleRegular scanRuleRegular) {
        try {
            //解析网页(Jsoup返回浏览器Document对象，可以使用Js的方法)
            Document document = Jsoup.parse(new URL(scanRuleRegular.getWebUrl()), 60000);//设置60s超时
            String documentStr = document.toString();

            //页面前缀【部分网站打开网页需要加】
            Pattern listPattern = Pattern.compile("(?<=" + scanRuleRegular.getListStart() + ")[\\s\\S]*?(?=(" + scanRuleRegular.getListEnd() + "|$))");
            Matcher listMatcher = listPattern.matcher(documentStr);
            while (listMatcher.find()) {
                String list = listMatcher.group();

                //规则
                Pattern urlPattern = Pattern.compile("(?<=" + scanRuleRegular.getUrlStart() + ")[\\s\\S]*?(?=(" + scanRuleRegular.getUrlEnd() + "|$))");
                Matcher urlMatcher = urlPattern.matcher(list);
                while (urlMatcher.find()) {
                    String pageUrl = scanRuleRegular.getMainUrl() + urlMatcher.group();
                    savePageMsg(pageUrl, scanRuleRegular);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savePageMsg(String pageUrl, ScanRuleRegular scanRuleRegular) throws Exception {
        //解析网页(Jsoup返回浏览器Document对象，可以使用Js的方法)
        Document documentPage = Jsoup.parse(new URL(pageUrl), 60000);//设置60s超时
        String documentPageStr = documentPage.toString();

        //页面前缀【部分网站打开网页需要加】

        String title = method(scanRuleRegular.getTitleStart(), scanRuleRegular.getTitleEnd(), documentPageStr);
        String time = method(scanRuleRegular.getTimeStart(), scanRuleRegular.getTimeEnd(), documentPageStr);
        String source = method(scanRuleRegular.getSourceStart(), scanRuleRegular.getSourceEnd(), documentPageStr);
        String context = method(scanRuleRegular.getContextStart(), scanRuleRegular.getContextEnd(), documentPageStr);

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
            textInfo.setModuleId(ModuleEnum.FINANEW.getCode());
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

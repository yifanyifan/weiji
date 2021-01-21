package com.fujiang.weiji.service.take;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fujiang.weiji.entity.text.TextInfo;
import com.fujiang.weiji.mapper.text.TextInfoMapper;
import com.fujiang.weiji.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@EnableScheduling
public class TakeScheduling {
    @Autowired
    private TextInfoMapper textInfoMapper;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 网站抓取列表地址
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void main1() {
        String jscj = "https://api.jinse.com/noah/v2/lives?limit=20&reading=false&source=web&flag=up&id=220520&category=0";
        getWebPageDataByPOST2(jscj);
    }

    /**
     * 2层信息获取接口
     *
     * @param web
     */
    public void getWebPageDataByPOST2(String web) {
        try {
            JSONObject msgAll = restTemplate.getForObject(web, JSONObject.class);

            List<TextInfo> textInfoList = new ArrayList<TextInfo>();

            //2层规则+Md5标识
            List<String> ruleList = new ArrayList<String>(Arrays.asList("list,lives,content_prefix".split(",")));
            //分层获取信息
            JSONArray jsonArray1 = msgAll.getJSONArray(ruleList.get(0));

            for (int i = 0; i < jsonArray1.size(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                JSONArray jsonArray2 = jsonObject1.getJSONArray(ruleList.get(1));
                for (int j = 0; j < jsonArray2.size(); j++) {
                    //参数信息
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);

                    String title = jsonObject2.getString(ruleList.get(2));
                    String titleMd5 = MD5Utils.md5(title);
                    Integer textInfoCount = textInfoMapper.getCountByTitleMd5(titleMd5);
                    if (textInfoCount == 0) {
                        TextInfo textInfo = new TextInfo();

                        textInfo.setTitle(title);
                        textInfo.setTitleMd5(titleMd5);
                        textInfo.setContext(jsonObject2.getString("content"));

                        textInfoMapper.insert(textInfo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

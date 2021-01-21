package com.fujiang.weiji.service.take;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fujiang.weiji.dto.text.ParamByLevel2;
import com.fujiang.weiji.entity.text.TextInfo;
import com.fujiang.weiji.mapper.text.TextInfoMapper;
import com.fujiang.weiji.utils.DateUtils;
import com.fujiang.weiji.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        ParamByLevel2 paramByLevel2 = new ParamByLevel2();
        paramByLevel2.setWebUrl("https://api.jinse.com/noah/v2/lives?limit=20&reading=false&source=web&flag=up&id=220520&category=0");
        paramByLevel2.setL1("list");
        paramByLevel2.setL2("lives");
        paramByLevel2.setTitleField("content_prefix");
        paramByLevel2.setTimeField("created_at");
        paramByLevel2.setContextField("content");

        getDataByLevel2(paramByLevel2);
    }

    /**
     * 2层信息获取接口
     *
     * @param paramByLevel2
     */
    public void getDataByLevel2(ParamByLevel2 paramByLevel2) {
        try {
            JSONObject msgAll = restTemplate.getForObject(paramByLevel2.getWebUrl(), JSONObject.class);

            //分层获取信息
            JSONArray jsonArray1 = msgAll.getJSONArray(paramByLevel2.getL1());
            for (int i = 0; i < jsonArray1.size(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                JSONArray jsonArray2 = jsonObject1.getJSONArray(paramByLevel2.getL2());
                for (int j = 0; j < jsonArray2.size(); j++) {
                    //参数信息
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);

                    String title = jsonObject2.getString(paramByLevel2.getTitleField());
                    String titleMd5 = MD5Utils.md5(title);
                    Integer textInfoCount = textInfoMapper.getCountByTitleMd5(titleMd5);
                    if (textInfoCount == 0) {
                        TextInfo textInfo = new TextInfo();

                        textInfo.setTitle(title);
                        textInfo.setTitleMd5(titleMd5);
                        textInfo.setTime(DateUtils.msToDate(jsonObject2.getLong(paramByLevel2.getTimeField())));
                        textInfo.setContext(jsonObject2.getString(paramByLevel2.getContextField()));

                        textInfoMapper.insert(textInfo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

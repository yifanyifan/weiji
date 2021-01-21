package com.fujiang.weiji.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Xu Haidong
 * @date 2018/4/24
 */
public final class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String DATE_TIME = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_DAY = "yyyy/MM/dd";

    public static final Date stringToDate(String str, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATE_TIME;
        }
        try {
            return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            logger.error("str:{},format:{}", str, format, e);
            return null;
        }
    }

    public static final String dateToString(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = DATE_TIME;
        }
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            logger.error("date:{},format:{}", date, format, e);
            return null;
        }
    }

    /**
     * 毫秒转日期
     *
     * @param ms
     * @return
     */
    public static Date msToDate(Long ms) {
        Date date = new Date();
        date.setTime(ms);
        return date;
    }

    public static Double getHoursCountFromBgtmToEndtm(String bgTm, String endTm) {
        Double res = null;
        try {
            String bgFormat;
            if (bgTm.length() == DATE_TIME.length()) {
                bgFormat = DATE_TIME;
            } else if (bgTm.length() == DATE_DAY.length()) {
                bgFormat = DATE_DAY;
            } else {
                throw new RuntimeException("bgTm format error!");
            }
            String endFormat;
            if (endTm.length() == DATE_TIME.length()) {
                endFormat = DATE_TIME;
            } else if (endTm.length() == DATE_DAY.length()) {
                endFormat = DATE_DAY;
            } else {
                throw new RuntimeException("endTm format error!");
            }
            Long fr = stringToDate(bgTm, bgFormat).getTime();
            Long to = stringToDate(endTm, endFormat).getTime();
            if (endFormat.equals(DATE_DAY)) {
                to += 24 * 60 * 60 * 1000;
            }
            Double result = (Double.valueOf(to + "") - Double.valueOf(fr + "")) / (60 * 60 * 1000);
            res = Math.ceil(result);
        } catch (Exception e) {
            logger.error("bgTm:{},endTm:{}", bgTm, endTm, e);
            return null;
        }
        return res;
    }

    public static Double getDaysCountFromBgtmToEndtm(String bgTm, String endTm) {
        Double res;
        try {
            String bgFormat;
            if (bgTm.length() == DATE_TIME.length()) {
                bgFormat = DATE_TIME;
            } else if (bgTm.length() == DATE_DAY.length()) {
                bgFormat = DATE_DAY;
            } else {
                throw new RuntimeException("bgTm format error!");
            }
            String endFormat;
            if (endTm.length() == DATE_TIME.length()) {
                endFormat = DATE_TIME;
            } else if (endTm.length() == DATE_DAY.length()) {
                endFormat = DATE_DAY;
            } else {
                throw new RuntimeException("endTm format error!");
            }
            Long fr = stringToDate(bgTm, bgFormat).getTime();
            Long to = stringToDate(endTm, endFormat).getTime();
            if (endFormat.equals(DATE_DAY)) {
                to += 24 * 60 * 60 * 1000;
            }
            Double result = (Double.valueOf(to + "") - Double.valueOf(fr + "")) / (24 * 60 * 60 * 1000);
            res = Math.ceil(result);
        } catch (Exception e) {
            logger.error("bgTm:{},endTm:{}", bgTm, endTm, e);
            return null;
        }
        return res;
    }

    public static Double getNumberOfDaysCalculation(String bgTm, String endTm) {
        Double res;
        try {
            Long fr = stringToDate(bgTm, DATE_DAY).getTime();
            Long to = stringToDate(endTm, DATE_DAY).getTime();
            Double result = (Double.valueOf(to + "") - Double.valueOf(fr + "")) / (24 * 60 * 60 * 1000);
            res = Math.ceil(result);
        } catch (Exception e) {
            logger.error("bgTm:{},endTm:{}", bgTm, endTm, e);
            return null;
        }
        return res;
    }

    public static int compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat(DATE_TIME);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 2;
            }
        } catch (Exception e) {
            logger.error("date1:{},date2:{}", date1, date2, e);
        }
        return 0;
    }
}

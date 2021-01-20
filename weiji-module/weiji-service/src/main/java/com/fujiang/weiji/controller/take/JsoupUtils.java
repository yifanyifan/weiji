package com.fujiang.weiji.controller.take;

public class JsoupUtils {
    public static String replaceChar(String s) {
        s = s.replaceAll("&quot;", "\"");
        s = s.replaceAll("&amp;", "&");
        s = s.replaceAll("&lt;", "<");
        s = s.replaceAll("&gt;", ">");
        s = s.replaceAll("&nbsp;", " ");
        return s;
    }
}

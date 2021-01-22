package com.fujiang.weiji.enumeration;

public enum ModuleEnum {
    FAST("0", "快讯"),
    FINANEW("1", "财务新闻");

    private String code;
    private String name;

    ModuleEnum() {
    }

    ModuleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

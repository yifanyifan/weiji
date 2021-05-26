package com.fujiang.weiji.dto.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 甚而返回类
 */
@Data
public class DataResponse implements Serializable {
    /**
     * 编码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 对象
     */
    private Object object;

    public DataResponse() {
    }

    public DataResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public DataResponse(Integer code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }
}

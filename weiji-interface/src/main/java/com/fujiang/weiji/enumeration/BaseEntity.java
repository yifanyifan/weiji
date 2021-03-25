package com.fujiang.weiji.enumeration;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    private Integer left;
    private Integer right;
}

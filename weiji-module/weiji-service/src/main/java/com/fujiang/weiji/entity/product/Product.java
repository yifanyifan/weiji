package com.fujiang.weiji.entity.product;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@Data
public class Product {
    private String name;
    private int age;
    private String add;
    private String email;
}

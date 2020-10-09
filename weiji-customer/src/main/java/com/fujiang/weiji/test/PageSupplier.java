package com.fujiang.weiji.test;

import lombok.Data;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@Data
public class PageSupplier<T, U> implements Supplier<T> {
    // 分页查询调用方法
    private BiFunction<T, U, T> func;
    // 查询参数
    private T param1;
    // 查询参数
    private U param;

    private Integer value;

    @Override
    public T get() {
        param1 = func.apply(param1, param);
        return param1;
    }
}

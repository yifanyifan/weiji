package com.fujiang.weiji.test;

import lombok.Setter;

import java.util.List;
import java.util.function.Supplier;

/**
 * Excel数据源工具类，用于批量分页提取数据到缓存，并逐个提供给后续流程
 * 
 * @param <T> 导出到Excel数据对象类型
 * @param <U> 查询条件对象类型
 * @author whk00104/金豆-小蝴蝶
 * @since 2018-04
 */
public class PageableSupplier<T, U> implements Supplier<T> {
    // 查询分页大小限制
    private static int LIMIT_SIZE = 10000;
    // 当前获取元素位置
    private int index = 0;
    // 队列大小
    private int size = 0;
    // 是否已获取所有数据
    private boolean isOver = false;
    // 分页对象。设置不进行count
    //private Page<T> page = new Page<T>(1,LIMIT_SIZE);
    // 当前获取的数据
    private List<T> list;

    // 查询参数
    @Setter
    private U param;
    // 分页查询调用方法
    //@Setter
    //private BiFunction<U, Page<T>, Page<T>> func;

    /**
     * 获取一个元素，当可用元素为空，且数据还有记录时，获取下一批记录
     * 
     * @author whk00104/金豆-小蝴蝶
     */
    @Override
    public T get() {
//        if (index >= size && !isOver) {
//            initData();
//        }
        return index < size ? list.get(index++) : null;
    }

    /**
     * 获取下一批记录，当记录数小于分页大小时判定已获取所有数据
     * 
     * @author whk00104/金豆-小蝴蝶
     */
    /*public void initData() {
    	page = func.apply(param, page);
        list = page.getRecords();
        size = list.size();

        if (size < LIMIT_SIZE) {
            isOver = true;
        }
        index = 0;
        page.setCurrent(page.getCurrent() + 1);
    }*/
}

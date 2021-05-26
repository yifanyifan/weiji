package com.fujiang.weiji.rabbitmq.config;

/**
 * rabbitMQ 交换器/队列 键值声明及绑定
 */
public enum RabbitmqEnum {
    EXCHANGE_FINANCE("EXCHANGE_FINANCE", "财务模块交换器"),

    QUEUE_FINANCE_EXPORT("QUEUE_FINANCE_EXPORT", "财务模块导出"),
    ROUTKEY_FINANCE_EXPORT("ROUTKEY_FINANCE_EXPORT", "财务模块导出"),

    QUEUE_ORDER_EXPORT("QUEUE_ORDER_EXPORT", "订单模块导出"),
    ROUTKEY_ORDER_EXPORT("ROUTKEY_ORDER_EXPORT", "订单模块导出"),

    QUEUE_DELAY("QUEUE_DELAY", "延迟队列");

    private String code;
    private String name;

    RabbitmqEnum() {
    }

    RabbitmqEnum(String code, String name) {
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

    @Override
    public String toString() {
        return name;
    }
}

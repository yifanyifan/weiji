package com.fujiang.weiji.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;

//@Component
public class FeignClientConfig {
    public RequestInterceptor headerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String xid = RootContext.getXID();
                requestTemplate.header("TX_XID", xid);
            }
        };
    }
}

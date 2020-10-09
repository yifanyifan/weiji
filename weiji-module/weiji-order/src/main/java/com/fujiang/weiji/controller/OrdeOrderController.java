package com.fujiang.weiji.controller;

import com.fujiang.weiji.entity.orde.OrdeOrder;
import com.fujiang.weiji.service.OrdeOrderService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yifan
 * @Description:
 * @date: 2020/4/25
 * Modified By:
 */
@RestController
@RequestMapping("/order")
public class OrdeOrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrdeOrderController.class);

    @Autowired
    private OrdeOrderService ordeOrderService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert() throws Exception {
        //有值则在全局事务当中
        String xId = RootContext.getXID();
        String unXid = RootContext.unbind();
        Boolean b = RootContext.inGlobalTransaction();
        Boolean b1 = RootContext.requireGlobalLock();

        if (true) {
            throw new Exception("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }

        OrdeOrder ordeOrder = new OrdeOrder();
        ordeOrder.setId("1");
        ordeOrderService.insert(ordeOrder);

        return "successOrder";
    }
}

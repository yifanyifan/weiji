package com.fujiang.weiji.controller.runoob;

import com.fujiang.weiji.entity.runoob.RunoobTbl;
import com.fujiang.weiji.service.RunoobTblService;
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
@RequestMapping("/runoobTbl")
public class RunoobTblController {
    private static final Logger logger = LoggerFactory.getLogger(RunoobTblController.class);

    @Autowired
    private RunoobTblService runoobTblService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert() {
        RunoobTbl runoobTbl = new RunoobTbl();
        runoobTbl.setRunoobId(1);
        runoobTblService.insert(runoobTbl);

        return "success";
    }
}

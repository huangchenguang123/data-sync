package com.chuxing.datasyncservice.controller;

import com.chuxing.datasyncservice.model.rpc.common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @date 2022/12/7 13:48
 * @author huangchenguang
 * @desc TestHttpCallbackController
 */
@RestController
@RequestMapping("/test")
public class TestHttpCallbackController {

    /**
     * @date 2022/11/24 17:33
     * @author huangchenguang
     * @desc create flow
     */
    @RequestMapping("/get")
    public Result<Map<String, Object>> get(@RequestBody Map<String, Object> map) {
        return Result.success(map);
    }

}

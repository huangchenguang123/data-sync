package com.chuxing.datasyncservice.controller;

import com.chuxing.datasyncservice.model.rpc.common.Page;
import com.chuxing.datasyncservice.model.rpc.common.Result;
import com.chuxing.datasyncservice.model.rpc.request.*;
import com.chuxing.datasyncservice.model.rpc.response.FlowResponse;
import com.chuxing.datasyncservice.service.FlowService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @date 2022/11/24 17:07
 * @author huangchenguang
 * @desc FlowController
 */
@RestController
@RequestMapping("/flow")
public class FlowController {

    @Resource
    private FlowService flowService;

    /**
     * @date 2022/11/24 17:33
     * @author huangchenguang
     * @desc create flow
     */
    @RequestMapping("/get")
    public Result<FlowResponse> get(@RequestBody @Valid FlowGetRequest flowRequest) {
        return Result.success(flowService.get(flowRequest));
    }

    /**
     * @date 2022/11/24 17:33
     * @author huangchenguang
     * @desc create flow
     */
    @RequestMapping("/search")
    public Result<Page<FlowResponse>> search(@RequestBody @Valid FlowSearchRequest flowSearchRequest) {
        return Result.success(flowService.search(flowSearchRequest));
    }

    /**
     * @date 2022/11/24 17:33
     * @author huangchenguang
     * @desc create flow
     */
    @RequestMapping("/create")
    public Result<Boolean> create(@RequestBody @Valid FlowCreateRequest flowRequest) {
        return Result.success(flowService.create(flowRequest));
    }

    /**
     * @date 2022/11/25 16:01
     * @author huangchenguang
     * @desc delete flow
     */
    @RequestMapping("/delete")
    public Result<Boolean> delete(@RequestBody @Valid FlowDeleteRequest flowRequest) {
        return Result.success(flowService.delete(flowRequest));
    }

    /**
     * @date 2022/11/25 16:01
     * @author huangchenguang
     * @desc enable flow
     */
    @RequestMapping("/enable")
    public Result<Boolean> enable(@RequestBody @Valid FlowEnableRequest flowRequest) {
        return Result.success(flowService.enable(flowRequest));
    }

}

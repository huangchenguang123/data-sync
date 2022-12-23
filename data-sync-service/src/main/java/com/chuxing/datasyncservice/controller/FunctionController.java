package com.chuxing.datasyncservice.controller;

import com.chuxing.datasyncservice.model.rpc.common.Page;
import com.chuxing.datasyncservice.model.rpc.common.Result;
import com.chuxing.datasyncservice.model.rpc.request.*;
import com.chuxing.datasyncservice.model.rpc.response.FunctionResponse;
import com.chuxing.datasyncservice.service.FunctionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @date 2022/12/22 10:02
 * @author huangchenguang
 * @desc FunctionController
 */
@RestController
@RequestMapping("/function")
public class FunctionController {

    @Resource
    private FunctionService functionService;

    /**
     * @date 2022/12/22 10:17
     * @author huangchenguang
     * @desc get function
     */
    @RequestMapping("/get")
    public Result<FunctionResponse> get(@RequestBody @Valid FunctionGetRequest functionGetRequest) {
        return Result.success(functionService.get(functionGetRequest));
    }

    /**
     * @date 2022/12/22 10:56
     * @author huangchenguang
     * @desc search function
     */
    @RequestMapping("/search")
    public Result<Page<FunctionResponse>> search(@RequestBody @Valid FunctionSearchRequest functionSearchRequest) {
        return Result.success(functionService.search(functionSearchRequest));
    }

    /**
     * @date 2022/11/24 17:33
     * @author huangchenguang
     * @desc create function
     */
    @RequestMapping("/create")
    public Result<Boolean> create(@RequestBody @Valid FunctionCreateRequest functionCreateRequest) {
        return Result.success(functionService.create(functionCreateRequest));
    }

    /**
     * @date 2022/11/24 17:33
     * @author huangchenguang
     * @desc update function
     */
    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody @Valid FunctionUpdateRequest functionUpdateRequest) {
        return Result.success(functionService.update(functionUpdateRequest));
    }

    /**
     * @date 2022/11/24 17:33
     * @author huangchenguang
     * @desc delete function
     */
    @RequestMapping("/delete")
    public Result<Boolean> update(@RequestBody @Valid FunctionDeleteRequest functionDeleteRequest) {
        return Result.success(functionService.delete(functionDeleteRequest));
    }

}

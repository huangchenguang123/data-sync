package com.chuxing.datasyncservice.service;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.dao.FunctionDAO;
import com.chuxing.datasyncservice.model.dto.FunctionDTO;
import com.chuxing.datasyncservice.model.rpc.common.Page;
import com.chuxing.datasyncservice.model.rpc.request.*;
import com.chuxing.datasyncservice.model.rpc.response.FunctionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @date 2022/11/24 17:19
 * @author huangchenguang
 * @desc FlowService
 */
@Slf4j
@Service
public class FunctionService {

    @Resource
    private FunctionDAO functionDAO;

    /**
     * @date 2022/12/19 16:10
     * @author huangchenguang
     * @desc get all function
     */
    public List<FunctionDTO> getAllFunction() {
        return functionDAO.getAllFunction();
    }

    /**
     * @date 2022/12/22 10:17
     * @author huangchenguang
     * @desc get function
     */
    public FunctionResponse get(FunctionGetRequest functionGetRequest) {
        FunctionDTO functionDTO = functionDAO.getFunctionById(functionGetRequest.getId());
        if (Objects.isNull(functionDTO)) {
            return null;
        }
        return FunctionResponse.builder()
                .id(functionDTO.getId())
                .functionName(functionDTO.getFunctionName())
                .script(functionDTO.getScript())
                .createAt(functionDTO.getCreateAt())
                .updateAt(functionDTO.getUpdateAt())
                .build();
    }

    /**
     * @date 2022/12/22 11:03
     * @author huangchenguang
     * @desc search
     */
    public Page<FunctionResponse> search(FunctionSearchRequest functionSearchRequest) {
        List<FunctionDTO> list = functionDAO.searchFunction(
                functionSearchRequest.getFunctionName(),
                (functionSearchRequest.getPage() - 1) * functionSearchRequest.getPageSize(),
                functionSearchRequest.getPageSize());

        Integer count = functionDAO.countFunction(functionSearchRequest.getFunctionName());

        List<FunctionResponse> result = list.stream().map(
                functionDTO -> FunctionResponse.builder()
                        .id(functionDTO.getId())
                        .functionName(functionDTO.getFunctionName())
                        .script(functionDTO.getScript())
                        .createAt(functionDTO.getCreateAt())
                        .updateAt(functionDTO.getUpdateAt())
                        .build()).collect(Collectors.toList());

        return Page.<FunctionResponse>builder()
                .data(result)
                .currentPage(functionSearchRequest.getPage())
                .totalPage(count / functionSearchRequest.getPage())
                .build();
    }

    /**
     * @date 2022/12/22 11:26
     * @author huangchenguang
     * @desc create function
     */
    public Boolean create(FunctionCreateRequest functionCreateRequest) {
        FunctionDTO functionDTO = FunctionDTO.builder()
                .functionName(functionCreateRequest.getFunctionName())
                .script(functionCreateRequest.getScript())
                .createAt(System.currentTimeMillis())
                .updateAt(System.currentTimeMillis())
                .build();

        return functionDAO.createFunction(functionDTO) > 0;
    }

    /**
     * @date 2022/12/22 11:26
     * @author huangchenguang
     * @desc update function
     */
    public Boolean update(FunctionUpdateRequest functionUpdateRequest) {
        FunctionDTO functionDTO = functionDAO.getFunctionById(functionUpdateRequest.getId());
        if (Objects.isNull(functionDTO)) {
            log.error("[FunctionService.update] update fail, function is empty, request={}", JSON.toJSONString(functionUpdateRequest));
            throw new RuntimeException("update fail, function is empty");
        }
        functionDTO = FunctionDTO.builder()
                .id(functionUpdateRequest.getId())
                .functionName(functionUpdateRequest.getFunctionName())
                .script(functionUpdateRequest.getScript())
                .updateAt(System.currentTimeMillis())
                .build();

        return functionDAO.updateFunction(functionDTO) > 0;
    }

    /**
     * @date 2022/12/23 09:49
     * @author huangchenguang
     * @desc delete function
     */
    public Boolean delete(FunctionDeleteRequest functionDeleteRequest) {
        return functionDAO.deleteFunctionById(functionDeleteRequest.getId()) > 0;
    }

}

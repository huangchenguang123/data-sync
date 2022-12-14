package com.chuxing.datasyncservice.service;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.dao.FlowDAO;
import com.chuxing.datasyncservice.model.dto.FlowDTO;
import com.chuxing.datasyncservice.model.rpc.common.Page;
import com.chuxing.datasyncservice.model.rpc.request.*;
import com.chuxing.datasyncservice.model.rpc.response.FlowResponse;
import com.chuxing.datasyncservice.service.flow.FlowManager;
import com.chuxing.datasyncservice.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
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
public class FlowService {

    @Resource
    private FlowDAO flowDAO;

    @Lazy
    @Resource
    private FlowManager flowManager;

    /**
     * @date 2022/12/19 16:10
     * @author huangchenguang
     * @desc get all flow
     */
    public List<FlowDTO> getAllFlow() {
        return flowDAO.getAllFlow();
    }

    /**
     * @date 2022/11/25 10:09
     * @author huangchenguang
     * @desc get flow
     */
    public FlowResponse get(FlowGetRequest flowGetRequest) {
        FlowDTO flowDTO = flowDAO.getFlowById(flowGetRequest.getFlowId());
        if (Objects.isNull(flowDTO)) {
            return null;
        }
        return FlowResponse.builder()
                .id(flowDTO.getId())
                .flowName(flowDTO.getFlowName())
                .config(flowDTO.getConfig())
                .enable(flowDTO.getEnable() == 1)
                .createAt(flowDTO.getCreateAt())
                .updateAt(flowDTO.getUpdateAt())
                .build();
    }

    /**
     * @date 2022/11/25 16:05
     * @author huangchenguang
     * @desc search flow
     */
    public Page<FlowResponse> search(FlowSearchRequest flowSearchRequest) {
        List<FlowDTO> list = flowDAO.searchFlow(
                flowSearchRequest.getFlowName(),
                (flowSearchRequest.getPage() - 1) * flowSearchRequest.getPageSize(),
                flowSearchRequest.getPageSize());

        Integer count = flowDAO.countFlow(flowSearchRequest.getFlowName());

        List<FlowResponse> result = list.stream().map(
                flowDTO -> FlowResponse.builder()
                        .id(flowDTO.getId())
                        .flowName(flowDTO.getFlowName())
                        .config(flowDTO.getConfig())
                        .enable(flowDTO.getEnable() == 1)
                        .createAt(flowDTO.getCreateAt())
                        .updateAt(flowDTO.getUpdateAt())
                        .build()
                )
                .collect(Collectors.toList());

        return Page.<FlowResponse>builder()
                .data(result)
                .currentPage(flowSearchRequest.getPage())
                .totalPage(count / flowSearchRequest.getPage())
                .build();
    }

    /**
     * @date 2022/11/24 17:26
     * @author huangchenguang
     * @desc create flow
     */
    public Boolean create(FlowCreateRequest flowRequest) {
        FlowDTO flowDTO = FlowDTO.builder()
                .flowName(flowRequest.getFlowName())
                .config(flowRequest.getConfig())
                .enable(0)
                .createAt(DateTimeUtils.getCurrentTimestamp())
                .updateAt(DateTimeUtils.getCurrentTimestamp())
                .build();

        return flowDAO.createFlow(flowDTO) > 0;
    }

    /**
     * @date 2022/11/24 17:26
     * @author huangchenguang
     * @desc update flow
     */
    public Boolean update(FlowUpdateRequest flowRequest) {
        FlowDTO flowDTO = flowDAO.getFlowById(flowRequest.getId());
        if (Objects.isNull(flowDTO)) {
            log.error("[FlowService.update] update fail, flow is empty, request={}", JSON.toJSONString(flowRequest));
            throw new RuntimeException("update fail, flow is empty");
        }
        flowDTO.setFlowName(flowRequest.getFlowName());
        flowDTO.setConfig(flowRequest.getConfig());
        flowDTO.setUpdateAt(System.currentTimeMillis());
        return flowDAO.updateFlow(flowDTO) > 0;
    }

    /**
     * @date 2022/11/25 17:33
     * @author huangchenguang
     * @desc delete
     */
    public Boolean delete(FlowDeleteRequest flowRequest) {
        return flowDAO.deleteFlowById(flowRequest.getFlowId()) > 0;
    }

    /**
     * @date 2022/11/25 17:37
     * @author huangchenguang
     * @desc enable
     */
    public Boolean enable(FlowEnableRequest flowRequest) {
        boolean result = flowDAO.enableFlow(flowRequest.getFlowId(), flowRequest.getEnable() ? 1 : 0) > 0;
        FlowDTO flowDTO = flowDAO.getFlowById(flowRequest.getFlowId());
        if (flowRequest.getEnable()) {
            flowManager.stopFlow(flowDTO);
            flowManager.initFlow(flowDTO);
            flowManager.startFlow(flowDTO);
        } else {
            flowManager.stopFlow(flowDTO);
        }
        return result;
    }

}

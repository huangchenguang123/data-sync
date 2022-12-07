package com.chuxing.datasyncservice.service;

import com.chuxing.datasyncservice.dao.FlowDAO;
import com.chuxing.datasyncservice.model.dto.FlowDTO;
import com.chuxing.datasyncservice.model.rpc.common.Page;
import com.chuxing.datasyncservice.model.rpc.request.*;
import com.chuxing.datasyncservice.model.rpc.response.FlowResponse;
import com.chuxing.datasyncservice.service.flow.FlowManager;
import com.chuxing.datasyncservice.utils.DateTimeUtils;
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
@Service
public class FlowService {

    @Resource
    private FlowDAO flowDAO;

    @Resource
    private FlowManager flowManager;

    /**
     * @date 2022/11/25 10:09
     * @author huangchenguang
     * @desc get flow
     */
    public FlowResponse get(FlowGetRequest flowGetRequest) {
        FlowDTO flowDTO = flowDAO.getFlow(flowGetRequest.getFlowId());
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
     * @date 2022/11/25 17:33
     * @author huangchenguang
     * @desc delete
     */
    public Boolean delete(FlowDeleteRequest flowRequest) {
        return flowDAO.deleteFlow(flowRequest.getFlowId()) > 0;
    }

    /**
     * @date 2022/11/25 17:37
     * @author huangchenguang
     * @desc enable
     */
    public Boolean enable(FlowEnableRequest flowRequest) {
        boolean result = flowDAO.enableFlow(flowRequest.getFlowId(), flowRequest.getEnable() ? 1 : 0) > 0;
        FlowDTO flowDTO = flowDAO.getFlow(flowRequest.getFlowId());
        if (flowRequest.getEnable()) {
            flowManager.initFlow(flowDTO);
            flowManager.startFlow(flowDTO);
        } else {
            flowManager.stopFlow(flowDTO);
        }
        return result;
    }

}

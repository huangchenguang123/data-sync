package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/11/25 10:08
 * @author huangchenguang
 * @desc FlowGetRequest
 */
@Data
public class FlowGetRequest implements Serializable {

    /**
     * @date 2022/11/25 10:08
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = -5154983031712655728L;

    /**
     * @date 2022/11/25 10:08
     * @author huangchenguang
     * @desc flowId
     */
    private Long flowId;

}
package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/10/24 15:29
 * @author huangchenguang
 * @desc FlowCreateRequest
 */
@Data
public class FlowCreateRequest implements Serializable {

    /**
     * @date 2022/11/24 17:17
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 1627950836066992314L;

    /**
     * @date 2022/10/24 15:34
     * @desc flow name
     */
    private String flowName;

    /**
     * @date 2022/10/24 15:34
     * @desc config
     */
    private String config;

}

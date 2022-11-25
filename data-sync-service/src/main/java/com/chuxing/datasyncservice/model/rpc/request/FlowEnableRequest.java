package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/11/25 10:08
 * @author huangchenguang
 * @desc FlowEnableRequest
 */
@Data
public class FlowEnableRequest implements Serializable {

    /**
     * @date 2022/11/25 17:33
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 8740144936399010305L;

    /**
     * @date 2022/11/25 10:08
     * @author huangchenguang
     * @desc flowId
     */
    private Long flowId;

    /**
     * @date 2022/11/25 17:36
     * @author huangchenguang
     * @desc enable
     */
    private Boolean enable;

}

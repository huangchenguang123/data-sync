package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @date 2022/11/25 10:08
 * @author huangchenguang
 * @desc FlowDeleteRequest
 */
@Data
public class FlowDeleteRequest implements Serializable {

    /**
     * @date 2022/11/25 17:33
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 8740144936399010305L;

    /**
     * @date 2022/11/25 10:08
     * @desc flowId
     */
    @NotNull
    private Long flowId;

}

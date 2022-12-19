package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @date 2022/10/24 15:29
 * @author huangchenguang
 * @desc FlowUpdateRequest
 */
@Data
public class FlowUpdateRequest implements Serializable {

    /**
     * @date 2022/11/24 17:17
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 1627950836066992314L;

    /**
     * @date 2022/12/19 10:58
     * @author huangchenguang
     * @desc id
     */
    private Long id;

    /**
     * @date 2022/10/24 15:34
     * @desc flow name
     */
    @NotNull
    private String flowName;

    /**
     * @date 2022/10/24 15:34
     * @desc config
     */
    @NotNull
    private String config;

}

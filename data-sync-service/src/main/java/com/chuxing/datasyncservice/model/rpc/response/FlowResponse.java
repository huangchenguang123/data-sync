package com.chuxing.datasyncservice.model.rpc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @date 2022/11/25 10:09
 * @author huangchenguang
 * @desc FlowResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowResponse implements Serializable {

    /**
     * @date 2022/11/25 10:09
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 3837466755057578200L;

    /**
     * @date 2022/10/24 15:34
     * @desc id
     */
    private Long id;

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

    /**
     * @date 2022/10/24 15:34
     * @desc enable
     */
    private Boolean enable;

    /**
     * @date 2022/10/24 15:34
     * @desc create time
     */
    private Long createAt;

    /**
     * @date 2022/10/24 15:35
     * @desc update time
     */
    private Long updateAt;

}

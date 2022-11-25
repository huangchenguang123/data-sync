package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/11/25 10:08
 * @author huangchenguang
 * @desc FlowSearchRequest
 */
@Data
public class FlowSearchRequest implements Serializable {

    /**
     * @date 2022/11/25 10:08
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = -388164242555291936L;

    /**
     * @date 2022/11/25 15:58
     * @author huangchenguang
     * @desc flowName
     */
    private String flowName;


    /**
     * @date 2022/11/25 16:14
     * @author huangchenguang
     * @desc page
     */
    private Integer page;

    /**
     * @date 2022/11/25 17:24
     * @author huangchenguang
     * @desc pageSize
     */
    private Integer pageSize;

}

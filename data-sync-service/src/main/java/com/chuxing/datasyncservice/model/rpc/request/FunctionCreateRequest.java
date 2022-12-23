package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @date 2022/10/24 15:29
 * @author huangchenguang
 * @desc FunctionCreateRequest
 */
@Data
public class FunctionCreateRequest implements Serializable {

    /**
     * @date 2022/12/22 11:23
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = -7548054799155831244L;

    /**
     * @date 2022/10/24 15:34
     * @desc function name
     */
    @NotNull
    private String functionName;

    /**
     * @date 2022/10/24 15:34
     * @desc script
     */
    @NotNull
    private String script;

}

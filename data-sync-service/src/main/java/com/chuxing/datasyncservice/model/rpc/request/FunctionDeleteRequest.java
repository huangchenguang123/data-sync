package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @date 2022/11/25 10:08
 * @author huangchenguang
 * @desc FunctionDeleteRequest
 */
@Data
public class FunctionDeleteRequest implements Serializable {

    /**
     * @date 2022/11/25 17:33
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 5445433429912255521L;

    /**
     * @date 2022/11/25 10:08
     * @desc flowId
     */
    @NotNull
    private Long functionId;

}

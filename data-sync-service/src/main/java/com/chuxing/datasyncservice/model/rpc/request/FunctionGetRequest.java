package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @date 2022/12/22 10:13
 * @author huangchenguang
 * @desc FunctionGetRequest
 */
@Data
public class FunctionGetRequest implements Serializable {

    /**
     * @date 2022/12/22 10:13
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 4799670516100130755L;

    /**
     * @date 2022/12/22 10:13
     * @desc id
     */
    @NotNull
    private Long id;

}

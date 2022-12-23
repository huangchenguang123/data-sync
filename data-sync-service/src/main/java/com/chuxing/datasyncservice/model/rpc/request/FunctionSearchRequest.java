package com.chuxing.datasyncservice.model.rpc.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @date 2022/11/25 10:08
 * @author huangchenguang
 * @desc FunctionSearchRequest
 */
@Data
public class FunctionSearchRequest implements Serializable {

    /**
     * @date 2022/11/25 10:08
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 8224075165097271298L;

    /**
     * @date 2022/11/25 15:58
     * @desc functionName
     */
    private String functionName;

    /**
     * @date 2022/11/25 16:14
     * @desc page
     */
    @Valid
    @Min(value = 1)
    private Integer page;

    /**
     * @date 2022/11/25 17:24
     * @desc pageSize
     */
    @Valid
    @Min(value = 1)
    private Integer pageSize;

}

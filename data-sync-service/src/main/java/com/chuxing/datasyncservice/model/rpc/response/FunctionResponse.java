package com.chuxing.datasyncservice.model.rpc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @date 2022/12/22 10:08
 * @author huangchenguang
 * @desc FunctionResponse
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResponse implements Serializable {
    
    /**
     * @date 2022/12/22 10:08
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = -3807528777749838688L;

    /**
     * @date 2022/12/22 10:13
     * @desc id
     */
    private Long id;

    /**
     * @date 2022/12/22 10:10
     * @desc function name
     */
    private String functionName;

    /**
     * @date 2022/12/22 10:10
     * @desc function script
     */
    private String script;

    /**
     * @date 2022/12/22 10:11
     * @desc create at
     */
    private Long createAt;

    /**
     * @date 2022/12/22 10:11
     * @desc update at
     */
    private Long updateAt;

}

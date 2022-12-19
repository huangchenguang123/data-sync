package com.chuxing.datasyncservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @date 2022/10/24 15:29
 * @author huangchenguang
 * @desc FunctionDTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FunctionDTO implements Serializable {

    /**
     * @date 2022/12/19 16:31
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 6910395388013381209L;

    /**
     * @date 2022/10/24 15:34
     * @desc id
     */
    private Long id;

    /**
     * @date 2022/10/24 15:34
     * @desc function name
     */
    private String functionName;

    /**
     * @date 2022/10/24 15:34
     * @desc script
     */
    private String script;

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

package com.chuxing.datasyncservice.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/10/24 15:29
 * @author huangchenguang
 * @desc FlowDTO
 */
@Data
public class FlowDTO implements Serializable {

    /**
     * @date 2022/10/24 15:30
     * @desc Serializable
     */
    private static final long serialVersionUID = 6736105630640446239L;

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

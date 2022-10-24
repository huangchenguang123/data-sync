package com.chuxing.datasyncservice.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/10/24 15:29
 * @author huangchenguang
 * @desc
 */
@Data
public class ConfigDTO implements Serializable {

    /**
     * @date 2022/10/24 15:30
     * @author huangchenguang
     * @desc Serializable
     */
    private static final long serialVersionUID = 6736105630640446239L;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc id
     */
    private Long id;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc component id
     */
    private Long componentId;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc config
     */
    private String config;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc config type
     */
    private String configType;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc subtype
     */
    private String subType;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc create time
     */
    private Long createAt;

    /**
     * @date 2022/10/24 15:35
     * @author huangchenguang
     * @desc update time
     */
    private Long updateAt;

}

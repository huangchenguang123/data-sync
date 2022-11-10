package com.chuxing.datasyncservice.model.config;

import lombok.Data;

import java.util.Map;

/**
 * @date 2022/10/28 14:11
 * @author huangchenguang
 * @desc ComponentConfig
 */
@Data
public class ComponentConfig {

    /**
     * @date 2022/10/28 14:14
     * @author huangchenguang
     * @desc type
     */
    private String type;

    /**
     * @date 2022/11/9 15:28
     * @author huangchenguang
     * @desc id
     */
    private Integer id;

    /**
     * @date 2022/10/28 14:15
     * @author huangchenguang
     * @desc config
     */
    private Map<String, Object> config;

}

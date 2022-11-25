package com.chuxing.datasyncservice.model.config;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @date 2022/10/28 14:11
 * @author huangchenguang
 * @desc ComponentConfig
 */
@Data
public class ComponentConfig implements Serializable {

    /**
     * @date 2022/11/25 15:44
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = -6435238767671225967L;

    /**
     * @date 2022/10/28 14:14
     * @desc type
     */
    private String type;

    /**
     * @date 2022/11/9 15:28
     * @desc id
     */
    private Integer id;

    /**
     * @date 2022/10/28 14:15
     * @desc config
     */
    private Map<String, Object> config;

}

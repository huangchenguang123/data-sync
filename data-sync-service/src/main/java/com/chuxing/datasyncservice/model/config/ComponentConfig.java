package com.chuxing.datasyncservice.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @date 2022/10/28 14:11
 * @author huangchenguang
 * @desc ComponentConfig
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComponentConfig {

    /**
     * @date 2022/10/28 14:14
     * @author huangchenguang
     * @desc type
     */
    private String type;

    /**
     * @date 2022/10/28 14:15
     * @author huangchenguang
     * @desc config
     */
    private Map<String, Object> config;

}

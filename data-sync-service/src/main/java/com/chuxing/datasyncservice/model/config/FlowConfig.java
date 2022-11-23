package com.chuxing.datasyncservice.model.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @date 2022/10/28 14:19
 * @author huangchenguang
 * @desc FlowConfigDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowConfig {

    /**
     * @date 2022/10/28 14:20
     * @author huangchenguang
     * @desc sources
     */
    private List<ComponentConfig> sources;

    /**
     * @date 2022/10/28 14:20
     * @author huangchenguang
     * @desc channels
     */
    private List<ChannelConfig> channels;

    /**
     * @date 2022/10/28 14:20
     * @author huangchenguang
     * @desc sinks
     */
    private List<ComponentConfig> sinks;

}

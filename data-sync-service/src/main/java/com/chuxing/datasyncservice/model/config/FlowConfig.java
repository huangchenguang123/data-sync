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
     * @desc source
     */
    private List<ComponentConfig> source;

    /**
     * @date 2022/10/28 14:20
     * @author huangchenguang
     * @desc channel
     */
    private List<ChannelConfig> channel;

    /**
     * @date 2022/10/28 14:20
     * @author huangchenguang
     * @desc sink
     */
    private List<ComponentConfig> sink;

}

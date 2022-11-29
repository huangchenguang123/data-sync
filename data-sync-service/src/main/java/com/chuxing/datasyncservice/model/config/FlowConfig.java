package com.chuxing.datasyncservice.model.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @date 2022/10/28 14:19
 * @author huangchenguang
 * @desc FlowConfigDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowConfig implements Serializable {

    /**
     * @date 2022/11/25 15:44
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = -3134899860609430451L;

    /**
     * @date 2022/10/28 14:20
     * @desc sources
     */
    private List<ComponentConfig> sources;

    /**
     * @date 2022/10/28 14:20
     * @desc channels
     */
    private List<ChannelConfig> channels;

    /**
     * @date 2022/10/28 14:20
     * @desc sinks
     */
    private List<ComponentConfig> sinks;

}

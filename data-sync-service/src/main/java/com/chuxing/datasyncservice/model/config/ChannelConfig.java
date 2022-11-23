package com.chuxing.datasyncservice.model.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @date 2022/11/10 16:53
 * @author huangchenguang
 * @desc ChannelConfig
 */
@Getter
@Setter
public class ChannelConfig extends ComponentConfig {

    /**
     * @date 2022/11/7 15:32
     * @desc preChannelId
     */
    private List<Integer> preChannelIds;

    /**
     * @date 2022/11/7 15:32
     * @desc nextChannelId
     */
    private List<Integer> nextChannelIds;

}

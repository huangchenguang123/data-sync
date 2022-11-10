package com.chuxing.datasyncservice.service.flow;

import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.component.source.BaseSource;
import lombok.Getter;

import java.util.Map;

/**
 * @date 2022/10/28 10:57
 * @author huangchenguang
 * @desc Flow
 */
@Getter
public class Flow {

    /**
     * @date 2022/11/7 15:10
     * @author huangchenguang
     * @desc flowName
     */
    private String flowName;

    /**
     * @date 2022/10/28 10:58
     * @author huangchenguang
     * @desc baseSource
     */
    private Map<Integer, BaseSource> baseSources;

    /**
     * @date 2022/10/28 11:00
     * @author huangchenguang
     * @desc baseChannel
     */
    private Map<Integer, BaseChannel> baseChannels;

    public Flow(String flowName, Map<Integer, BaseSource> baseSources, Map<Integer, BaseChannel> baseChannels) {
        this.flowName = flowName;
        this.baseSources = baseSources;
        baseSources.values().forEach(baseSource -> baseSource.setFlow(this));
        this.baseChannels = baseChannels;
        baseChannels.values().forEach(baseChannel -> baseChannel.setFlow(this));
    }

    /**
     * @date 2022/10/28 11:03
     * @author huangchenguang
     * @desc start
     */
    public void start() {
        baseChannels.values().forEach(BaseChannel::start);
        baseSources.values().forEach(BaseSource::start);
    }

    /**
     * @date 2022/10/28 11:04
     * @author huangchenguang
     * @desc stop
     */
    public void stop() {
        baseChannels.values().forEach(BaseChannel::stop);
        baseSources.values().forEach(BaseSource::stop);
    }

}

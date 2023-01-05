package com.chuxing.datasyncservice.service.flow;

import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.component.sink.BaseSink;
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
     * @desc flowName
     */
    private final String flowName;

    /**
     * @date 2022/10/28 10:58
     * @desc baseSources
     */
    private final Map<Integer, BaseSource> baseSources;

    /**
     * @date 2022/10/28 11:00
     * @desc baseChannels
     */
    private final Map<Integer, BaseChannel> baseChannels;

    /**
     * @date 2022/10/28 11:00
     * @desc shadowChannels
     */
    private final Map<Integer, BaseChannel> shadowChannels;

    /**
     * @date 2022/11/23 16:16
     * @desc baseSinks
     */
    private final Map<Integer, BaseSink> baseSinks;

    public Flow(String flowName, Map<Integer, BaseSource> baseSources, Map<Integer, BaseChannel> baseChannels, Map<Integer, BaseChannel> shadowChannels, Map<Integer, BaseSink> baseSinks) {
        this.flowName = flowName;
        this.baseSources = baseSources;
        baseSources.values().forEach(baseSource -> baseSource.setFlow(this));
        this.baseChannels = baseChannels;
        baseChannels.values().forEach(baseChannel -> baseChannel.setFlow(this));
        this.shadowChannels = shadowChannels;
        shadowChannels.values().forEach(baseChannel -> baseChannel.setFlow(this));
        this.baseSinks = baseSinks;
        baseSinks.values().forEach(baseSink -> baseSink.setFlow(this));
    }

    /**
     * @date 2022/10/28 11:03
     * @author huangchenguang
     * @desc start
     */
    public void start() {
        baseSinks.values().forEach(BaseSink::start);
        baseChannels.values().forEach(BaseChannel::start);
        shadowChannels.values().forEach(BaseChannel::start);
        baseSources.values().forEach(BaseSource::start);
    }

    /**
     * @date 2022/10/28 11:04
     * @author huangchenguang
     * @desc stop
     */
    public void stop() {
        baseSources.values().forEach(BaseSource::stop);
        baseChannels.values().forEach(BaseChannel::stop);
        shadowChannels.values().forEach(BaseChannel::stop);
        baseSinks.values().forEach(BaseSink::stop);
    }

}

package com.chuxing.datasyncservice.service.flow;

import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.component.source.BaseSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

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
    private List<BaseSource> baseSources;

    /**
     * @date 2022/10/28 11:00
     * @author huangchenguang
     * @desc baseChannel
     */
    private List<BaseChannel> baseChannels;

    public Flow(String flowName, List<BaseSource> baseSources, List<BaseChannel> baseChannels) {
        this.flowName = flowName;
        this.baseSources = baseSources;
        this.baseChannels = baseChannels;

        // find root channel
        baseChannels.stream().filter(channel -> Objects.isNull(channel.getPreChannelId()));
        baseSources.forEach(source -> source.setRootChannels(baseChannels));
    }

    /**
     * @date 2022/10/28 11:03
     * @author huangchenguang
     * @desc start
     */
    public void start() {
        baseChannels.forEach(BaseChannel::start);
        baseSources.forEach(BaseSource::start);
    }

    /**
     * @date 2022/10/28 11:04
     * @author huangchenguang
     * @desc stop
     */
    public void stop() {
        baseChannels.forEach(BaseChannel::stop);
        baseSources.forEach(BaseSource::stop);
    }

}

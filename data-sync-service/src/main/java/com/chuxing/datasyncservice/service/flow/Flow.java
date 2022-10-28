package com.chuxing.datasyncservice.service.flow;

import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.component.source.BaseSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @date 2022/10/28 10:57
 * @author huangchenguang
 * @desc Flow
 */
@Getter
@AllArgsConstructor
public class Flow {

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

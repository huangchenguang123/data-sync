package com.chuxing.datasyncservice.service.component.source;

import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.chuxing.datasyncservice.model.enums.SourceEnum;
import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @date 2022/10/20 17:16
 * @author huangchenguang
 * @desc the source is the entry point to the data
 */
@Setter
public abstract class BaseSource {

    /**
     * @date 2022/11/7 15:30
     * @author huangchenguang
     * @desc rootChannels
     */
    private List<BaseChannel> rootChannels;

    /**
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init source
     */
    public static BaseSource init(ComponentConfig config) {
        if (config.getType().equals(SourceEnum.NSQ_SOURCE.getName())) {
            return NsqSource.init(config);
        }
        return null;
    }

    /**
     * start source
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void start();

    /**
     * stop source
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void stop();

    /**
     * @date 2022/11/7 15:22
     * @author huangchenguang
     * @desc source run
     */
    public void run(Map<String, Object> data) {
        rootChannels.forEach(rootChannel -> rootChannel.run(data));
    }

}

package com.chuxing.datasyncservice.service.component.channel;

import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.chuxing.datasyncservice.model.enums.ChannelEnum;
import lombok.Getter;

import java.util.Map;

/**
 * @date 2022/11/7 15:00
 * @author huangchenguang
 * @desc BaseChannel
 */
@Getter
public abstract class BaseChannel {

    /**
     * @date 2022/11/7 15:32
     * @author huangchenguang
     * @desc channelId
     */
    protected Integer channelId;

    /**
     * @date 2022/11/7 15:32
     * @author huangchenguang
     * @desc preChannelId
     */
    protected Integer preChannelId;

    /**
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init source
     */
    public static BaseChannel init(ComponentConfig config) {
        if (config.getType().equals(ChannelEnum.EXPRESS_CHANNEL.getName())) {
            return ExpressChannel.init(config);
        }
        return null;
    }

    /**
     * start channel
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void start();

    /**
     * stop channel
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void stop();

    /**
     * run
     *
     * @date 2022/10/27 17:19
     * @author huangchenguang
     */
    public abstract void run(Map<String, Object> data);

}

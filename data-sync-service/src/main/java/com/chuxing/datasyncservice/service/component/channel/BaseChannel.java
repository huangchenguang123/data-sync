package com.chuxing.datasyncservice.service.component.channel;

import com.chuxing.datasyncservice.model.config.ChannelConfig;
import com.chuxing.datasyncservice.model.enums.ChannelEnum;
import com.chuxing.datasyncservice.service.flow.Flow;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @date 2022/11/7 15:00
 * @author huangchenguang
 * @desc BaseChannel
 */
@Data
public abstract class BaseChannel {

    /**
     * @date 2022/11/10 16:12
     * @desc
     */
    private Flow flow;

    /**
     * @date 2022/10/28 14:14
     * @desc type
     */
    private String type;

    /**
     * @date 2022/11/9 15:28
     * @desc id
     */
    private Integer id;

    /**
     * @date 2022/11/7 15:32
     * @desc preChannelId
     */
    protected List<Integer> preChannelIds;

    /**
     * @date 2022/11/7 15:32
     * @desc nextChannelId
     */
    protected List<Integer> nextChannelIds;

    /**
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init channel
     */
    public static BaseChannel init(ChannelConfig config) {
        BaseChannel baseChannel;
        if (config.getType().equals(ChannelEnum.EXPRESS_CHANNEL.getName())) {
            baseChannel = ExpressChannel.init(config);
        } else {
            throw new RuntimeException("not support channel type");
        }
        baseChannel.setId(config.getId());
        baseChannel.setType(config.getType());
        baseChannel.setPreChannelIds(config.getPreChannelIds());
        baseChannel.setNextChannelIds(config.getNextChannelIds());
        return baseChannel;
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
     * @param data all data
     */
    public abstract void run(Map<String, Object> data);

}

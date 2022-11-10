package com.chuxing.datasyncservice.service.component.source;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.sproutsocial.nsq.MessageDataHandler;
import com.sproutsocial.nsq.Subscriber;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @date 2022/10/20 17:30
 * @author huangchenguang
 * @desc nsq source
 */
@Data
public class NsqSource extends BaseSource {

    /**
     * @date 2022/10/20 17:29
     * @author huangchenguang
     * @desc lookup address
     */
    private String[] lookupAddress;

    /**
     * @date 2022/10/20 17:29
     * @author huangchenguang
     * @desc topic name
     */
    private String topic;

    /**
     * @date 2022/10/20 17:28
     * @author huangchenguang
     * @desc channel name
     */
    private String channel;

    /**
     * @date 2022/10/24 19:50
     * @author huangchenguang
     * @desc subscriber
     */
    private Subscriber subscriber;

    /**
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init source
     */
    public static BaseSource init(ComponentConfig config) {
        return JSON.parseObject(JSON.toJSONString(config.getConfig()), NsqSource.class);
    }

    /**
     * @date 2022/10/24 19:28
     * @author huangchenguang
     * @desc start
     */
    @Override
    public void start() {
        subscriber = new Subscriber(lookupAddress);
        subscriber.subscribe(topic, channel, (MessageDataHandler) bytes -> {
            Map<String, Object> data = JSON.parseObject(new String(bytes), new TypeReference<Map<String, Object>>() {});
            run(data);
        });
    }

    /**
     * @date 2022/10/24 19:28
     * @author huangchenguang
     * @desc stop
     */
    @Override
    public void stop() {
        if (Objects.nonNull(subscriber)) {
            subscriber.stop();
        }
    }

}

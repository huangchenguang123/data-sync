package com.chuxing.datasyncservice.service.component.source;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.chuxing.datasyncservice.service.context.Context;
import com.sproutsocial.nsq.MessageDataHandler;
import com.sproutsocial.nsq.Subscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

/**
 * @date 2022/10/20 17:30
 * @author huangchenguang
 * @desc nsq source
 */
@Getter
@Setter
public class NsqSource extends BaseSource {

    /**
     * @date 2022/10/20 17:29
     * @desc lookup address
     */
    private String[] lookupAddress;

    /**
     * @date 2022/10/20 17:29
     * @desc topic name
     */
    private String topic;

    /**
     * @date 2022/10/20 17:28
     * @desc channel name
     */
    private String channel;

    /**
     * @date 2022/10/24 19:50
     * @desc subscriber
     */
    private Subscriber subscriber;

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
            run(data, Context.init());
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

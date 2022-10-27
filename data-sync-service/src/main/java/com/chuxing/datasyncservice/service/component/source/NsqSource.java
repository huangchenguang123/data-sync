package com.chuxing.datasyncservice.service.component.source;

import com.alibaba.fastjson2.JSON;
import com.sproutsocial.nsq.Message;
import com.sproutsocial.nsq.Subscriber;
import lombok.Data;

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
    public static BaseSource init(String config) {
        return JSON.parseObject(config, NsqSource.class);
    }

    /**
     * @date 2022/10/24 19:28
     * @author huangchenguang
     * @desc start
     */
    @Override
    public void start() {
        subscriber = new Subscriber(lookupAddress);
        subscriber.subscribe(topic, channel, this::handleData);
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

    /**
     * @date 2022/10/20 17:28
     * @author huangchenguang
     * @desc handle Data
     */
    public void handleData(Message message) {
        System.out.println("Received:" + new String(message.getData()));
        message.finish();
    }

}

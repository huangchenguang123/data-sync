package com.chuxing.datasyncservice.service.component.source;

import com.sproutsocial.nsq.Message;
import com.sproutsocial.nsq.Subscriber;

/**
 * @date 2022/10/20 17:30
 * @author huangchenguang
 * @desc nsq source
 */
public class NsqSource extends Source {

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
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init source
     */
    @Override
    public void init() {
        Subscriber subscriber = new Subscriber(lookupAddress);
        subscriber.subscribe(topic, channel, this::handleData);
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

package com.chuxing.datasyncservice.service.component.channel;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ChannelConfig;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @date 2022/12/7 15:37
 * @author huangchenguang
 * @desc MappingChannel
 */
@Slf4j
@Getter
@Setter
public class MappingChannel extends BaseChannel {

    /**
     * @date 2022/10/28 10:16
     * @desc channel run flag
     */
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * @date 2022/12/7 15:52
     * @desc mappings
     */
    private List<Mapping> mappings;

    /**
     * @date 2022/12/7 15:51
     * @author huangchenguang
     * @desc Mapping
     */
    @Data
    private static class Mapping {

        /**
         * @date 2022/12/7 15:51
         * @desc name
         */
        private String fromName;

        /**
         * @date 2022/12/7 15:51
         * @desc type
         */
        private String fromType;

        /**
         * @date 2022/12/7 15:51
         * @desc name
         */
        private String toName;

        /**
         * @date 2022/12/7 15:51
         * @desc type
         */
        private String toType;

    }

    /**
     * @date 2022/11/7 15:18
     * @author huangchenguang
     * @desc init mapping channel
     */
    public static MappingChannel init(ChannelConfig config) {
        return JSON.parseObject(JSON.toJSONString(config.getConfig()), MappingChannel.class);
    }

    /**
     * @date 2022/12/7 15:38
     * @author huangchenguang
     * @desc start channel
     */
    @Override
    public void start() {
        isRunning.set(true);
    }

    /**
     * @date 2022/12/7 15:39
     * @author huangchenguang
     * @desc stop channel
     */
    @Override
    public void stop() {
        isRunning.set(false);
    }

    /**
     * @date 2022/12/7 15:40
     * @author huangchenguang
     * @desc run
     */
    @Override
    @SuppressWarnings("unchecked")
    public void run(Map<String, Object> data) {
        if (isRunning.get()) {
            mappings.forEach(mapping -> {
                // get from
                String[] fromNames = mapping.getFromName().split("\\.");
                Object current = data;
                for (String name : fromNames) {
                    try {
                        current = ((Map<String, Object>) current).get(name);
                        if (Objects.isNull(current)) {
                            break;
                        }
                    } catch (Exception e) {
                        log.error("[MappingChannel.run] mapping error, current must be a map, current={}", current, e);
                    }
                }
                Object object = null;
                try {
                    object = JSON.parseObject(JSON.toJSONString(current), Class.forName(mapping.getToType()));
                } catch (ClassNotFoundException e) {
                    log.error("[MappingChannel.run] mapping error, mapping value error, current={}", current, e);
                    throw new RuntimeException(e.getCause());
                }
                // put to
                String[] toNames = mapping.getToName().split("\\.");
                current = data;
                for (int i = 0; i < toNames.length; i++) {
                    try {
                        if (!((Map<String, Object>) current).containsKey(toNames[i])) {
                            if (i == toNames.length - 1) {
                                ((Map<String, Object>) current).put(toNames[i], object);
                            } else {
                                ((Map<String, Object>) current).put(toNames[i], Maps.newHashMap());
                            }
                        }
                        current = ((Map<String, Object>) current).get(toNames[i]);
                    } catch (Exception e) {
                        log.error("[MappingChannel.run] mapping error, current must be a map, current={}", current);
                        throw new RuntimeException(e.getCause());
                    }
                }
            });
        }
    }

}

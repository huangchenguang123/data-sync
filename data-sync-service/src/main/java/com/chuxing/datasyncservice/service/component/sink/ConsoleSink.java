package com.chuxing.datasyncservice.service.component.sink;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ComponentConfig;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @date 2022/11/23 15:30
 * @author huangchenguang
 * @desc ConsoleSink
 */
public class ConsoleSink extends BaseSink {

    /**
     * @date 2022/10/28 10:16
     * @desc channel run flag
     */
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * @date 2022/11/23 16:09
     * @author huangchenguang
     * @desc init console sink
     */
    public static BaseSink init(ComponentConfig config) {
        return JSON.parseObject(JSON.toJSONString(config.getConfig()), ConsoleSink.class);
    }

    /**
     * start sink
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    @Override
    public void start() {
        isRunning.set(true);
    }

    /**
     * stop sink
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    @Override
    public void stop() {
        isRunning.set(false);
    }

    /**
     * run
     *
     * @date 2022/10/27 17:19
     * @author huangchenguang
     * @param data all data
     */
    @Override
    public void run(Map<String, Object> data) {
        if (isRunning.get()) {
            System.out.println(JSON.toJSONString(data));
        }
    }

}

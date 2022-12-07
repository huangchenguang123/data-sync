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
     * @desc sink run flag
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
     * @date 2022/10/25 15:42
     * @author huangchenguang
     * @desc start sink
     */
    @Override
    public void start() {
        isRunning.set(true);
    }

    /**
     * @date 2022/10/25 15:42
     * @author huangchenguang
     * @desc stop sink
     */
    @Override
    public void stop() {
        isRunning.set(false);
    }

    /**
     * @date 2022/10/27 17:19
     * @author huangchenguang
     * @desc run
     * @param data all data
     */
    @Override
    public void run(Map<String, Object> data) {
        if (isRunning.get()) {
            System.out.println(JSON.toJSONString(data));
        }
    }

}

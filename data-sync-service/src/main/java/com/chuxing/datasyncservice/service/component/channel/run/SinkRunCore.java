package com.chuxing.datasyncservice.service.component.channel.run;

import com.chuxing.datasyncservice.service.flow.Flow;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @date 2022/11/23 16:18
 * @author huangchenguang
 * @desc SinkRunCore
 */
public class SinkRunCore {

    /**
     * @date 2022/11/9 15:03
     * @author huangchenguang
     * @desc threadPoolExecutor
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            8,
            8,
            0,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(100000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * @date 2022/11/23 16:39
     * @author huangchenguang
     * @desc submit
     */
    public static void submit(Flow flow, Map<String, Object> data) {
        flow.getBaseSinks().values().forEach(baseSink -> baseSink.run(data));
    }

}

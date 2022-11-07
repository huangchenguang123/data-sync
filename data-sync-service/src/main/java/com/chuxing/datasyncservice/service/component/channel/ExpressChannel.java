package com.chuxing.datasyncservice.service.component.channel;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @date 2022/10/28 10:15
 * @author huangchenguang
 * @desc
 */
public class ExpressChannel extends BaseChannel {

    /**
     * @date 2022/10/28 10:16
     * @author huangchenguang
     * @desc channel run flag
     */
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * @date 2022/10/28 10:26
     * @author huangchenguang
     * @desc runner
     */
    private ExpressRunner runner;

    /**
     * @date 2022/10/28 10:18
     * @author huangchenguang
     * @desc qle script
     */
    private String script;

    /**
     * @date 2022/11/7 15:18
     * @author huangchenguang
     * @desc init channel
     */
    public static ExpressChannel init(ComponentConfig config) {
        return JSON.parseObject(JSON.toJSONString(config.getConfig()), ExpressChannel.class);
    }

    /**
     * start channel
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    @Override
    public void start() {
        isRunning.set(true);
        runner = new ExpressRunner();
    }

    /**
     * stop channel
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
     */
    @Override
    @SuppressWarnings("unchecked")
    public void run(Map<String, Object> data) {
        if (isRunning.get()) {
            DefaultContext<String, Object> context = new DefaultContext<>();
            context.putAll(data);
            try {
                data = (Map<String, Object>) runner.execute(script, context, null, true, false);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}

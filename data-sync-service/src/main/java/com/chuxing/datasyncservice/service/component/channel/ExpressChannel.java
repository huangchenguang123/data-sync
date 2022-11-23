package com.chuxing.datasyncservice.service.component.channel;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ChannelConfig;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @date 2022/10/28 10:15
 * @author huangchenguang
 * @desc
 */
@Data
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
    private ExpressRunner runner = new ExpressRunner();

    /**
     * @date 2022/11/9 14:54
     * @author huangchenguang
     * @desc qle
     */
    private QlExpress qle;

    /**
     * @date 2022/11/9 14:53
     * @author huangchenguang
     * @desc QlExpress
     */
    @Data
    private static class QlExpress {

        /**
         * @date 2022/10/28 10:18
         * @author huangchenguang
         * @desc qle script
         */
        private String script;

        /**
         * @date 2022/11/9 14:53
         * @author huangchenguang
         * @desc result
         */
        private String result;

    }

    /**
     * @date 2022/11/7 15:18
     * @author huangchenguang
     * @desc init express channel
     */
    public static ExpressChannel init(ChannelConfig config) {
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
            // init context
            context.putAll(data);
            try {
                data.put(qle.getResult(), runner.execute(qle.getScript(), context, null, false, false));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}

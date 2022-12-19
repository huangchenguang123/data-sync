package com.chuxing.datasyncservice.service.component.channel;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ChannelConfig;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @date 2022/10/28 10:15
 * @author huangchenguang
 * @desc ExpressChannel
 */
@Getter
@Setter
public class ExpressChannel extends BaseChannel {

    /**
     * @date 2022/10/28 10:16
     * @desc channel run flag
     */
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * @date 2022/10/28 10:26
     * @desc runner
     */
    private ExpressRunner runner = new ExpressRunner();

    /**
     * @date 2022/11/9 14:54
     * @desc qle
     */
    private QlExpress qle;

    /**
     * @date 2022/11/9 14:53
     * @author huangchenguang
     * @desc QlExpress
     */
    @Getter
    @Setter
    private static class QlExpress {

        /**
         * @date 2022/10/28 10:18
         * @desc qle script
         */
        private String script;

        /**
         * @date 2022/11/9 14:53
         * @desc result
         */
        private String resultName;

    }

    /**
     * @date 2022/10/25 15:42
     * @author huangchenguang
     * @desc start channel
     */
    @Override
    public void start() {
        isRunning.set(true);
    }

    /**
     * @date 2022/10/25 15:42
     * @author huangchenguang
     * @desc stop channel
     */
    @Override
    public void stop() {
        isRunning.set(false);
    }

    /**
     * @date 2022/10/27 17:19
     * @author huangchenguang
     * @desc run
     */
    @Override
    public void run(Map<String, Object> data) {
        if (isRunning.get()) {
            DefaultContext<String, Object> context = new DefaultContext<>();
            // init context
            context.putAll(data);
            try {
                data.put(qle.getResultName(), runner.execute(qle.getScript(), context, null, false, false));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}

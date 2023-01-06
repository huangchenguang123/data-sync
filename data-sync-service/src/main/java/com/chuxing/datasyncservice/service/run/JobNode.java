package com.chuxing.datasyncservice.service.run;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.context.Context;
import com.chuxing.datasyncservice.utils.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;

import java.util.List;
import java.util.Map;

/**
 * @date 2022/11/10 16:07
 * @author huangchenguang
 * @desc JobNode
 */
@Slf4j
@Data
public class JobNode implements Runnable {

    /**
     * @date 2022/11/7 15:32
     * @desc preJobNodes
     */
    private List<JobNode> preJobNodes;

    /**
     * @date 2022/11/7 15:32
     * @desc nextJobNodes
     */
    private List<JobNode> nextJobNodes;

    /**
     * @date 2022/11/10 16:09
     * @desc baseChannel
     */
    private BaseChannel baseChannel;

    /**
     * @date 2023/1/5 15:30
     * @desc input
     */
    private Map<String, Object> input;

    /**
     * @date 2022/11/10 16:06
     * @desc data
     */
    private Map<String, Object> data;

    /**
     * @date 2022/12/21 15:42
     * @desc context
     */
    private Context context;

    /**
     * @date 2022/11/23 16:24
     * @desc remainingJobNodes
     */
    private List<JobNode> remainingJobNodes;

    /**
     * @date 2023/1/5 15:03
     * @author huangchenguang
     * @desc is shadow
     */
    private Boolean isShadow;

    /**
     * @date 2023/1/5 15:04
     * @author huangchenguang
     * @desc shadowData
     */
    private Map<String, Object> trueData;

    /**
     * @date 2023/1/6 09:59
     * @author huangchenguang
     * @desc isSwitch
     */
    private Boolean isSwitch;

    /**
     * @date 2022/11/10 16:32
     * @author huangchenguang
     * @desc run
     */
    @Override
    @SuppressWarnings("all")
    public void run() {
        try {
            if (preJobNodes.isEmpty()) {
                baseChannel.run(data);
                // remove myself from remainingJobNodes
                synchronized(remainingJobNodes) {
                    remainingJobNodes.remove(this);
                    if (remainingJobNodes.isEmpty()) {
                        if (BooleanUtils.isTrue(isShadow)) {
                            if (BooleanUtils.isNotTrue(isSwitch)) {
                                String result = JsonUtils.diffView(JSON.toJSONString(trueData), JSON.toJSONString(data));
                                log.info("[JobNode.run] shadow mode run, result is different, json diff result={}", result);
                            } else {
                                SinkRunCore.execute(baseChannel.getFlow(), data, context);
                            }
                        } else {
                            SinkRunCore.execute(baseChannel.getFlow(), data, context);
                            if (MapUtils.isNotEmpty(baseChannel.getFlow().getShadowChannels())) {
                                ChannelRunCore.executeShadow(baseChannel.getFlow(), input, Context.init(), data, false);
                            }
                        }
                    }
                }
                // remove myself from next job node and execute
                nextJobNodes.forEach(nextJobNode -> {
                    // prevent duplicate submissions
                    synchronized (data) {
                        nextJobNode.getPreJobNodes().remove(this);
                        if (nextJobNode.getPreJobNodes().isEmpty()) {
                            ChannelRunCore.execute(nextJobNode);
                        }
                    }
                });
            }
        } catch (Exception e) {
            context.fail(e.getMessage());
        }
    }

}

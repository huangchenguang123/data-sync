package com.chuxing.datasyncservice.service.run;

import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @date 2022/11/10 16:07
 * @author huangchenguang
 * @desc JobNode
 */
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
     * @date 2022/11/10 16:06
     * @desc data
     */
    private Map<String, Object> data;

    /**
     * @date 2022/11/23 16:24
     * @desc remainingJobNodes
     */
    private List<JobNode> remainingJobNodes;

    /**
     * @date 2022/11/10 16:32
     * @author huangchenguang
     * @desc run
     */
    @Override
    @SuppressWarnings("all")
    public void run() {
        if (preJobNodes.isEmpty()) {
            baseChannel.run(data);
            // remove myself from remainingJobNodes
            synchronized(remainingJobNodes) {
                remainingJobNodes.remove(this);
                if (remainingJobNodes.isEmpty()) {
                    SinkRunCore.submit(baseChannel.getFlow(), data);
                }
            }
            // remove myself from next job node and submit
            nextJobNodes.forEach(nextJobNode -> {
                // prevent duplicate submissions
                synchronized (data) {
                    nextJobNode.getPreJobNodes().remove(this);
                    if (nextJobNode.getPreJobNodes().isEmpty()) {
                        ChannelRunCore.submit(nextJobNode);
                    }
                }
            });

        }
    }

}

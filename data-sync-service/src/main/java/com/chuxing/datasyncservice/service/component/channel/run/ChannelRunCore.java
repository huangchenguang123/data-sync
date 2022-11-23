package com.chuxing.datasyncservice.service.component.channel.run;

import com.chuxing.datasyncservice.service.flow.Flow;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @date 2022/11/9 14:58
 * @author huangchenguang
 * @desc ChannelRunCore
 */
public class ChannelRunCore {

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
     * @date 2022/11/9 15:12
     * @author huangchenguang
     * @desc submit first job to thread pool
     */
    public static void submit(Flow flow, Map<String, Object> data) {
        List<JobNode> job = initJob(flow, data);
        job.forEach(threadPoolExecutor::submit);
    }

    /**
     * @date 2022/11/10 16:39
     * @author huangchenguang
     * @desc submit running task to thread pool
     */
    public static void submit(JobNode jobNode) {
        threadPoolExecutor.submit(jobNode);
    }

    /**
     * @date 2022/11/10 16:25
     * @author huangchenguang
     * @desc init job
     */
    private static List<JobNode> initJob(Flow flow, Map<String, Object> data) {
        // init jobNodeMap
        Map<Integer, JobNode> jobNodeMap = flow.getBaseChannels().values().stream().map(baseChannel -> {
            JobNode jobNode = new JobNode();
            jobNode.setBaseChannel(baseChannel);
            jobNode.setData(data);
            return jobNode;
        }).collect(Collectors.toMap(jobNode -> jobNode.getBaseChannel().getId(), Function.identity()));
        // init preJobNodes and nextJobNodes
        flow.getBaseChannels().values().forEach(baseChannel -> {
            // init preJobNodes
            List<JobNode> preJobNodes = Lists.newArrayList();
            baseChannel.getPreChannelIds().forEach(preChannelId -> preJobNodes.add(jobNodeMap.get(preChannelId)));
            // init nextJobNodes
            List<JobNode> nextJobNodes = Lists.newArrayList();
            baseChannel.getNextChannelIds().forEach(nextChannelId -> nextJobNodes.add(jobNodeMap.get(nextChannelId)));
            // set preJobNodes and nextJobNodes
            jobNodeMap.get(baseChannel.getId()).setPreJobNodes(preJobNodes);
            jobNodeMap.get(baseChannel.getId()).setNextJobNodes(nextJobNodes);
        });
        // int remainingJobNodes
        List<JobNode> remainingJobNodes = Lists.newArrayList(jobNodeMap.values());
        jobNodeMap.values().forEach(jobNode -> jobNode.setRemainingJobNodes(remainingJobNodes));

        // find root job node
        return jobNodeMap.values().stream().filter(jobNode -> jobNode.getPreJobNodes().isEmpty()).collect(Collectors.toList());
    }

}

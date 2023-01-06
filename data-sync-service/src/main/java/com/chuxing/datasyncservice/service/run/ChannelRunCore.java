package com.chuxing.datasyncservice.service.run;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.context.Context;
import com.chuxing.datasyncservice.service.flow.Flow;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.BooleanUtils;

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
     * @desc threadPoolExecutor
     */
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
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
     * @desc execute first job to thread pool
     */
    public static void execute(Flow flow, Map<String, Object> data, Context context) {
        List<JobNode> job = initJob(flow, data, context, false, null, false);
        job.forEach(THREAD_POOL_EXECUTOR::execute);
    }

    /**
     * @date 2022/11/10 16:39
     * @author huangchenguang
     * @desc execute running task to thread pool
     */
    public static void execute(JobNode jobNode) {
        THREAD_POOL_EXECUTOR.execute(jobNode);
    }

    /**
     * @date 2023/1/6 09:41
     * @author huangchenguang
     * @desc execute shadow job to thread pool
     */
    public static void executeShadow(Flow flow, Map<String, Object> data, Context context, Map<String, Object> trueData, Boolean isSwitch) {
        List<JobNode> job = initJob(flow, data, context, true, trueData, isSwitch);
        job.forEach(THREAD_POOL_EXECUTOR::execute);
    }

    /**
     * @date 2022/11/10 16:25
     * @author huangchenguang
     * @desc init job
     */
    @SuppressWarnings("unchecked")
    private static List<JobNode> initJob(Flow flow, Map<String, Object> data, Context context, Boolean isShadow, Map<String, Object> trueData, Boolean isSwitch) {
        Map<Integer, BaseChannel> channelsMap = BooleanUtils.isNotTrue(isShadow) ? flow.getBaseChannels() : flow.getShadowChannels();
        // init jobNodeMap
        Map<Integer, JobNode> jobNodeMap = channelsMap.values().stream().map(baseChannel -> {
            JobNode jobNode = new JobNode();
            jobNode.setBaseChannel(baseChannel);
            jobNode.setData(JSON.parseObject(JSON.toJSONString(data), Map.class));
            jobNode.setInput(data);
            jobNode.setContext(context);
            jobNode.setIsShadow(isShadow);
            jobNode.setTrueData(trueData);
            jobNode.setIsSwitch(isSwitch);
            return jobNode;
        }).collect(Collectors.toMap(jobNode -> jobNode.getBaseChannel().getId(), Function.identity(), (a, b) -> a));
        // init preJobNodes and nextJobNodes
        channelsMap.values().forEach(baseChannel -> {
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

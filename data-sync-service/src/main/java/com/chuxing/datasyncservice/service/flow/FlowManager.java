package com.chuxing.datasyncservice.service.flow;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ChannelConfig;
import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.chuxing.datasyncservice.model.config.FlowConfig;
import com.chuxing.datasyncservice.model.dto.FlowDTO;
import com.chuxing.datasyncservice.service.FlowService;
import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.component.sink.BaseSink;
import com.chuxing.datasyncservice.service.component.source.BaseSource;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @date 2022/10/24 16:07
 * @author huangchenguang
 * @desc FlowManager
 */
@Slf4j
@DependsOn("springUtils")
@Component
public class FlowManager {

    @Resource
    private FlowService flowService;

    /**
     * @date 2022/10/28 10:56
     * @desc flow map
     */
    private Map<String, Flow> flowMap;

    /**
     * @date 2022/10/24 16:21
     * @author huangchenguang
     * @desc init
     */
    @PostConstruct
    private void init() {
        initAllFlow();
        startAllFlow();
        addFlowStopHook();
    }

    /**
     * @date 2022/10/24 19:46
     * @author huangchenguang
     * @desc init all flow
     */
    private void initAllFlow() {
        // init flow map
        flowMap = Maps.newConcurrentMap();

        // init component
        List<FlowDTO> flows = flowService.getAllFlow();
        for (FlowDTO flowDTO : flows) {
            try {
                initFlow(flowDTO);
            } catch (Exception e) {
                log.error("[FlowManager.initAllFlow] init flow fail, flow={}", JSON.toJSONString(flowDTO), e);
            }
        }
    }

    /**
     * @date 2022/12/7 10:42
     * @author huangchenguang
     * @desc init flow
     */
    public void initFlow(FlowDTO flowDTO) {
        FlowConfig flowConfig = JSON.parseObject(flowDTO.getConfig(), FlowConfig.class);
        // init sources
        Map<Integer, BaseSource> sources = Maps.newConcurrentMap();
        for (ComponentConfig config : flowConfig.getSources()) {
            BaseSource baseSource = BaseSource.init(config);
            sources.put(baseSource.getId(), baseSource);
        }
        // init channels
        Map<Integer, BaseChannel> channels = Maps.newConcurrentMap();
        for (ChannelConfig config : flowConfig.getChannels()) {
            BaseChannel baseChannel = BaseChannel.init(config);
            channels.put(baseChannel.getId(), baseChannel);
        }
        // init sinks
        Map<Integer, BaseSink> sinks = Maps.newConcurrentMap();
        for (ComponentConfig config : flowConfig.getSinks()) {
            BaseSink baseSink = BaseSink.init(config);
            sinks.put(baseSink.getId(), baseSink);
        }
        // init flow
        Flow flow = new Flow(flowDTO.getFlowName(), sources, channels, sinks);
        flowMap.put(flow.getFlowName(), flow);
    }

    /**
     * @date 2022/10/28 14:06
     * @author huangchenguang
     * @desc start all flow
     */
    private void startAllFlow() {
        // start flow
        flowMap.values().forEach(Flow::start);
    }

    /**
     * @date 2022/12/7 10:42
     * @author huangchenguang
     * @desc start flow
     */
    public void startFlow(FlowDTO flowDTO) {
        Flow flow = flowMap.get(flowDTO.getFlowName());
        if (Objects.nonNull(flow)) {
            flow.start();
        }
    }

    /**
     * @date 2022/11/7 15:36
     * @author huangchenguang
     * @desc add flow stop hook
     */
    private void addFlowStopHook() {
        // add hook to jvm shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(this::stopAllFlow));
    }

    /**
     * @date 2022/12/7 10:45
     * @author huangchenguang
     * @desc stop all flow
     */
    private void stopAllFlow() {
        flowMap.values().forEach(Flow::stop);
    }

    /**
     * @date 2022/12/7 10:45
     * @author huangchenguang
     * @desc stop flow
     */
    public void stopFlow(FlowDTO flowDTO) {
        Flow flow = flowMap.get(flowDTO.getFlowName());
        if (Objects.nonNull(flow)) {
            flow.stop();
        }
    }

}

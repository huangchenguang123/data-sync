package com.chuxing.datasyncservice.service.flow;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.dao.FlowDAO;
import com.chuxing.datasyncservice.model.config.ChannelConfig;
import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.chuxing.datasyncservice.model.config.FlowConfig;
import com.chuxing.datasyncservice.model.dto.FlowDTO;
import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.component.sink.BaseSink;
import com.chuxing.datasyncservice.service.component.source.BaseSource;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @date 2022/10/24 16:07
 * @author huangchenguang
 * @desc FlowManager
 */
@Component
public class FlowManager {

    /**
     * @date 2022/10/24 16:47
     * @author huangchenguang
     * @desc flowDAO
     */
    @Resource
    private FlowDAO flowDAO;

    /**
     * @date 2022/10/28 10:56
     * @author huangchenguang
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
        initFlow();
        startFlow();
        stopFlow();
    }

    /**
     * @date 2022/10/24 19:46
     * @author huangchenguang
     * @desc init source
     */
    private void initFlow() {
        // init flow map
        flowMap = Maps.newConcurrentMap();

        // init component
        List<FlowDTO> flows = flowDAO.getAllFlow();
        for (FlowDTO flowDTO : flows) {
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
    }

    /**
     * @date 2022/10/28 14:06
     * @author huangchenguang
     * @desc start flow
     */
    private void startFlow() {
        // start flow
        flowMap.values().forEach(Flow::start);
    }

    /**
     * @date 2022/11/7 15:36
     * @author huangchenguang
     * @desc stop flow
     */
    private void stopFlow() {
        // add hook to jvm shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> flowMap.values().forEach(Flow::stop)));
    }

}

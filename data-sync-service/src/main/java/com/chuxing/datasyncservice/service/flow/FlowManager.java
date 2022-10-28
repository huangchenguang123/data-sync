package com.chuxing.datasyncservice.service.flow;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.dao.FlowDAO;
import com.chuxing.datasyncservice.model.config.FlowConfig;
import com.chuxing.datasyncservice.model.dto.FlowDTO;
import com.chuxing.datasyncservice.service.component.channel.BaseChannel;
import com.chuxing.datasyncservice.service.component.source.BaseSource;
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
    }

    /**
     * @date 2022/10/24 19:46
     * @author huangchenguang
     * @desc init source
     */
    private void initFlow() {
        List<FlowDTO> flows = flowDAO.getAllFlow();
        for (FlowDTO flow : flows) {
//            FlowConfig flowConfig = JSON.parseObject(flow.getConfig(), FlowConfig.class);
//            BaseSource baseSource =  BaseSource.init(flowConfig);
//            BaseChannel baseChannel = BaseChannel.init(flowConfig);
//            flowMap.put(flow.getFlowName(), new Flow(baseSource, baseChannel));
        }
    }

    /**
     * @date 2022/10/28 14:06
     * @author huangchenguang
     * @desc start flow
     */
    private void startFlow() {
        flowMap.values().forEach(Flow::start);
    }

}

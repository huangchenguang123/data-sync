package com.chuxing.datasyncservice.service.component;

import com.chuxing.datasyncservice.dao.ComponentDAO;
import com.chuxing.datasyncservice.dao.ConfigDAO;
import com.chuxing.datasyncservice.model.dto.ComponentDTO;
import com.chuxing.datasyncservice.model.dto.ConfigDTO;
import com.chuxing.datasyncservice.model.enums.ComponentEnum;
import com.chuxing.datasyncservice.service.component.source.Source;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @date 2022/10/24 16:07
 * @author huangchenguang
 * @desc ComponentManager
 */
@Component
public class ComponentManager {

    /**
     * @date 2022/10/24 16:22
     * @author huangchenguang
     * @desc componentManager
     */
    @Resource
    private ComponentDAO componentDAO;

    /**
     * @date 2022/10/24 16:47
     * @author huangchenguang
     * @desc configDAO
     */
    @Resource
    private ConfigDAO configDAO;

    private List<Source> sources;

    /**
     * @date 2022/10/24 16:21
     * @author huangchenguang
     * @desc init
     */
    @PostConstruct
    private void init() {
        initSource();
        startSource();
    }

    /**
     * @date 2022/10/24 19:46
     * @author huangchenguang
     * @desc init source
     */
    private void initSource() {
        // init source
        sources = Lists.newArrayList();
        // add sources
        List<ComponentDTO> components = componentDAO.getAllComponent();
        for (ComponentDTO componentDTO : components) {
            List<ConfigDTO> configs = configDAO.getByComponentId(componentDTO.getId());
            Map<String, ConfigDTO> configMap = configs.stream().collect(Collectors.toMap(ConfigDTO::getConfigType, Function.identity()));
            if (configMap.containsKey(ComponentEnum.SINK.getName())) {
            }
            if (configMap.containsKey(ComponentEnum.CHANNEL.getName())) {
            }
            if (configMap.containsKey(ComponentEnum.SOURCE.getName())) {
                ConfigDTO config = configMap.get(ComponentEnum.SOURCE.getName());
                Source source = Source.init(config.getSubType(), config.getConfig());
                if (Objects.nonNull(source)) {
                    sources.add(source);
                }
            }
        }
    }

    /**
     * @date 2022/10/24 19:46
     * @author huangchenguang
     * @desc start source
     */
    private void startSource() {
        sources.forEach(Source::start);
    }

}

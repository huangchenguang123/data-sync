package com.chuxing.datasyncservice.service.function;

import com.chuxing.datasyncservice.model.dto.FunctionDTO;
import com.chuxing.datasyncservice.service.FunctionService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @date 2022/12/19 15:53
 * @author huangchenguang
 * @desc FunctionManage
 */
@Component
public class FunctionManager {

    @Resource
    private FunctionService functionService;

    /**
     * @date 2022/12/19 15:55
     * @desc functionMap
     */
    private static final Map<String, String> FUNCTION_MAP = Maps.newConcurrentMap();

    /**
     * @date 2022/12/19 16:37
     * @author huangchenguang
     * @desc init
     */
    @PostConstruct
    private void init() {
        FUNCTION_MAP.putAll(functionService.getAllFunction().stream().collect(Collectors.toMap(FunctionDTO::getFunctionName, FunctionDTO::getScript, (a, b) -> a)));
    }

    /**
     * @date 2022/12/19 15:58
     * @author huangchenguang
     * @desc get script by name
     */
    public static String getScript(String name) {
        if (FUNCTION_MAP.containsKey(name)) {
            return FUNCTION_MAP.get(name);
        }
        throw new RuntimeException("get script fail");
    }

}

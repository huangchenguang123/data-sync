package com.chuxing.datasyncservice.service.component.channel;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.service.function.FunctionManager;
import com.google.common.collect.Maps;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @date 2022/12/19 11:15
 * @author huangchenguang
 * @desc FunctionChannel
 */
@Slf4j
@Getter
@Setter
public class FunctionChannel extends BaseChannel {

    /**
     * @date 2022/10/28 10:16
     * @desc channel run flag
     */
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * @date 2022/12/19 16:06
     * @author huangchenguang
     * @desc runner
     */
    private ExpressRunner runner = new ExpressRunner();

    /**
     * @date 2022/12/19 15:46
     * @author huangchenguang
     * @desc functions
     */
    private List<Function> functions;

    /**
     * @date 2022/12/19 15:43
     * @author huangchenguang
     * @desc Function
     */
    @Data
    private static class Function {

        /**
         * @date 2022/12/19 15:45
         * @author huangchenguang
         * @desc function name
         */
        private String functionName;

        /**
         * @date 2022/12/19 15:45
         * @author huangchenguang
         * @desc params
         */
        private List<String> params;

        /**
         * @date 2022/12/19 15:50
         * @author huangchenguang
         * @desc resultName
         */
        private String resultName;

    }

    /**
     * @date 2022/12/19 15:49
     * @author huangchenguang
     * @desc start channel
     */
    @Override
    public void start() {
        isRunning.set(true);
    }

    /**
     * @date 2022/12/19 15:49
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
    @SuppressWarnings("unchecked")
    public void run(Map<String, Object> data) {
        if (isRunning.get()) {
            functions.forEach(function -> {
                try {
                    String script = FunctionManager.getScript(function.getFunctionName());
                    List<Object> objects = function.getParams().stream().map(param -> {
                        // get param
                        String[] fromNames = param.split("\\.");
                        Object current = data;
                        for (String name : fromNames) {
                            try {
                                current = ((Map<String, Object>) current).get(name);
                                if (Objects.isNull(current)) {
                                    break;
                                }
                            } catch (Exception e) {
                                log.error("[FunctionChannel.run] mapping error, current must be a map, current={}", current, e);
                            }
                        }
                        return current;
                    }).collect(Collectors.toList());

                    // set result
                    String[] toNames = function.getResultName().split("\\.");
                    Object current = data;
                    for (int i = 0; i < toNames.length; i++) {
                        try {
                            if (!((Map<String, Object>) current).containsKey(toNames[i])) {
                                if (i == toNames.length - 1) {
                                    String code = String.format("%s(%s);", function.getFunctionName(), StringUtils.join(objects, ","));
                                    String runCode = String.format("%s%s", script, code);
                                    DefaultContext<String, Object> context = new DefaultContext<>();
                                    // init context
                                    context.putAll(data);
                                    ((Map<String, Object>) current).put(function.getResultName(), runner.execute(runCode, context, null, false, false));
                                } else {
                                    ((Map<String, Object>) current).put(toNames[i], Maps.newHashMap());
                                }
                            }
                            current = ((Map<String, Object>) current).get(toNames[i]);
                        } catch (Exception e) {
                            log.error("[MappingChannel.run] mapping error, current must be a map, current={}", current);
                            throw new RuntimeException(e.getCause());
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}

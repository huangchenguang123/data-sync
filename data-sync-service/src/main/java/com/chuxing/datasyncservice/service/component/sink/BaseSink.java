package com.chuxing.datasyncservice.service.component.sink;

import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.chuxing.datasyncservice.model.enums.SinkEnum;
import com.chuxing.datasyncservice.service.flow.Flow;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @date 2022/11/23 15:28
 * @author huangchenguang
 * @desc BaseSink
 */
@Data
public abstract class BaseSink {

    /**
     * @date 2022/11/10 16:12
     * @desc
     */
    private Flow flow;

    /**
     * @date 2022/10/28 14:14
     * @desc type
     */
    private String type;

    /**
     * @date 2022/11/9 15:28
     * @desc id
     */
    private Integer id;

    /**
     * @date 2022/11/23 16:08
     * @author huangchenguang
     * @desc init sink
     */
    public static BaseSink init(ComponentConfig config) {
        BaseSink baseSink;
        if (Objects.equals(config.getType(), SinkEnum.CONSOLE_SINK.getName())) {
            baseSink = ConsoleSink.init(config);
        } else if (Objects.equals(config.getType(), SinkEnum.HTTP_SINK.getName())) {
            baseSink = HttpSink.init(config);
        } else {
            throw new RuntimeException("sink type not support");
        }
        baseSink.setId(config.getId());
        baseSink.setType(config.getType());
        return baseSink;
    }

    /**
     * start sink
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void start();

    /**
     * stop sink
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void stop();

    /**
     * run
     *
     * @date 2022/10/27 17:19
     * @author huangchenguang
     * @param data all data
     */
    public abstract void run(Map<String, Object> data);

}

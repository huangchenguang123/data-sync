package com.chuxing.datasyncservice.service.component.source;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ComponentConfig;
import com.chuxing.datasyncservice.model.enums.SourceEnum;
import com.chuxing.datasyncservice.service.run.ChannelRunCore;
import com.chuxing.datasyncservice.service.flow.Flow;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @date 2022/10/20 17:16
 * @author huangchenguang
 * @desc the source is the entry point to the data
 */
@Data
public abstract class BaseSource {

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
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init source
     */
    public static BaseSource init(ComponentConfig config) {
        BaseSource baseSource;
        if (Objects.equals(config.getType(), SourceEnum.NSQ_SOURCE.getName())) {
            baseSource = JSON.parseObject(JSON.toJSONString(config.getConfig()), NsqSource.class);
        } else if (Objects.equals(config.getType(), SourceEnum.HTTP_SOURCE.getName())) {
            baseSource = JSON.parseObject(JSON.toJSONString(config.getConfig()), HttpSource.class);
        } else {
            throw new RuntimeException("source type is not supported");
        }
        baseSource.setId(config.getId());
        baseSource.setType(config.getType());
        return baseSource;
    }

    /**
     * start source
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void start();

    /**
     * stop source
     *
     * @date 2022/10/25 15:42
     * @author huangchenguang
     */
    public abstract void stop();

    /**
     * @date 2022/11/7 15:22
     * @author huangchenguang
     * @desc source run
     */
    public void run(Map<String, Object> data) {
        ChannelRunCore.execute(flow, data);
    }

}

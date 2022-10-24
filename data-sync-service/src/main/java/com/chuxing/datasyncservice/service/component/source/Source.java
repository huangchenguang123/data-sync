package com.chuxing.datasyncservice.service.component.source;

import com.chuxing.datasyncservice.model.enums.SourceEnum;
import lombok.Data;

/**
 * @date 2022/10/20 17:16
 * @author huangchenguang
 * @desc the source is the entry point to the data
 */
@Data
public abstract class Source {

    protected String type;

    /**
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init source
     */
    public static Source init(String subType, String config) {
        if (SourceEnum.NSQ_SOURCE.getName().equals(subType)) {
            return NsqSource.init(config);
        }
        return null;
    }

    public abstract void start();

    public abstract void stop();


}

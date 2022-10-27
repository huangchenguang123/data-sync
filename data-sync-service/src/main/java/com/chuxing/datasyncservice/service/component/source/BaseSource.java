package com.chuxing.datasyncservice.service.component.source;

import com.chuxing.datasyncservice.model.enums.SourceEnum;
import lombok.Data;

/**
 * @date 2022/10/20 17:16
 * @author huangchenguang
 * @desc the source is the entry point to the data
 */
@Data
public abstract class BaseSource {

    protected String type;

    /**
     * @date 2022/10/20 17:27
     * @author huangchenguang
     * @desc init source
     */
    public static BaseSource init(String subType, String config) {
        if (SourceEnum.NSQ_SOURCE.getName().equals(subType)) {
            return NsqSource.init(config);
        }
        return null;
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


}

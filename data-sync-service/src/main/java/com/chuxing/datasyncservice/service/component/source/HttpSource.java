package com.chuxing.datasyncservice.service.component.source;

import com.chuxing.datasyncservice.service.run.HttpSourceRunCore;
import com.chuxing.datasyncservice.utils.SpringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @date 2022/12/16 14:54
 * @author huangchenguang
 * @desc http source
 */
@Getter
@Setter
public class HttpSource extends BaseSource {

    /**
     * @date 2022/12/16 14:55
     * @desc url
     */
    private String url;

    /**
     * @date 2022/12/21 15:01
     * @desc async
     */
    private Boolean async;

    /**
     * @date 2023/1/5 14:37
     * @author huangchenguang
     * @desc timeout
     */
    private Long timeout;

    /**
     * @date 2022/12/16 15:39
     * @author huangchenguang
     * @desc start
     */
    @Override
    public void start() {
        HttpSourceRunCore httpSourceRunCore = SpringUtils.getBean(HttpSourceRunCore.class);
        httpSourceRunCore.register(this);
    }

    /**
     * @date 2022/12/16 15:39
     * @author huangchenguang
     * @desc stop
     */
    @Override
    public void stop() {
        HttpSourceRunCore httpSourceRunCore = SpringUtils.getBean(HttpSourceRunCore.class);
        httpSourceRunCore.unregister(this);
    }

}

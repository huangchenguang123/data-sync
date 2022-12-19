package com.chuxing.datasyncservice.service.component.source;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ComponentConfig;
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
     * @author huangchenguang
     * @desc url
     */
    private String url;

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

package com.chuxing.datasyncservice.service.run;

import com.chuxing.datasyncservice.model.rpc.common.Result;
import com.chuxing.datasyncservice.service.component.source.HttpSource;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @date 2022/12/16 15:26
 * @author huangchenguang
 * @desc http source run core
 */
@RestController
public class HttpSourceRunCore {

    /**
     * @date 2022/12/16 15:28
     * @author huangchenguang
     * @desc http source proxy
     */
    private Map<String, HttpSource> proxy;

    /**
     * @date 2022/12/16 15:30
     * @author huangchenguang
     * @desc init
     */
    @PostConstruct
    private void init() {
        proxy = Maps.newConcurrentMap();
    }

    /**
     * @date 2022/12/16 15:27
     * @author huangchenguang
     * @desc input core
     */
    @RequestMapping("/source/{path}")
    public Result<Boolean> input(@PathVariable String path, @RequestBody Map<String, Object> data) {
        if (proxy.containsKey(path)) {
            proxy.get(path).run(data);
            return Result.success(true);
        } else {
            return Result.fail("unknown path, please check your url");
        }
    }

    /**
     * @date 2022/12/16 15:37
     * @author huangchenguang
     * @desc register
     */
    public void register(HttpSource httpSource) {
        proxy.put(httpSource.getUrl(), httpSource);
    }

    /**
     * @date 2022/12/16 15:39
     * @author huangchenguang
     * @desc unregister
     */
    public void unregister(HttpSource httpSource) {
        proxy.remove(httpSource.getUrl());
    }

}

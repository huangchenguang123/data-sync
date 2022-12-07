package com.chuxing.datasyncservice.service.component.sink;

import com.alibaba.fastjson2.JSON;
import com.chuxing.datasyncservice.model.config.ComponentConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @date 2022/12/7 11:18
 * @author huangchenguang
 * @desc HttpSink
 */
@Slf4j
@Getter
@Setter
public class HttpSink extends BaseSink {

    /**
     * @date 2022/10/28 10:16
     * @desc channel run flag
     */
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    /**
     * @date 2022/12/7 11:26
     * @desc http url
     */
    private String url;

    /**
     * @date 2022/12/7 11:35
     * @desc okhttp client
     */
    private OkHttpClient okHttpClient;

    /**
     * @date 2022/11/23 16:09
     * @author huangchenguang
     * @desc init http sink
     */
    public static BaseSink init(ComponentConfig config) {
        return JSON.parseObject(JSON.toJSONString(config.getConfig()), HttpSink.class);
    }

    /**
     * @date 2022/12/7 11:20
     * @author huangchenguang
     * @desc start sink
     */
    @Override
    public void start() {
        okHttpClient = new OkHttpClient();
        isRunning.set(true);
    }

    /**
     * @date 2022/12/7 11:22
     * @author huangchenguang
     * @desc stop sink
     */
    @Override
    public void stop() {
        isRunning.set(false);
    }

    /**
     * @date 2022/12/7 11:24
     * @author huangchenguang
     * @desc run
     */
    @Override
    public void run(Map<String, Object> data) {
        if (isRunning.get()) {
            RequestBody body = RequestBody.create(JSON.toJSONString(data), MediaType.parse("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                ResponseBody responseBody = response.body();
                if (Objects.nonNull(responseBody)) {
                    System.out.println(responseBody.string());
                }
            } catch (Exception e) {
                log.error("[HttpSink.run] post error, params={}", JSON.toJSONString(data), e);
            }
        }
    }

}

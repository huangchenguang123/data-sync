package com.chuxing.datasyncservice.service.context;

import lombok.Data;
import org.apache.commons.lang3.BooleanUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @date 2022/12/21 15:11
 * @author huangchenguang
 * @desc Context
 */
@Data
public class Context {

    /**
     * @date 2022/12/21 15:30
     * @desc successful
     */
    private volatile Boolean successful;

    /**
     * @date 2022/12/21 15:37
     * @desc result
     */
    private volatile Object result;

    /**
     * @date 2023/1/5 14:36
     * @author huangchenguang
     * @desc startTimes
     */
    private Long startTimes;

    /**
     * @date 2023/1/5 14:47
     * @author huangchenguang
     * @desc countDownLatch
     */
    private CountDownLatch countDownLatch;

    /**
     * @date 2022/12/21 15:31
     * @author huangchenguang
     * @desc init context
     */
    public static Context init() {
        Context context = new Context();
        context.startTimes = System.currentTimeMillis();
        return context;
    }

    /**
     * @date 2023/1/5 14:49
     * @author huangchenguang
     * @desc init countDownLatch
     */
    public void initCountDownLatch(int count) {
        this.countDownLatch = new CountDownLatch(count);
    }

    /**
     * @date 2023/1/5 14:51
     * @author huangchenguang
     * @desc countDown
     */
    public void countDown() {
        countDownLatch.countDown();;
    }

    /**
     * @date 2022/12/22 09:39
     * @author huangchenguang
     * @desc success
     */
    public void success(Object result) {
        if (BooleanUtils.isNotFalse(successful)) {
            this.result = result;
            successful = true;
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @date 2022/12/22 09:39
     * @author huangchenguang
     * @desc fail
     */
    public void fail(String result) {
        this.result = result;
        successful = false;
    }

}

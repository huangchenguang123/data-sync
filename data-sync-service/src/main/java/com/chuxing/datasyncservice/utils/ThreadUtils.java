package com.chuxing.datasyncservice.utils;

/**
 * @date 2022/12/21 15:36
 * @author huangchenguang
 * @desc ThreadUtils
 */
public class ThreadUtils {

    /**
     * @date 2022/12/21 15:36
     * @author huangchenguang
     * @desc sleep
     */
    public static void sleep(Long times) {
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

package com.chuxing.datasyncservice.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @date 2022/12/16 15:33
 * @author huangchenguang
 * @desc spring utils
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    /**
     * @date 2022/12/16 15:34
     * @desc applicationContext
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * @date 2022/12/16 15:35
     * @author huangchenguang
     * @desc get bean
     */
    public static  <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}

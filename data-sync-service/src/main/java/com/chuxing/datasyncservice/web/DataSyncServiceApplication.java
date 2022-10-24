package com.chuxing.datasyncservice.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @date 2022/10/24 19:55
 * @author huangchenguang
 * @desc DataSyncServiceApplication
 */
@SpringBootApplication(scanBasePackages = "com.chuxing.datasyncservice")
@MapperScan("com.chuxing.datasyncservice.dao")
public class DataSyncServiceApplication {

    /**
     * @date 2022/10/24 19:55
     * @author huangchenguang
     * @desc start
     */
    public static void main(String[] args) {
        SpringApplication.run(DataSyncServiceApplication.class, args);
    }

}

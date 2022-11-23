package com.chuxing.datasyncservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @date 2022/11/23 16:03
 * @author huangchenguang
 * @desc SinkEnum
 */
@Getter
@AllArgsConstructor
public enum SinkEnum {

    /**
     * @date 2022/11/23 16:04
     * @author huangchenguang
     * @desc ConsoleSink
     */
    CONSOLE_SINK("ConsoleSink"),

    ;

    /**
     * @date 2022/10/24 19:36
     * @author huangchenguang
     * @desc name
     */
    private String name;

}

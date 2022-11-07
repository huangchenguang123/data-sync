package com.chuxing.datasyncservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @date 2022/10/28 14:40
 * @author huangchenguang
 * @desc ChannelEnum
 */
@Getter
@AllArgsConstructor
public enum ChannelEnum {

    /**
     * @date 2022/11/7 15:00
     * @author huangchenguang
     * @desc ExpressChannel
     */
    EXPRESS_CHANNEL("ExpressChannel"),

    ;

    /**
     * @date 2022/10/24 19:36
     * @author huangchenguang
     * @desc name
     */
    private String name;

}
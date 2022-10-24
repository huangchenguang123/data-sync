package com.chuxing.datasyncservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @date 2022/10/24 16:56
 * @author huangchenguang
 * @desc ComponentEnum
 */
@Getter
@AllArgsConstructor
public enum ComponentEnum {

    /**
     * @date 2022/10/24 16:57
     * @author huangchenguang
     * @desc Source
     */
    SOURCE("Source"),

    /**
     * @date 2022/10/24 16:57
     * @author huangchenguang
     * @desc Channel
     */
    CHANNEL("Channel"),

    /**
     * @date 2022/10/24 16:57
     * @author huangchenguang
     * @desc Sink
     */
    SINK("Sink");

    ;

    /**
     * @date 2022/10/24 16:56
     * @author huangchenguang
     * @desc name
     */
    private String name;

}

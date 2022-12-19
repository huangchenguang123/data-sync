package com.chuxing.datasyncservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @date 2022/10/24 19:35
 * @author huangchenguang
 * @desc SourceEnum
 */
@Getter
@AllArgsConstructor
public enum SourceEnum {

    /**
     * @date 2022/10/24 19:36
     * @desc NsqSource
     */
    NSQ_SOURCE("NsqSource"),

    /**
     * @date 2022/10/24 19:36
     * @desc HttpSource
     */
    HTTP_SOURCE("HttpSource"),

    ;

    /**
     * @date 2022/10/24 19:36
     * @desc name
     */
    private final String name;

}

package com.chuxing.datasyncservice.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @date 2022/10/24 15:38
 * @author huangchenguang
 * @desc ComponentDTO
 */
@Data
public class ComponentDTO implements Serializable {

    /**
     * @date 2022/10/24 15:38
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 9186595410153455021L;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc id
     */
    private Long id;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc name
     */
    private String name;

    /**
     * @date 2022/10/24 15:34
     * @author huangchenguang
     * @desc create time
     */
    private Long createAt;

    /**
     * @date 2022/10/24 15:35
     * @author huangchenguang
     * @desc update time
     */
    private Long updateAt;

}

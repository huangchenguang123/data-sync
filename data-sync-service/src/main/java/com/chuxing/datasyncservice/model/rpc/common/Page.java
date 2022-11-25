package com.chuxing.datasyncservice.model.rpc.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @date 2022/11/25 15:44
 * @author huangchenguang
 * @desc page
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {

    /**
     * @date 2022/11/25 15:44
     * @author huangchenguang
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 5752105586818617285L;

    /**
     * @date 2022/11/25 15:47
     * @author huangchenguang
     * @desc currentPage
     */
    private int currentPage;

    /**
     * @date 2022/11/25 15:47
     * @author huangchenguang
     * @desc totalPage
     */
    private int totalPage;

    /**
     * @date 2022/11/25 15:47
     * @author huangchenguang
     * @desc data
     */
    private List<T> data;

}

package com.chuxing.datasyncservice.dao;

import com.chuxing.datasyncservice.model.dto.FunctionDTO;

import java.util.List;

/**
 * @date 2022/12/19 16:26
 * @author huangchenguang
 * @desc FunctionDAO
 */
public interface FunctionDAO {

    /**
     * get all function
     *
     * @date 2022/10/24 15:45
     * @author huangchenguang
     * @return function list
     */
    List<FunctionDTO> getAllFunction();

}

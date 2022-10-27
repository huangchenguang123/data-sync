package com.chuxing.datasyncservice.dao;

import com.chuxing.datasyncservice.model.dto.ComponentDTO;

import java.util.List;

/**
 * @date 2022/10/24 15:53
 * @author huangchenguang
 * @desc ComponentDAO
 */
public interface ComponentDAO {

    /**
     * get all component
     *
     * @date 2022/10/24 15:45
     * @author huangchenguang
     * @return component list
     */
    List<ComponentDTO> getAllComponent();

}





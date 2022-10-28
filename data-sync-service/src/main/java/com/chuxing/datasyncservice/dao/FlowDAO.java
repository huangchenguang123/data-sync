package com.chuxing.datasyncservice.dao;

import com.chuxing.datasyncservice.model.dto.FlowDTO;

import java.util.List;

/**
 * @date 2022/10/24 15:53
 * @author huangchenguang
 * @desc FlowDAO
 */
public interface FlowDAO {

    /**
     * get all config
     *
     * @date 2022/10/24 15:45
     * @author huangchenguang
     * @return flow list
     */
    List<FlowDTO> getAllFlow();

}





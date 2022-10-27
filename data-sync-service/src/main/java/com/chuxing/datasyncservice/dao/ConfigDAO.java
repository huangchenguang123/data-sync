package com.chuxing.datasyncservice.dao;

import com.chuxing.datasyncservice.model.dto.ConfigDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @date 2022/10/24 15:53
 * @author huangchenguang
 * @desc ConfigDAO
 */
public interface ConfigDAO {

    /**
     * get by component id
     *
     * @date 2022/10/24 15:45
     * @author huangchenguang
     * @param componentId component id
     * @return config list
     */
    List<ConfigDTO> getByComponentId(@Param("componentId") Long componentId);

}





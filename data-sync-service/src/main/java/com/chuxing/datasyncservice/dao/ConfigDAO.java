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
     * @date 2022/10/24 15:45
     * @author huangchenguang
     * @desc get by component id
     */
    List<ConfigDTO> getByComponentId(@Param("componentId") Long componentId);

}





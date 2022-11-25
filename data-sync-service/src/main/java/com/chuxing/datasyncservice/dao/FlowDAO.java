package com.chuxing.datasyncservice.dao;

import com.chuxing.datasyncservice.model.dto.FlowDTO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * @date 2022/11/24 17:34
     * @author huangchenguang
     * @desc create flow
     */
    Integer createFlow(@Param("flow") FlowDTO flowDTO);

    /**
     * @date 2022/11/25 10:10
     * @author huangchenguang
     * @desc get flow
     */
    FlowDTO getFlow(@Param("flowId") Long flowId);

    /**
     * @date 2022/11/25 16:07
     * @author huangchenguang
     * @desc search
     */
    List<FlowDTO> searchFlow(@Param("flowName") String flowName, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * @date 2022/11/25 16:07
     * @author huangchenguang
     * @desc count
     */
    Integer countFlow(@Param("flowName") String flowName);

    /**
     * @date 2022/11/25 17:34
     * @author huangchenguang
     * @desc delete flow
     */
    Integer deleteFlow(@Param("flowId") Long flowId);

    /**
     * @date 2022/11/25 17:38
     * @author huangchenguang
     * @desc enable
     */
    Integer enableFlow(@Param("flowId") Long flowId, @Param("enable") Integer enable);

}





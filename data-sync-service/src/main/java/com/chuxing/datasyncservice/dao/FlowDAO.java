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
     * create flow
     *
     * @date 2022/11/24 17:34
     * @author huangchenguang
     * @param flowDTO flowDTO
     * @return create count number
     */
    Integer createFlow(@Param("flow") FlowDTO flowDTO);

    /**
     * update flow
     *
     * @date 2022/11/24 17:34
     * @author huangchenguang
     * @param flowDTO flowDTO
     * @return update count number
     */
    Integer updateFlow(@Param("flow") FlowDTO flowDTO);

    /**
     * get flow
     *
     * @date 2022/11/25 10:10
     * @author huangchenguang
     * @param flowId flowId
     * @return  flowDTO
     */
    FlowDTO getFlow(@Param("flowId") Long flowId);

    /**
     * search flow
     *
     * @date 2022/11/25 16:07
     * @author huangchenguang
     * @param flowName flowName
     * @param offset page from
     * @param limit page size
     * @return search flow list
     */
    List<FlowDTO> searchFlow(@Param("flowName") String flowName, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * count
     *
     * @date 2022/11/25 16:07
     * @author huangchenguang
     * @param flowName flowName
     * @return  count number
     */
    Integer countFlow(@Param("flowName") String flowName);

    /**
     * delete flow
     *
     * @date 2022/11/25 17:34
     * @author huangchenguang
     * @param flowId flowId
     * @return delete count number
     */
    Integer deleteFlow(@Param("flowId") Long flowId);

    /**
     * enable flow
     *
     * @date 2022/11/25 17:38
     * @author huangchenguang
     * @param flowId flowId
     * @param enable enable
     * @return enable count number
     */
    Integer enableFlow(@Param("flowId") Long flowId, @Param("enable") Integer enable);

}





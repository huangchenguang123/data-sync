package com.chuxing.datasyncservice.dao;

import com.chuxing.datasyncservice.model.dto.FunctionDTO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * get function
     *
     * @date 2022/12/22 10:19
     * @author huangchenguang
     * @param functionId function id
     * @return FunctionDTO
     */
    FunctionDTO getFunctionById(@Param("functionId") Long functionId);

    /**
     * search function
     *
     * @date 2022/11/25 16:07
     * @author huangchenguang
     * @param functionName functionName
     * @param offset page from
     * @param limit page size
     * @return search function list
     */
    List<FunctionDTO> searchFunction(@Param("functionName") String functionName, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * count
     *
     * @date 2022/11/25 16:07
     * @author huangchenguang
     * @param functionName functionName
     * @return  count number
     */
    Integer countFunction(@Param("functionName") String functionName);

    /**
     * create function
     *
     * @date 2022/11/24 17:34
     * @author huangchenguang
     * @param functionDTO functionDTO
     * @return create count number
     */
    Integer createFunction(@Param("functionDTO") FunctionDTO functionDTO);

    /**
     * update function
     *
     * @date 2022/11/24 17:34
     * @author huangchenguang
     * @param functionDTO functionDTO
     * @return update count number
     */
    Integer updateFunction(@Param("functionDTO") FunctionDTO functionDTO);

    /**
     * delete function
     *
     * @date 2022/12/22 10:19
     * @author huangchenguang
     * @param functionId function id
     * @return delete count
     */
    Integer deleteFunctionById(@Param("functionId") Long functionId);

}

package com.chuxing.datasyncservice.service;

import com.chuxing.datasyncservice.dao.FunctionDAO;
import com.chuxing.datasyncservice.model.dto.FunctionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2022/11/24 17:19
 * @author huangchenguang
 * @desc FlowService
 */
@Slf4j
@Service
public class FunctionService {

    @Resource
    private FunctionDAO functionDAO;

    /**
     * @date 2022/12/19 16:10
     * @author huangchenguang
     * @desc get all function
     */
    public List<FunctionDTO> getAllFunction() {
        return functionDAO.getAllFunction();
    }

}

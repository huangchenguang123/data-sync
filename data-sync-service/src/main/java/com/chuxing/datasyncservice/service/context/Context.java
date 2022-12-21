package com.chuxing.datasyncservice.service.context;

import lombok.Data;

/**
 * @date 2022/12/21 15:11
 * @author huangchenguang
 * @desc Context
 */
@Data
public class Context {

    /**
     * @date 2022/12/21 15:30
     * @author huangchenguang
     * @desc successful
     */
    private Boolean successful;

    /**
     * @date 2022/12/21 15:37
     * @author huangchenguang
     * @desc result
     */
    private Object result;

    /**
     * @date 2022/12/21 15:31
     * @author huangchenguang
     * @desc init context
     */
    public static Context init() {
        return new Context();
    }

    public void success(Object result) {
        this.result = result;
        successful = true;
    }

}

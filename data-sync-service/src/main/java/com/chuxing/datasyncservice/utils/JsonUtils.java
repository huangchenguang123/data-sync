package com.chuxing.datasyncservice.utils;

import com.alibaba.fastjson2.JSON;
import com.ke.diff.Diff;
import com.ke.diff.model.Result;

import java.util.List;

/**
 * @date 2023/1/5 15:50
 * @author huangchenguang
 * @desc JsonUtils
 */
public class JsonUtils {

    /**
     * @date 2023/1/5 15:55
     * @author huangchenguang
     * @desc diff view and output result
     */
    public static String diffView(String json1, String json2) {
        Diff diff = new Diff();
        List<Result> results = diff.diff(json1, json2);
        return JSON.toJSONString(results);
    }

}

package com.chuxing.datasyncservice.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * @date 2022/12/21 14:34
 * @author huangchenguang
 * @desc ObjectUtils
 */
@Slf4j
public class ObjectUtils {

    /**
     * @date 2022/12/21 14:37
     * @author huangchenguang
     * @desc set value
     */
    @SuppressWarnings("unchecked")
    public static void set(Map<String, Object> data, String objectPathName, Object value) {
        String[] names = objectPathName.split("\\.");
        Object current = data;
        for (int i = 0; i < names.length; i++) {
            try {
                if (i == names.length - 1) {
                    ((Map<String, Object>) current).put(names[i], value);
                } else {
                    if (!((Map<String, Object>) current).containsKey(names[i])) {
                        ((Map<String, Object>) current).put(names[i], Maps.newHashMap());
                    }
                    current = ((Map<String, Object>) current).get(names[i]);
                }
            } catch (Exception e) {
                log.error("[ObjectUtils.set] set error, current must be a map, current={}", current);
                throw new RuntimeException(e.getCause());
            }
        }
    }

    /**
     * @date 2022/12/21 14:37
     * @author huangchenguang
     * @desc get value
     */
    @SuppressWarnings("unchecked")
    public static Object get(Map<String, Object> data, String objectPathName) {
        String[] names = objectPathName.split("\\.");
        Object current = data;
        for (String name : names) {
            try {
                current = ((Map<String, Object>) current).get(name);
                if (Objects.isNull(current)) {
                    break;
                }
            } catch (Exception e) {
                log.error("[ObjectUtils.get] get error, current must be a map, current={}", current, e);
                throw new RuntimeException(e.getCause());
            }
        }
        return current;
    }

}

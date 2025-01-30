package com.example.studentBackend.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 将List数据根据某规则转换为Map格式
 */
public class ConvertUtils {
    public static <E, K, V> Map<K, V> convertToMap(List<E> list, Function<E, K> KeyFunc, Function<E, V> valueFunc) {
        Map<K, V> map = new HashMap<>();
        for (E entry: list) {
            V value = valueFunc.apply(entry);
            K key = KeyFunc.apply(entry);
            map.put(key, value);
        }
        return map;
    }
}

package com.apiversioning.utils;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author shadialsulami
 * @Since 8/25/17
 */
public class HashMapBuilder {

    private Map<String,Object> map;

    private HashMapBuilder(){
        map = Maps.newHashMap();
    }

    public static HashMapBuilder builder(){
        return new HashMapBuilder();
    }

    public HashMapBuilder put(String key,final Object value){
        map.put(key,value);
        return this;
    }

    public Map<String,Object> build(){
        return map;
    }
}

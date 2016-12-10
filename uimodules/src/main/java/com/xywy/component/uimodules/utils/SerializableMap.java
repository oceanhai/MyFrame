package com.xywy.component.uimodules.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by shijiazi on 16/1/25.
 * 封装的可以通过bundle传递的对象
 */
public class SerializableMap implements Serializable {

    private Map<String, Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}

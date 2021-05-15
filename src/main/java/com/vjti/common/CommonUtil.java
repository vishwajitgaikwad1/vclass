package com.vjti.common;

import com.vjti.constant.ApplicationConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 15/5/21.
 */
public class CommonUtil {

    public static Map<String, String> getPropertiesKeyValuePair(String properties) {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        if (StringUtil.isNotNullOrBlank(properties) && !ApplicationConstants.STR_NA.equalsIgnoreCase(properties)) {
            String[] keyValuepairs = properties.split("\t");
            for (String pair : keyValuepairs) {
                String key = pair.substring(0, pair.indexOf(":"));
                String value = pair.substring(pair.indexOf(":") + 1);
                propertiesMap.put(key, value);
            }
        }
        return propertiesMap;
    }
}

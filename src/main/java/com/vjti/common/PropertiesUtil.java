package com.vjti.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

/**
 * Created by vishwajit_gaikwad on 10/6/21.
 */
public class PropertiesUtil extends PropertyPlaceholderConfigurer {
    private static Map<String, String> propertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException
    {
        super.processProperties(beanFactory, props);

        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet())
        {
            String keyStr = key.toString();
//            propertiesMap.put(keyStr, parseStringValue(props.getProperty(keyStr), props, new HashSet()));
//            propertiesMap.put(keyStr,setPropertiesArray(props.getProperty(keyStr),props,new HashSet());)
        }
    }

    public static String getPropValue(String key)
    {
        return propertiesMap.get(key)!=null?propertiesMap.get(key).trim():null;
    }
}

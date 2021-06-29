package com.vjti.common;

import com.vjti.constant.ApplicationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 15/5/21.
 */
public class CommonUtil {
    private static Logger logger = LogManager.getLogger("CommonUtil");

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

    public static String generateBasicAuth(String userId, String password) {
        String plainCreds = userId + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        return "Basic " + base64Creds;
    }

    public static Object callGetterMethod(Class className, Object objectName, String fieldName) throws Exception {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = className.getDeclaredMethod("get" + methodName);
        Object res;
        try{
            res = method.invoke(objectName);
        }catch (Exception e){
            return null;
        }
        return res;
    }

    public static Boolean callSetterMethod(Class className, Object objectName, String fieldName, Class[] paramTypes, Object... args) throws Exception {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method;
        try{
            method = className.getDeclaredMethod("set" + methodName, paramTypes);
            method.invoke(objectName, args);
            return true;
        }catch (NoSuchMethodException e){
            logger.error("callSetterMethod method not found "+className.getName()+":"+methodName);
            return false;
        }
    }

    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }
}

package com.vjti.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
public class CommonWebUtil {


    public static Map<String,String> fetchCookie(String cookieLogin) {
        return CommonUtil.getPropertiesKeyValuePair(cookieLogin);
    }

    public static void createCookie(String cookieName, String cookieString, HttpServletResponse httpServletResponse){

        Cookie cookie = new Cookie(cookieName, cookieString);
        cookie.setMaxAge(7 * 24 * 24); //expires in 7 days
        cookie.setSecure(true);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    public static void deleteCookie(String cookieName, String cookieString, HttpServletResponse httpServletResponse){

        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0); //expires in 7 days
        cookie.setSecure(true);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }
}

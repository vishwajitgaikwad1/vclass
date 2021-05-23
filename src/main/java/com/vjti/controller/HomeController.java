package com.vjti.controller;

import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.LoginVO;
import com.vjti.model.TimetableVO;
import com.vjti.service.ILoginService;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 9/5/21.
 */
@Controller
public class HomeController {

    @Autowired
    ILoginService loginService;

    @Autowired
    IUserService userService;


    @RequestMapping("/testpage")
    public String testPage(Model model){
        return "testpage";
    }


    @RequestMapping({"/", "/login"})
    public  String showLogin(Model model,
                             @RequestParam(value = ApplicationConstants.ERRORPARAM, defaultValue = "") String error,
                             @CookieValue(name=ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie){
        if(!error.isEmpty()){
            model.addAttribute("ERROR_MESSAGE", "Invalid Credentials!");
        }
        else{
            if(loginCookie.length()>0){
                return "redirect:/authLogin";
            }
        }
        LoginVO loginVO = new LoginVO();
        model.addAttribute("loginVO",loginVO);
        return "login";
    }

    @RequestMapping("/logout")
    public String handleLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession(false);
        if (httpServletRequest.isRequestedSessionIdValid() && session != null) {
            session.invalidate();
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }
        return "redirect:/login";
    }


    @RequestMapping("/authLogin")
    public String authLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletResponse httpServletResponse,
                            @CookieValue(name = ApplicationConstants.COOKIE_LOGIN , defaultValue = "")String loginCookie){

        String role = "";
        if(loginCookie.equals("")){
            Map<String, String> loginResponse = loginService.findByLoginIdAndPassword(loginVO.getLoginId(), loginVO.getPassword());
            if(loginResponse!=null & loginResponse.get(ApplicationConstants.RESPONSE).equals(ApplicationConstants.SUCCESS)){
                CommonWebUtil.createCookie(ApplicationConstants.COOKIE_LOGIN, loginResponse.get(ApplicationConstants.COOKIE_LOGIN), httpServletResponse);
                role = userService.fetchUserRoleBySeq(Integer.valueOf(loginResponse.get(ApplicationConstants.USER_ROLE_MSTR_SEQ)));
            }
        }else {
            Map<String, String> loginCookieMap = CommonWebUtil.fetchCookie(loginCookie);
            role = userService.fetchUserRoleBySeq(Integer.valueOf(loginCookieMap.get(ApplicationConstants.USER_ROLE_MSTR_SEQ)));
        }

        if(role.equals(ApplicationConstants.STUDENT)){
            return "redirect:/student/";
        }
        else if(role.equals(ApplicationConstants.FACULTY)){
            return "redirect:/faculty/";
        }
     return "redirect:/login?error=true";
    }


}

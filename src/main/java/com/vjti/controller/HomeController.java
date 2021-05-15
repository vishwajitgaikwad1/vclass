package com.vjti.controller;

import com.vjti.constant.ApplicationConstants;
import com.vjti.model.LoginVO;
import com.vjti.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 9/5/21.
 */
@Controller
public class HomeController {

    @Autowired
    ILoginService loginService;


    @RequestMapping("/testpage")
    public String testPage(Model model){
        return "testpage";
    }


    @RequestMapping({"/", "/login"})
    public  String showLogin(Model model,
                             @RequestParam(value = "error", defaultValue = "") String error,
                             @CookieValue(name=ApplicationConstants.COOKIE_USER, defaultValue = "") String userCookie){

        if(!error.isEmpty()){
            model.addAttribute("ERROR_MESSAGE", "Invalid Credentials!");
        }

        if(userCookie.length()>0){
            return "redirect:/home";
        }

        LoginVO loginVO = new LoginVO();
        model.addAttribute("loginVO",loginVO);
        return "login";
    }


    @RequestMapping("/authLogin")
    public String authLogin(@ModelAttribute("loginVO") LoginVO loginVO, Model model,HttpServletResponse httpServletResponse){

        Map<String, String> response = loginService.findByLoginIdAndPassword(loginVO.getLoginId(), loginVO.getPassword());

        if(response !=null &&  response.get(ApplicationConstants.RESPONSE).toString().equals(ApplicationConstants.SUCCESS)){

            //Cookie NOTE: COOKIES DOES NOT ACCEPT ";" IN THE VALUE
            String userCookieString = ApplicationConstants.LOGGED_IN + ":" + ApplicationConstants.BOOLEAN_TRUE + "\t"
                                        + ApplicationConstants.LOGIN_ID + ":" + response.get(ApplicationConstants.LOGIN_ID) + "\t"
                                        + ApplicationConstants.USER_MSTR_SEQ + ":" + response.get(ApplicationConstants.USER_MSTR_SEQ);

            Cookie cookie = new Cookie(ApplicationConstants.COOKIE_USER, userCookieString);
            cookie.setMaxAge(7 * 24 * 24); //expires in 7 days
            cookie.setSecure(true);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            System.out.println(cookie.getValue());

            model.addAttribute(ApplicationConstants.RESPONSE, response);
            return "redirect:/home";
        }
        else{
            return "redirect:/login?error=true";
        }
    }

    @RequestMapping("/files")
    public String showFiles(Model model){ return "files"; }

    @RequestMapping("/home")
    public String showPage(Model model){
        return "home";
    }

    @RequestMapping("/classroom")
    public String getClassroom(Model model){ return "classroom"; }

    @RequestMapping("/assignment")
    public String getAssignment(Model model){ return "assignment"; }

    @RequestMapping("/announcement")
    public String getAnnouncement(Model model){ return "announcement"; }

}

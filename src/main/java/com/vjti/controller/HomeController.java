package com.vjti.controller;

import com.vjti.model.LoginVO;
import com.vjti.service.ILoginService;
import com.vjti.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 9/5/21.
 */
@Controller
public class HomeController {

    @Autowired
    ILoginService loginService;

    @RequestMapping({"/", "/login"})
    public  String showLogin(Model model, @RequestParam(value = "error", defaultValue = "") String error){

        if(!error.isEmpty()){
            model.addAttribute("ERROR_MESSAGE", "Invalid Credentials!");
        }
        LoginVO loginVO = new LoginVO();
        model.addAttribute("loginVO",loginVO);
        return "login";
    }


    @RequestMapping("/authLogin")
    public String authLogin(@ModelAttribute("loginVO") LoginVO loginVO, Model model){

        Map<String, String> response = loginService.findByLoginIdAndPassword(loginVO.getLoginId(), loginVO.getPassword());
        if(response !=null &&  response.get("RESPONSE").toString().equals("SUCCESS")){
            model.addAttribute("RESPONSE", response);
            return "redirect:/home";
        }
        else{
            return "redirect:/login?error=true";
        }
    }


    @RequestMapping("/home")
    public String showPage(Model model){
        return "home";
    }

    @RequestMapping("/testpage")
    public String testPage(Model model){
        return "testpage";
    }
}

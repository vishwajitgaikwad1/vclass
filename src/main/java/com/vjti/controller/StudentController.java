package com.vjti.controller;

import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.NewsVO;
import com.vjti.model.StudentVO;
import com.vjti.service.ILoginService;
import com.vjti.service.IStudentService;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 21/5/21.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    ILoginService loginService;

    @Autowired
    IUserService userService;

    @Autowired
    IStudentService studentService;


    @RequestMapping("/")
    public String showPage(){
        return "redirect:/student/home";
    }

/*    @RequestMapping("/home")
    public String home(){
        System.out.println("Inside /student/home");
        return "testpage";
    }*/
    @RequestMapping("/home")
    public String showHomePage(Model model,
                           HttpServletResponse httpServletResponse,
                           @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                           @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){

        model.addAttribute(ApplicationConstants.ROLE_MODEL, "STUDENT");

        Map<String, String> userProfileCookieMap = null;
        if(loginCookie.length()>0){
            Map<String, String> loginCookieMap = CommonWebUtil.fetchCookie(loginCookie);

            if(userProfileCookie.equals("")){
                StudentVO studentVO = studentService.findStudentByUserMstrSeq(Integer.valueOf(loginCookieMap.get(ApplicationConstants.USER_MSTR_SEQ)));
                CommonWebUtil.createCookie(ApplicationConstants.COOKIE_USER_PROFILE, studentVO.getCookieString(),httpServletResponse);
                model.addAttribute(ApplicationConstants.FIRSTNAME, studentVO.getFirstname());

                List<Map<String, Object>> timetableResponse = userService.fetchTimetableForUser(
                        studentVO.getCourseMstrSeq(),
                        studentVO.getSem());
                model.addAttribute(ApplicationConstants.TIMETABLE_MODEL, timetableResponse);


            }else{
                userProfileCookieMap = CommonUtil.getPropertiesKeyValuePair(userProfileCookie);
                model.addAttribute(ApplicationConstants.FIRSTNAME, userProfileCookieMap.get(ApplicationConstants.FIRSTNAME));

                List<Map<String, Object>> timetableResponse = userService.fetchTimetableForUser(
                        Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.COURSE_MSTR_SEQ)),
                        Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.SEM)));
                model.addAttribute(ApplicationConstants.TIMETABLE_MODEL, timetableResponse);
            }

            List<NewsVO> newsResponse = userService.fetchNews();
            model.addAttribute(ApplicationConstants.NEWS_MODEL, newsResponse);

            return "home";
        }
        return "redirect:/login?error=true";
    }

    @RequestMapping("/files")
    public String showFiles(Model model){ return "files"; }

    @RequestMapping("/classroom")
    public String getClassroom(Model model){ return "classroom"; }

    @RequestMapping("/assignment")
    public String getAssignment(Model model){ return "assignment"; }

    @RequestMapping("/announcement")
    public String getAnnouncement(Model model){ return "announcement"; }

    @RequestMapping("/logout")
    public String handleLogout(){
        return "redirect:/logout";
    }

}

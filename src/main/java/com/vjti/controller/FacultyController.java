package com.vjti.controller;

import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.FacultyVO;
import com.vjti.model.NewsVO;
import com.vjti.model.StudentVO;
import com.vjti.service.IFacultyService;
import com.vjti.service.ILoginService;
import com.vjti.service.IStudentService;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 21/5/21.
 */
@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    ILoginService loginService;

    @Autowired
    IUserService userService;

    @Autowired
    IFacultyService facultyService;

    @RequestMapping("/")
    public String showPage(){
        return "redirect:/faculty/home";
    }

    @RequestMapping("/home")
    public String showHomePage(Model model,
                               HttpServletResponse httpServletResponse,
                               @RequestParam(name = ApplicationConstants.COURSEPARAM,defaultValue = "") Integer courseMstrSeq,
                               @RequestParam(name = ApplicationConstants.SEMPARAM,defaultValue = "") Integer sem,
                               @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                               @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){

        model.addAttribute(ApplicationConstants.ROLE_MODEL, "FACULTY");

        Map<String, String> userProfileCookieMap = null;
        if(loginCookie.length()>0){
            Map<String, String> loginCookieMap = CommonWebUtil.fetchCookie(loginCookie);

            if(userProfileCookie.equals("")){
                FacultyVO facultyVO = facultyService.findFacultyByUserMstrSeq(Integer.valueOf(loginCookieMap.get(ApplicationConstants.USER_MSTR_SEQ)));
                CommonWebUtil.createCookie(ApplicationConstants.COOKIE_USER_PROFILE, facultyVO.getCookieString(),httpServletResponse);
                model.addAttribute(ApplicationConstants.FIRSTNAME, facultyVO.getFirstname());

                //fetch list of courseMstrSeq and Sem
                List<Map<String, Object>> facultyMatrixList = facultyService.fetchDistinctFacultyMatrix(facultyVO.getFacultyMstrSeq());
                model.addAttribute(ApplicationConstants.FACULTY_MATRIX_MODEL, facultyMatrixList);


                //fetch time table based on that list.
                List<Map<String, Object>> timetableResponse = userService.fetchTimetableForUser(
                        Integer.valueOf(facultyMatrixList.get(0).get("COURSE_MSTR_SEQ").toString()),
                        Integer.valueOf(facultyMatrixList.get(0).get("SEM").toString()));
                model.addAttribute(ApplicationConstants.TIMETABLE_MODEL, timetableResponse);


            }else{
                userProfileCookieMap = CommonUtil.getPropertiesKeyValuePair(userProfileCookie);
                model.addAttribute(ApplicationConstants.FIRSTNAME, userProfileCookieMap.get(ApplicationConstants.FIRSTNAME));

                //fetch list of courseMstrSeq and Sem
                List<Map<String, Object>> facultyMatrixList = facultyService.fetchDistinctFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));
                model.addAttribute(ApplicationConstants.FACULTY_MATRIX_MODEL, facultyMatrixList);

                if(courseMstrSeq == null && sem == null){


                    List<Map<String, Object>> timetableResponse = userService.fetchTimetableForUser(
                            Integer.valueOf(facultyMatrixList.get(0).get("COURSE_MSTR_SEQ").toString()),
                            Integer.valueOf(facultyMatrixList.get(0).get("SEM").toString()));
                    model.addAttribute(ApplicationConstants.TIMETABLE_MODEL, timetableResponse);

                }else{
                    List<Map<String, Object>> timetableResponse = userService.fetchTimetableForUser(courseMstrSeq,sem);
                    model.addAttribute(ApplicationConstants.TIMETABLE_MODEL, timetableResponse);
                }
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

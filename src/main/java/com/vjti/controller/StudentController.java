package com.vjti.controller;

import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.*;
import com.vjti.service.ILoginService;
import com.vjti.service.IStudentService;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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

            if(userProfileCookie.length()< 1){
                StudentVO studentVO = studentService.findStudentByUserMstrSeq(Integer.valueOf(loginCookieMap.get(ApplicationConstants.USER_MSTR_SEQ)));
                CommonWebUtil.createCookie(ApplicationConstants.COOKIE_USER_PROFILE, studentVO.getCookieString(),httpServletResponse);
                model.addAttribute(ApplicationConstants.FIRSTNAME, studentVO.getFirstname());

                List<Map<String, Object>> timetableResponse = userService.fetchTimetableForUser(
                        studentVO.getCourseMstrSeq(),
                        studentVO.getSem());
                model.addAttribute(ApplicationConstants.TIMETABLE_MODEL, timetableResponse);


            }else{
                userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
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
        return "redirect:/login";
    }

    @RequestMapping("/files")
    public String showFilesPage(Model model,
                            @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                            @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){

        model.addAttribute(ApplicationConstants.ROLE_MODEL, "STUDENT");
        Map<String, String> userProfileCookieMap = null;

        if(loginCookie.length()>0){

            if(userProfileCookie.length()< 1){
//                StudentVO studentVO = studentService.findStudentByUserMstrSeq(Integer.valueOf(loginCookieMap.get(ApplicationConstants.USER_MSTR_SEQ)));
//                CommonWebUtil.createCookie(ApplicationConstants.COOKIE_USER_PROFILE, studentVO.getCookieString(),httpServletResponse);
            }
            else{
                userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);


                List<SemVO> semVOList = new ArrayList<>();
                //number of sem in a particular course.
                Integer sem = userService.fetchSemByCourseMstrSeq(Integer.valueOf(userProfileCookieMap.get("COURSE_MSTR_SEQ")));

                for (int i=1; i<=sem; i++) {
                    List<Map<String, Object>> subjectList = userService.fetchSubjectsByCourseMstrSeqAndSem(
                                                                                   Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.COURSE_MSTR_SEQ)),
                                                                                   i);
                    List<FilesVO> filesVOList= new ArrayList<>();
                    for (Map<String,Object>subject:subjectList) {
                        List<FileVO> fileList = userService.fetchFilesBySemAndSubjectMstrSeq(i,Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()));

                        filesVOList.add(new FilesVO(i,Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()),subject.get("SUBJECT_NAME").toString(),fileList));
                    }
                    semVOList.add(new SemVO(filesVOList,i));
                }
                //add the filesVOList to the model
                model.addAttribute(ApplicationConstants.SEM_VO_LIST_MODEL, semVOList);
                return "files";
//                return "testpage";
            }

        }
        return "redirect:/login";
    }

    @RequestMapping("/fileupload")
    public String fileUpload(@RequestParam("file") MultipartFile file){

        String uploadDir = "/home/vishwajit_gaikwad/Desktop/VJTI/files/";
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "testpage";
    }

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

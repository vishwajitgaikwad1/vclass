package com.vjti.controller;

import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.*;
import com.vjti.service.IFacultyService;
import com.vjti.service.ILoginService;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
                List<Map<String, Object>> facultyCourseMatrixList = facultyService.fetchDistinctCourseFacultyMatrix(facultyVO.getFacultyMstrSeq());
                model.addAttribute(ApplicationConstants.COURSE_MATRIX_MODEL, facultyCourseMatrixList);


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
                List<Map<String, Object>> facultyCourseMatrixList = facultyService.fetchDistinctCourseFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));
                model.addAttribute(ApplicationConstants.COURSE_MATRIX_MODEL, facultyCourseMatrixList);

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
    public String showFilesPage(Model model,
                                @RequestParam(name = ApplicationConstants.COURSEPARAM,defaultValue = "") Integer courseMstrSeq,
                                @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                                @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){
        model.addAttribute(ApplicationConstants.ROLE_MODEL, "FACULTY");
        Map<String, String> userProfileCookieMap = null;

        if(loginCookie.length()>0){

            if(userProfileCookie.length()< 1){
//                StudentVO studentVO = studentService.findStudentByUserMstrSeq(Integer.valueOf(loginCookieMap.get(ApplicationConstants.USER_MSTR_SEQ)));
//                CommonWebUtil.createCookie(ApplicationConstants.COOKIE_USER_PROFILE, studentVO.getCookieString(),httpServletResponse);
            }
            else{
                userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);


                List<SemFilesVO> semFilesVOList = new ArrayList<>();
                List<Map<String, Object>> facultyMatrixList = facultyService.fetchDistinctFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));
                List<Map<String, Object>> facultyCourseMatrixList = facultyService.fetchDistinctCourseFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));

                if (courseMstrSeq == null) {
                    courseMstrSeq = Integer.valueOf(facultyCourseMatrixList.get(0).get("COURSE_MSTR_SEQ").toString());
                }

                Integer sem = userService.fetchSemByCourseMstrSeq(courseMstrSeq);

                for (int i=1; i<=sem; i++) {
                    List<Map<String, Object>> subjectList = userService.fetchSubjectsByCourseMstrSeqAndSem(
                            courseMstrSeq,
                            i);
                    List<SubjectFilesVO> subjectFilesVOList = new ArrayList<>();
                    for (Map<String,Object>subject:subjectList) {
                        List<FileVO> fileList = userService.fetchFilesBySemAndSubjectMstrSeq(i,Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()));

                        subjectFilesVOList.add(new SubjectFilesVO(i,Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()),subject.get("SUBJECT_NAME").toString(),fileList));
                    }
                    semFilesVOList.add(new SemFilesVO(i,subjectFilesVOList));
                }
                //add the filesVOList to the model
                model.addAttribute(ApplicationConstants.SEM_VO_LIST_MODEL, semFilesVOList);
                model.addAttribute(ApplicationConstants.COURSE_MATRIX_MODEL, facultyCourseMatrixList);
                model.addAttribute(ApplicationConstants.FACULTY_MATRIX_MODEL, facultyMatrixList);
                return "files";
//                return "testpage";
            }

        }
        return "redirect:/login";
    }

    @RequestMapping("/fileupload")
    public String fileUpload(@RequestParam(name = ApplicationConstants.FILEPARAM, defaultValue = "") MultipartFile file,
                             @RequestParam(name = ApplicationConstants.COURSEPARAM,defaultValue = "") Integer courseMstrSeq,
                             @RequestParam(name = ApplicationConstants.SEMPARAM,defaultValue = "") Integer sem,
                             @RequestParam(name = ApplicationConstants.SUBJECTPARAM,defaultValue = "") Integer subjectMstrSeq,
                             @RequestParam(name = "announcementName", defaultValue = "") String announcementName,
                             @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE,defaultValue = "")String userProfileCookie){


        //fetch course name
        String courseName=null;
        courseName = userService.fetchCourseNameById(courseMstrSeq);

        //fetch subject name
        String subjectName=null;
        if(subjectMstrSeq!=null){
            subjectName = userService.fetchSubjectNameByIdAndSem(subjectMstrSeq,sem);
        }

        String baseDir = "/home/vishwajit_gaikwad/Desktop/VJTI/files/";
        String uploadDir = "";
        if(announcementName.length()>0){
            uploadDir = baseDir+courseName+"/"+"Announcement"+"/";
        }else{
            uploadDir = baseDir+courseName+"/"+"SEM"+sem+"/"+subjectName+"/";
        }
        try {
            File fileDest = new File(uploadDir);
            if (!fileDest.exists()) {
                fileDest.mkdirs();
            }
            Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            if(announcementName.length()>0){
                Map<String,String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                AnnouncementVO announcementVO = new AnnouncementVO(null,
                                                                   courseMstrSeq,
                                                                   sem,
                                                                   Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)),
                                                                   announcementName,
                                                                   StringUtils.cleanPath(file.getOriginalFilename()),
                                                                   uploadDir);

                userService.saveAnnouncementVO(announcementVO);
                return "redirect:/faculty/announcement";
            }else{
                FileVO fileVO = new FileVO(courseMstrSeq,sem,subjectMstrSeq,StringUtils.cleanPath(file.getOriginalFilename()),uploadDir);
                userService.saveFileVO(fileVO);
                return "redirect:/faculty/files?upload=success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(announcementName.length()>0){
            return "redirect:/faculty/announcement?upload=fail";
        }
        return "redirect:/faculty/files?upload=fail";
    }





    @RequestMapping("/classroom")
    public String getClassroom(Model model,
                               @RequestParam(name = ApplicationConstants.AUTHPARAM,defaultValue = "") String authMessage,
                               @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                               @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){
        model.addAttribute(ApplicationConstants.ROLE_MODEL, "FACULTY");
        Map<String, String> userProfileCookieMap = null;

        if(loginCookie.length()>0){
            if (userProfileCookie.length()>1){
                if(authMessage.isEmpty()){
                    return "redirect:/zoom/signin";
                }
                userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                List<Map<String, Object>> facultyMatrixList = facultyService.fetchDistinctFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));
                List<Map<String, Object>> facultyCourseMatrixList = facultyService.fetchDistinctCourseFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));

                List<Integer> semList = new ArrayList<>();
                List<Integer> cmsList = new ArrayList<>();
                List<Integer> smsList = new ArrayList<>();
                List<String> subjectList = null;
                //list of sem, list of course_mstr_seq, list of subject_mstr_seq;

                for(Map<String,Object> facultyMatrix: facultyMatrixList){
                    semList.add(Integer.valueOf(facultyMatrix.get("SEM").toString()));
                    cmsList.add(Integer.valueOf(facultyMatrix.get("COURSE_MSTR_SEQ").toString()));
                    smsList.add(Integer.valueOf(facultyMatrix.get("SUBJECT_MSTR_SEQ").toString()));

                }

                List<RoomVO> roomVOList = userService.fetchRoomsWithParams(cmsList,
                        semList,
                        smsList,
                        Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)) );
                model.addAttribute(ApplicationConstants.SEM_VO_LIST_MODEL, roomVOList);
                model.addAttribute(ApplicationConstants.COURSE_MATRIX_MODEL, facultyCourseMatrixList);
                model.addAttribute(ApplicationConstants.FACULTY_MATRIX_MODEL, facultyMatrixList);

                ZoomCreate zoomCreate = new ZoomCreate();
                model.addAttribute("zoomCreateVO",zoomCreate);
                return "classroom";
            }

        }
        return "redirect:/login";
    }

    @RequestMapping("/assignment")
    public String getAssignment(Model model){ return "assignment"; }

    @RequestMapping("/announcement")
    public String getAnnouncement(Model model,
                                  @RequestParam(name = ApplicationConstants.COURSEPARAM,defaultValue = "") Integer courseMstrSeq,
                                  @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                                  @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){
        model.addAttribute(ApplicationConstants.ROLE_MODEL, "FACULTY");
        if(loginCookie.length()>0){
            List<Map<String,Object>> announcementVOList;
            Map<String, String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
            List<Map<String, Object>> facultyCourseMatrixList = facultyService.fetchDistinctCourseFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));
            List<Map<String, Object>> facultyMatrixList = facultyService.fetchDistinctFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));
            if(courseMstrSeq!=null && courseMstrSeq!=0){
                List<Integer> cmsList = new ArrayList<>();
                cmsList.add(courseMstrSeq);
                announcementVOList = userService.findAllByCourseMstrSeq(cmsList);
            }else{

                List<Integer> cmsList = new ArrayList<>();
                for(Map<String,Object> facultyMatrix: facultyMatrixList){
                    cmsList.add(Integer.valueOf(facultyMatrix.get("COURSE_MSTR_SEQ").toString()));

                }
                announcementVOList = userService.findAllByCourseMstrSeq(cmsList);
            }
            model.addAttribute(ApplicationConstants.ANNOUNEMENT_VO_LIST_MODEL,announcementVOList);
            model.addAttribute(ApplicationConstants.COURSE_MATRIX_MODEL, facultyCourseMatrixList);
            model.addAttribute(ApplicationConstants.FACULTY_MATRIX_MODEL, facultyMatrixList);
            return "announcement";

        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String handleLogout(){
        return "redirect:/logout";
    }

    @RequestMapping("/refreshToken")
    public RedirectView refreshToken(@ModelAttribute("zoomCreateVO") ZoomCreate zoomCreateVO, Model model, RedirectAttributes redir){
//        model.addAttribute("zoomCreateVO",zoomCreateVO);
        RedirectView redirectView = new RedirectView("/zoom/refreshtoken",true);
        redir.addFlashAttribute("zoomCreateVO",zoomCreateVO);
//        return "redirect:/zoom/refreshtoken";
        return redirectView;
    }
}

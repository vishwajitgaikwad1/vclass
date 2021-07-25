package com.vjti.controller;

import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.common.DateUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.*;
import com.vjti.repository.AnnouncementRepository;
import com.vjti.service.IEmailService;
import com.vjti.service.ILoginService;
import com.vjti.service.IStudentService;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
import java.text.SimpleDateFormat;
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

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    IEmailService emailService;


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


                List<SemFilesVO> semFilesVOList = new ArrayList<>();
                //number of sem in a particular course.
                Integer sem = userService.fetchSemByCourseMstrSeq(Integer.valueOf(userProfileCookieMap.get("COURSE_MSTR_SEQ")));

                for (int i=1; i<=sem; i++) {
                    List<Map<String, Object>> subjectList = userService.fetchSubjectsByCourseMstrSeqAndSem(
                                                                                   Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.COURSE_MSTR_SEQ)),
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
                return "files";
//                return "testpage";
            }

        }
        return "redirect:/login";
    }

    @RequestMapping("/classroom")
    public String getClassroom(Model model,
                               @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                               @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){

        model.addAttribute(ApplicationConstants.ROLE_MODEL, "STUDENT");
        Map<String, String> userProfileCookieMap = null;
        Integer courseMstrSeq=0;

        if(loginCookie.length()>0){
            if (userProfileCookie.length()>1){
                userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                List<SemRoomVO> semRoomVOList = new ArrayList<>();
                courseMstrSeq = Integer.valueOf(userProfileCookieMap.get("COURSE_MSTR_SEQ"));

                Integer sem = Integer.valueOf(userProfileCookieMap.get("SEM")); //userService.fetchSemByCourseMstrSeq(courseMstrSeq);
                List<Map<String, Object>> subjectList = userService.fetchSubjectsByCourseMstrSeqAndSem(courseMstrSeq,sem);
                List<SubjectRoomsVO> subjectRoomsVOList = new ArrayList<>();
                for (Map<String,Object>subject:subjectList) {
                        List<RoomVO> roomVOList = userService.fetchRoomsBySemAndSubjectMstrSeq(courseMstrSeq,sem,Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()));
                        subjectRoomsVOList.add(new SubjectRoomsVO(sem,Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()),subject.get("SUBJECT_NAME").toString(),roomVOList));
                }

                model.addAttribute(ApplicationConstants.SEM_VO_LIST_MODEL, subjectRoomsVOList);
                return "classroom";
            }

        }
        return "redirect:/login";


//        return "classroom";
    }

    @RequestMapping("/assignment")
    public String getAssignment(Model model,
                                @RequestParam(name = "upload", defaultValue = "") String upload,
                                @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                                @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){
        model.addAttribute(ApplicationConstants.ROLE_MODEL, "STUDENT");

        Map<String, String> userProfileCookieMap = null;
        Integer courseMstrSeq=0;

        if(loginCookie.length()>0){
            if(userProfileCookie.length()>0){
                userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                courseMstrSeq = Integer.valueOf(userProfileCookieMap.get("COURSE_MSTR_SEQ"));
                Integer sem = Integer.valueOf(userProfileCookieMap.get("SEM").toString());
                model.addAttribute("USER_MSTR_SEQ",userProfileCookieMap.get("USER_MSTR_SEQ"));

                    List<Map<String, Object>> subjectList = userService.fetchSubjectsByCourseMstrSeqAndSem(courseMstrSeq, sem);

                    List<SubjectAssignmentVO> subjectAssignmentVOList = new ArrayList<>();
                    //FOR EACH SUBJECT FETCH ASSIGNMENT LIST
                    for(Map<String,Object> subject : subjectList){
                        //fetch assignments
                        List<AssignmentVO> assignmentVOList = new ArrayList<>();
                        List<Map<String,Object>> assignmentList = userService.fetchAssignmentsBySubjectSeq(Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()));
                        for(Map<String,Object> assignment: assignmentList){
                            //fetch submitted files
                            List<Map<String,Object>> submittedFilesList = userService.fetchSubmittedFilesByStudentSeqAndAssignmentSeq(Integer.valueOf(userProfileCookieMap.get("STUDENT_MSTR_SEQ")),Integer.valueOf(assignment.get("ASSIGNMENT_MSTR_SEQ").toString()));
                            //CREATING SUBMITTED FILES VO
                            List<SubmittedFilesVO> submittedFilesVOList = new ArrayList<>();
                            for(Map<String,Object> submittedFiles: submittedFilesList){
                                submittedFilesVOList.add(new SubmittedFilesVO(Integer.valueOf(submittedFiles.get("SUBMISSION_MSTR_SEQ").toString()),
                                        Integer.valueOf(submittedFiles.get("ASSIGNMENT_MSTR_SEQ").toString()),
                                        Integer.valueOf(submittedFiles.get("USER_MSTR_SEQ").toString()),
                                        Integer.valueOf(submittedFiles.get("SUBJECT_MSTR_SEQ").toString()),
                                        (submittedFiles.get("MARKS")== null)?0:Integer.valueOf(submittedFiles.get("MARKS").toString()),
                                        (submittedFiles.get("STATUS")==null)?"PENDING":submittedFiles.get("STATUS").toString(),
                                        (submittedFiles.get("SUBMIT_DATE")==null)?"NA":submittedFiles.get("SUBMIT_DATE").toString(),
                                        (submittedFiles.get("FILE_NAME")==null)?"NA":submittedFiles.get("FILE_NAME").toString(),
                                        (submittedFiles.get("FILE_PATH")==null)?"#":submittedFiles.get("FILE_PATH").toString()));
                            }

                            //CREATING ASSIGNMENTVO
                            assignmentVOList.add(new AssignmentVO(Integer.valueOf(assignment.get("ASSIGNMENT_MSTR_SEQ").toString()),
                                    Integer.valueOf(assignment.get("FACULTY_MSTR_SEQ").toString()),
                                    assignment.get("ASSIGNMENT_NAME").toString(),
                                    Integer.valueOf(assignment.get("MARKS").toString()),
                                    assignment.get("FILE_PATH").toString(),
                                    submittedFilesVOList));
                        }
                        //CREATING SUBJECTASSIGNMENTVO
                        subjectAssignmentVOList.add(new SubjectAssignmentVO(Integer.valueOf(subject.get("SEM").toString()),
                                Integer.valueOf(subject.get("SUBJECT_MSTR_SEQ").toString()),
                                subject.get("SUBJECT_NAME").toString(),
                                assignmentVOList));

                }
                model.addAttribute(ApplicationConstants.STUDENT_ASSIGNMENT_MODEL, subjectAssignmentVOList);
                    if(upload.length()>0){
                        MessageVO messageVO = new MessageVO(upload,"Assignment upload "+upload);
                        model.addAttribute("MESSAGE",messageVO);
                    }
                return "assignment";

            }
        }
        return "redirect:/login";
    }


    @RequestMapping("/fileupload")
    public String fileUpload(@RequestParam(name = ApplicationConstants.FILEPARAM, defaultValue = "") MultipartFile file,
                             @RequestParam(name = ApplicationConstants.SUBJECTPARAM,defaultValue = "") Integer subjectMstrSeq,
                             @RequestParam(name = ApplicationConstants.ACTIONPARAM, defaultValue = "") String actionParam,
                             @RequestParam(name = ApplicationConstants.ASSIGNMENTPARAM, defaultValue = "") Integer assignmentMstrSeq,
                             @RequestParam(name = "assignmentName", defaultValue = "") String assignmentName,
                             @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE,defaultValue = "")String userProfileCookie){

        Map<String, String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
        String courseName=null;
        String subjectName=null;
        String baseDir = "/home/vishwajit_gaikwad/Desktop/VJTI/files/";
        String uploadDir = "";

        courseName = userService.fetchCourseNameById(Integer.valueOf(userProfileCookieMap.get("COURSE_MSTR_SEQ")));
        Integer userMstrSeq = Integer.valueOf(userProfileCookieMap.get("USER_MSTR_SEQ"));
        Integer courseMstrSeq = Integer.valueOf(userProfileCookieMap.get("COURSE_MSTR_SEQ"));
        Integer sem = Integer.valueOf(userProfileCookieMap.get("SEM"));
        subjectName = userService.fetchSubjectNameByIdAndSem(subjectMstrSeq,sem);

        if(actionParam.equals(ApplicationConstants.ACTION_ASSIGNMENT)){
            uploadDir = baseDir+courseName+"/"+"SEM"+sem+"/"+subjectName+"/Assignment/"+assignmentName+"/Submission/"; // /Assignment/AssignmentName/Submission/
        }
        try{
            File fileDest = new File(uploadDir);
            if (!fileDest.exists()) {
                fileDest.mkdirs();
            }
            Path copyLocation = Paths.get(uploadDir + File.separator +StringUtils.cleanPath(file.getOriginalFilename()));
            /*File copyFile = new File(copyLocation.toString());
            if(copyFile.exists()){
                copyLocation = Paths.get(uploadDir + File.separator +StringUtils.cleanPath(file.getOriginalFilename())+"(1)");
            }*/
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(DateUtil.getCurrentDate());

            SubmittedFilesVO submittedFilesVO = new SubmittedFilesVO(null,
                                                                    assignmentMstrSeq,
                                                                    userMstrSeq,
                                                                    courseMstrSeq,
                                                                    sem,
                                                                    subjectMstrSeq,
                                                                    null,
                                                                    "SUBMITTED",
                                                                    dateString,
                                                                    fileName,
                                                                    "file://"+uploadDir+fileName);
            userService.saveSubmittedFilesVO(submittedFilesVO);
            List<Map<String,Object>> emailTemplate = userService.getEmailTemplate("STUDENT","ASSIGNMENT_SUCCESS");
            String message = emailTemplate.get(0).get("TEMPLATE_BODY").toString();
            message = message.replace("<Assignment Name>",assignmentName);

            String to = userProfileCookieMap.get("EMAIL");
            String subject = emailTemplate.get(0).get("TEMPLATE_SUBJECT").toString();
            emailService.sendMail(message, to, subject);
            return "redirect:/student/assignment?upload=success";

        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/student/assignment?upload=failed";
        }
    }

    @RequestMapping("/announcement")
    public String getAnnouncement(Model model,
                                  @RequestParam(name = ApplicationConstants.COURSEPARAM,defaultValue = "") Integer courseMstrSeq,
                                  @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                                  @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){
        model.addAttribute(ApplicationConstants.ROLE_MODEL, "STUDENT");
        if(loginCookie.length()>0){
            List<AnnouncementVO> announcementVOList;
            if(courseMstrSeq!=null){
                announcementVOList = announcementRepository.findAllByCourseMstrSeq(courseMstrSeq);
            }else{
                Map<String, String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                announcementVOList = announcementRepository.findAllByCourseMstrSeq(Integer.valueOf(userProfileCookieMap.get("COURSE_MSTR_SEQ")));
            }
            model.addAttribute(ApplicationConstants.ANNOUNEMENT_VO_LIST_MODEL,announcementVOList);
            return "announcement";

        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String handleLogout(){
        return "redirect:/logout";
    }

}

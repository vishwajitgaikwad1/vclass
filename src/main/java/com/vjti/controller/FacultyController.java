package com.vjti.controller;

import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.*;
import com.vjti.service.IFacultyService;
import com.vjti.service.ILoginService;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
                                @RequestParam(name = "upload", defaultValue = "") String upload,
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
                if(upload.length()>0){
                    MessageVO messageVO = new MessageVO(upload,"Assignment upload "+upload);
                    model.addAttribute("MESSAGE",messageVO);
                }
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
                             @RequestParam(name = ApplicationConstants.ANNOUNCEMENTNAMEPARAM, defaultValue = "") String announcementName,
                             @RequestParam(name = ApplicationConstants.ACTIONPARAM, defaultValue = "") String actionParam,
                             @RequestParam(name = ApplicationConstants.MARKSPARAM, defaultValue = "") Integer marks,
                             @RequestParam(name = ApplicationConstants.ASSIGNMENTNAMEPARAM, defaultValue = "") String assignmentName,
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
        if(actionParam.equals(ApplicationConstants.ACTION_ANNOUNCEMENT)){
            uploadDir = baseDir+courseName+"/Announcement/";
        } else if(actionParam.equals(ApplicationConstants.ACTION_NOTES)){
            uploadDir = baseDir+courseName+"/"+"SEM"+sem+"/"+subjectName+"/";
        }else if(actionParam.equals(ApplicationConstants.ACTION_ASSIGNMENT)){
            uploadDir = baseDir+courseName+"/"+"SEM"+sem+"/"+subjectName+"/Assignment/"+assignmentName+"/";
        }

        try {
            File fileDest = new File(uploadDir);
            if (!fileDest.exists()) {
                fileDest.mkdirs();
            }
            Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            Map<String,String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            switch (actionParam){
                case ApplicationConstants.ACTION_ANNOUNCEMENT :     AnnouncementVO announcementVO = new AnnouncementVO(null,
                                                                                                                       courseMstrSeq,
                                                                                                                       sem,
                                                                                                                       Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)),
                                                                                                                       announcementName,
                                                                                                                       fileName,
                                                                                                                       "file://"+uploadDir+fileName);
                                                                    userService.saveAnnouncementVO(announcementVO);
                                                                    return "redirect:/faculty/announcement?upload=success";

                case ApplicationConstants.ACTION_NOTES :            FileVO fileVO = new FileVO(null,courseMstrSeq,sem,subjectMstrSeq,fileName,"file://"+uploadDir+fileName);
                                                                    userService.saveFileVO(fileVO);
                                                                    return "redirect:/faculty/files?upload=success";

                case ApplicationConstants.ACTION_ASSIGNMENT :       Assignment assignmentVO = new Assignment(courseMstrSeq,sem,subjectMstrSeq,
                                                                                                                 Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)),
                                                                                                                 assignmentName,marks,fileName,"file://"+uploadDir+fileName);
                                                                    userService.saveAssignment(assignmentVO);
                                                                    return "redirect:/faculty/assignment?upload=success";
                default:        break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(actionParam.equals(ApplicationConstants.ACTION_ANNOUNCEMENT)){
            return "redirect:/faculty/announcement?upload=fail";
        } else if(actionParam.equals(ApplicationConstants.ACTION_NOTES)){
            return "redirect:/faculty/files?upload=fail";
        }else if(actionParam.equals(ApplicationConstants.ACTION_ASSIGNMENT)){
            return "redirect:/faculty/assignment?upload=fail";
        }
        return "redirect:/login";
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
    public String getAssignment(Model model,
                                @RequestParam(name = ApplicationConstants.UPLOADPARAM,defaultValue = "") String upload,
                                @RequestParam(name = ApplicationConstants.ACTIONPARAM, defaultValue = "") String action,
                                @CookieValue(name = ApplicationConstants.COOKIE_LOGIN, defaultValue = "") String loginCookie,
                                @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE, defaultValue = "") String userProfileCookie){
        model.addAttribute(ApplicationConstants.ROLE_MODEL, "FACULTY");
        Map<String, String> userProfileCookieMap = null;

        /*ZoomCreate zoomCreate = new ZoomCreate();
                model.addAttribute("zoomCreateVO",zoomCreate);*/

        GradeVOList gradeVO = new GradeVOList();
        model.addAttribute("gradeVO",gradeVO);

        if(loginCookie.length()>0){
            if(userProfileCookie.length()>0){
                userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                List<CourseAssignmentVO> courseAssignmentVOList = new ArrayList<>();

                //fetch course from faculty matrix
                List<Map<String, Object>> facultyCourseMatrixList = facultyService.fetchDistinctCourseFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));

                for(Map<String,Object> facultyCourse : facultyCourseMatrixList){
                    //fetch sems from faculty matrix
                    List<Integer> facultySemList = facultyService.fetchDistinctSemFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)));

                    List<SemAssignmentVO> semAssignmentVOList = new ArrayList<>();

                    for(Integer sem: facultySemList){

                        //fetch subjects from faculty matrix by faculty_mstr_seq and sem
                        List<Map<String,Object>> facultySubjectMatrixList = facultyService.fetchDistinctFacultyMatrix(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)), sem);

                        List<SubjectAssignmentVO> subjectAssignmentVOList = new ArrayList<>();

                        for(Map<String,Object> subjectMatrix : facultySubjectMatrixList){
                            //fetch assignments
                            List<Map<String,Object>> assignmentList = userService.fetchAssignmentsBySeqAndSubjectSeq(Integer.valueOf(userProfileCookieMap.get(ApplicationConstants.FACULTY_MSTR_SEQ)),Integer.valueOf(subjectMatrix.get("SUBJECT_MSTR_SEQ").toString()));

                            List<AssignmentVO> assignmentVOList = new ArrayList<>();
                            for(Map<String,Object> assignment : assignmentList){
                                //fetch submitted files
                                List<Map<String,Object>> submittedFilesList = facultyService.fetchSubmittedFilesBySeq(Integer.valueOf(assignment.get("ASSIGNMENT_MSTR_SEQ").toString()));

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
                            subjectAssignmentVOList.add(new SubjectAssignmentVO(Integer.valueOf(subjectMatrix.get("SEM").toString()),
                                                                                Integer.valueOf(subjectMatrix.get("SUBJECT_MSTR_SEQ").toString()),
                                                                                subjectMatrix.get("SUBJECT_NAME").toString(),
                                                                                assignmentVOList));
                        }

                        semAssignmentVOList.add(new SemAssignmentVO(sem,subjectAssignmentVOList));
                    }
                    courseAssignmentVOList.add(new CourseAssignmentVO(Integer.valueOf(facultyCourse.get("COURSE_MSTR_SEQ").toString()),facultyCourse.get("COURSE_NAME").toString(),semAssignmentVOList));

                }
                System.out.println(courseAssignmentVOList);
                model.addAttribute(ApplicationConstants.FACULTY_ASSIGNMENT_MODEL,courseAssignmentVOList);
                if(upload.length()>0){
                    MessageVO messageVO = new MessageVO("Success","Assignment Uploaded Successfully");
                    model.addAttribute("MESSAGE",messageVO);
                }else if(action.length()>0){
                    MessageVO messageVO = new MessageVO("Success","Assignments Graded Successfully!");
                    model.addAttribute("MESSAGE",messageVO);
                }
                return "assignment";
            }
        }
        return "redirect:/login"; }


    @RequestMapping("/gradeassignment")
    public String gradeAssignments(@ModelAttribute(name = "gradeVO")GradeVOList gradeVO){
        System.out.println(gradeVO);
        for (SubmittedFilesVO submittedFile: gradeVO.getSubmissionList()) {
            if(submittedFile.getMarks()!=null){
                SubmittedFilesVO submittedFilesVO = userService.fetchSubmittedFilesBySeq(submittedFile.getSubmissionMstrSeq());
                submittedFilesVO.setMarks(submittedFile.getMarks());
                submittedFilesVO.setStatus("GRADED");
                userService.saveSubmittedFilesVO(submittedFilesVO);
            }
        }
        return "redirect:/faculty/assignment?action=gradesuccess";
    }

    @RequestMapping("/announcement")
    public String getAnnouncement(Model model,
                                  @RequestParam(name = "upload", defaultValue = "") String upload,
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
            if(upload.length()>0){
                MessageVO messageVO = new MessageVO(upload,"Assignment upload "+upload);
                model.addAttribute("MESSAGE",messageVO);
            }
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

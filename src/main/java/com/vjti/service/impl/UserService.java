package com.vjti.service.impl;

import com.vjti.common.CommonUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.constant.JdbcConstants;
import com.vjti.model.*;
import com.vjti.repository.*;
import com.vjti.service.IUserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by vishwajit_gaikwad on 12/5/21.
 */
@Service
public class UserService implements IUserService {



    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    SubmittedFilesRepository submittedFilesRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Override
    public Map<String, String> findByUserMstrSeq(Integer userMstrSeq,String role, String ifCookie) {

        Map<String,String> response = new HashMap<>();

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.USER_MSTR_SEQ, userMstrSeq);

        if(role.equals(ApplicationConstants.STUDENT)){

            StudentVO studentVO = (StudentVO) namedParameterJdbcTemplate.queryForObject(JdbcConstants.FETCH_STUDENT_BY_USER_MSTR_SEQ, map, new StudentVO());
            if(studentVO!=null){
//                response = CommonUtil.getPropertiesKeyValuePair(studentVO.toMapResponseString());
                response.put(ApplicationConstants.RESPONSE, ApplicationConstants.SUCCESS);
            }
            if(ifCookie.equals(ApplicationConstants.STR_Y)){
//                response.put(ApplicationConstants.COOKIE_USER_PROFILE, studentVO.toMapResponseString());
            }
            return response;
        }else if(role.equals(ApplicationConstants.FACULTY)){

        }
     /*   if(true){

            response.put(ApplicationConstants.RESPONSE, ApplicationConstants.SUCCESS);
            response.put(ApplicationConstants.USER_MSTR_SEQ, userVO.getUserMstrSeq().toString());
            response.put(ApplicationConstants.EMAIL, userVO.getEmail());
            response.put(ApplicationConstants.FIRSTNAME, userVO.getFirstname());
            response.put(ApplicationConstants.LASTNAME, userVO.getLastname());
            response.put(ApplicationConstants.COURSE_MSTR_SEQ, userVO.getCourseMstrSeq().toString());
            response.put(ApplicationConstants.SEM, userVO.getSem().toString());
            response.put(ApplicationConstants.USER_ROLE_MSTR_SEQ, userVO.getUserRoleMstrSeq().toString());
            if(ifCookie!=null && ifCookie.equals(ApplicationConstants.STR_Y))
            {
                response.put(ApplicationConstants.COOKIE_USER_PROFILE, userVO.toCookieString());
            }
            return response;
        }*/
        return null;
    }

    @Override
    public List<NewsVO> fetchNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> fetchTimetableForUser(Integer courseMstrSeq, Integer sem){
        Map<String, String> response = new HashMap<>();

        //fetch the query by namedparameterjdbc template since we have not created a seperate class for timetable.
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.COURSE_MSTR_SEQ, courseMstrSeq);
        map.addValue(JdbcConstants.SEM, sem);
        //string
        List<Map<String, Object>> timetableList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_TIMETABLE_COURSE_MSTR_SEQ_AND_SEM,map);


        if(timetableList != null){
            return timetableList;
        }
        return null;
    }

    @Override
    public String fetchUserRoleBySeq(Integer userRoleMstrSeq){
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.USER_ROLE_MSTR_SEQ, userRoleMstrSeq);
        List<Map<String, Object>> roleList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_USER_ROLE_BY_SEQ, map);

        if(roleList!= null){
            return roleList.get(0).get("ROLE").toString();
        }else{
            return "INVALID ROLE_MSTR_SEQ";
        }
    }

    @Override
    public Integer fetchSemByCourseMstrSeq(Integer courseMstrSeq) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.COURSE_MSTR_SEQ, courseMstrSeq);
        Integer sem = namedParameterJdbcTemplate.queryForObject(JdbcConstants.FETCH_SEM_BY_COURSE_MSTR_SEQ,map,Integer.class);

        return sem;
    }

    @Override
    public List<Map<String, Object>> fetchSubjectsByCourseMstrSeqAndSem(Integer courseMstrSeq, Integer sem) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.COURSE_MSTR_SEQ, courseMstrSeq);
        map.addValue(JdbcConstants.SEM, sem);
        List<Map<String, Object>> subjectList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_SUBJECTS_BY_COURSE_MSTR_SEQ_AND_SEM, map);

        return subjectList;
    }

    @Override
    public List<FileVO> fetchFilesBySemAndSubjectMstrSeq(Integer sem, Integer subjectMstrSeq) {
        List<FileVO> fileVOList = fileRepository.findAllBySemAndSubjectMstrSeq(sem, subjectMstrSeq);
        return fileVOList;
    }

    @Override
    public String fetchCourseNameById(Integer courseMstrSeq) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.COURSE_MSTR_SEQ, courseMstrSeq);
        String course = namedParameterJdbcTemplate.queryForObject(JdbcConstants.FETCH_COURSE_BY_COURSE_MSTR_SEQ,map,String.class);
        return course;
    }

    @Override
    public String fetchSubjectNameByIdAndSem(Integer subjectMstrSeq, Integer sem) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.SUBJECT_MSTR_SEQ, subjectMstrSeq);
        map.addValue(JdbcConstants.SEM, sem);
        String Subject = namedParameterJdbcTemplate.queryForObject(JdbcConstants.FETCH_SUBJECT_BY_SUBJECT_MSTR_SEQ,map,String.class);
        return Subject;
    }

    @Override
    public List<RoomVO> fetchRoomsWithParams(List<Integer> courseMstrSeq,List<Integer> sem, List<Integer> subjectMstrSeq, Integer facultyMstrSeq) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.COURSE_MSTR_SEQ,courseMstrSeq);
        map.addValue(JdbcConstants.SEM, sem);
        map.addValue(JdbcConstants.SUBJECT_MSTR_SEQ, subjectMstrSeq);
        map.addValue(JdbcConstants.FACULTY_MSTR_SEQ, facultyMstrSeq);
        List<Map<String, Object>> roomList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_ROOMS_WITH_PARAMS, map);
        List<RoomVO> roomVOList = new ArrayList<>();
        for(Map<String,Object>room : roomList){
            java.sql.Timestamp sqlDate = java.sql.Timestamp.valueOf((LocalDateTime) room.get("DATE"));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(sqlDate).toString();
            dateFormat = new SimpleDateFormat("hh.mm aa");
            String timeString = dateFormat.format(sqlDate).toString();

            roomVOList.add(new RoomVO(room.get("COURSE_NAME").toString(),
                    room.get("SUBJECT_NAME").toString(),
                    room.get("SEM").toString(),
                    room.get("MEETING_ID").toString(),
                    room.get("CLASS_NAME").toString(),
                    room.get("CLASS_URL").toString(),
                    dateString,
                    timeString,
                    room.get("PASSWORD").toString()));
        }

        return roomVOList;
    }

    @Override
    public List<RoomVO> fetchRoomsBySemAndSubjectMstrSeq(Integer courseMstrSeq, Integer sem, Integer subjectMstrSeq) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.COURSE_MSTR_SEQ,courseMstrSeq);
        map.addValue(JdbcConstants.SEM, sem);
        map.addValue(JdbcConstants.SUBJECT_MSTR_SEQ, subjectMstrSeq);
        List<Map<String, Object>> roomList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_ROOMS_WITH_CMS_SEM_AND_SMS, map);

        List<RoomVO> roomVOList = new ArrayList<>();
        for(Map<String, Object>room : roomList){
            java.sql.Timestamp sqlDate = java.sql.Timestamp.valueOf((LocalDateTime) room.get("DATE"));
            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
            String dateString2 = dateFormat2.format(sqlDate).toString();

            roomVOList.add(new RoomVO(room.get("FIRSTNAME").toString(),
                                      room.get("CLASS_NAME").toString(),
                                      room.get("CLASS_URL").toString(),
                                      dateString2,
                                      room.get("PASSWORD").toString()));
        }
        return roomVOList;
    }

    @Override
    public void saveFileVO(FileVO fileVO) {
        fileRepository.save(fileVO);
    }

    @Override
    public void saveAnnouncementVO(AnnouncementVO announcementVO){
        announcementRepository.save(announcementVO);
    }


    @Override
    public  JSONObject getRequestObject(String configName, ZoomCreate zoomCreate) throws Exception {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.CONFIG_NAME,configName);
        String requestJson = namedParameterJdbcTemplate.queryForObject(JdbcConstants.FETCH_REQUEST_BY_CONFIGNAME, map, String.class);

        JSONObject requestJsonObject = new JSONObject();
        JSONParser parser = new JSONParser();

        JSONObject object = (JSONObject) parser.parse(requestJson);
        for(Object data : object.entrySet()){
            //code goes here
            Map.Entry<String,Object> dataSet = (Map.Entry<String, Object>) data;
            if(dataSet.getValue() instanceof String){
                Object dataVal;
                try{
                    dataVal = CommonUtil.callGetterMethod(ZoomCreate.class,zoomCreate,String.valueOf(dataSet.getValue()));
                }catch (Exception n){ //for predefined values in json
                    dataVal=String.valueOf(dataSet.getValue());
                }
                if(dataVal != null){
                        requestJsonObject.put(dataSet.getKey(), String.valueOf(dataVal));
                }
            }

        }
        return requestJsonObject;
    }

    @Override
    public List<Map<String, Object>> findAllByCourseMstrSeq(List<Integer> courseMstrSeq) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.COURSE_MSTR_SEQ, courseMstrSeq);
        List<Map<String, Object>> roomList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_ANNOUNCEMENT, map);
        return roomList;
    }

    @Override
    public List<Map<String,Object>> fetchAssignmentsBySeqAndSubjectSeq(Integer facultyMstrSeq, Integer subjectMstrSeq) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.FACULTY_MSTR_SEQ, facultyMstrSeq);
        map.addValue(JdbcConstants.SUBJECT_MSTR_SEQ, subjectMstrSeq);
        List<Map<String,Object>> assignmentList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_ASSIGNMENT_BY_FACULTY_AND_SUBJECT_SEQ,map);
        return assignmentList;
    }

    @Override
    public List<Map<String,Object>> fetchAssignmentsBySubjectSeq(Integer subjectMstrSeq){
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.SUBJECT_MSTR_SEQ, subjectMstrSeq);
        List<Map<String,Object>> assignmentList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_ASSIGNMENT_BY_SUBJECT_SEQ,map);
        return assignmentList;

    }

    @Override
    public List<Map<String,Object>> fetchSubmittedFilesByStudentSeqAndAssignmentSeq(Integer studentMstrSeq, Integer assignmentMstrSeq){
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.STUDENT_MSTR_SEQ,studentMstrSeq);
        map.addValue(JdbcConstants.ASSIGNMENT_MSTR_SEQ, assignmentMstrSeq);
        List<Map<String,Object>> submittedAssignmentList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_SUBMITTED_FILES_BY_STUDENT_MSTR_SEQ_AND_ASSIGNMENT_SEQ,map);
        return submittedAssignmentList;
    }

    @Override
    public void saveSubmittedFilesVO(SubmittedFilesVO submittedFilesVO) {submittedFilesRepository.save(submittedFilesVO);}

    @Override
    public void saveAssignment(Assignment assignmentVO){assignmentRepository.save(assignmentVO);}

    @Override
    public SubmittedFilesVO fetchSubmittedFilesBySeq(Integer submissionMstrSeq) {
        SubmittedFilesVO s = submittedFilesRepository.getOne(submissionMstrSeq);
        return s;
    }
}

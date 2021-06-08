package com.vjti.service.impl;

import com.vjti.common.CommonUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.constant.JdbcConstants;
import com.vjti.model.FileVO;
import com.vjti.model.NewsVO;
import com.vjti.model.StudentVO;
import com.vjti.repository.FileRepository;
import com.vjti.repository.NewsRepository;
import com.vjti.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void saveFileVO(FileVO fileVO) {
        fileRepository.save(fileVO);
    }


}

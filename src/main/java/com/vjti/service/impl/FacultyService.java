package com.vjti.service.impl;

import com.vjti.constant.JdbcConstants;
import com.vjti.model.FacultyVO;
import com.vjti.model.StudentVO;
import com.vjti.service.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
@Service
public class FacultyService implements IFacultyService {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public FacultyVO findFacultyByUserMstrSeq(Integer userMstrSeq) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.USER_MSTR_SEQ, userMstrSeq);
        FacultyVO facultyVO = (FacultyVO) namedParameterJdbcTemplate.queryForObject(JdbcConstants.FETCH_FACULTY_BY_USER_MSTR_SEQ, map, new FacultyVO());
        return facultyVO;
    }

    @Override
    public String createFacultyCookie(FacultyVO facultyVO) {
        return facultyVO.getCookieString();
    }

    @Override
    public List<Map<String, Object>> fetchFacultyMatrix(Integer facultyMstrSeq) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.FACULTY_MSTR_SEQ, facultyMstrSeq);
        List<Map<String, Object>> facultyMatrix = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_FACULTY_MATRIX_BY_SEQ, map);
        if(facultyMatrix!=null){
            return facultyMatrix;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> fetchDistinctFacultyMatrix(Integer facultyMstrSeq) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.FACULTY_MSTR_SEQ, facultyMstrSeq);
        List<Map<String, Object>> facultyMatrix = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_DISTINCT_FACULTY_MATRIX_BY_SEQ, map);
        if(facultyMatrix!=null){
            return facultyMatrix;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> fetchDistinctFacultyMatrix(Integer facultyMstrSeq, Integer sem) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.FACULTY_MSTR_SEQ, facultyMstrSeq);
        map.addValue(JdbcConstants.SEM,sem);
        List<Map<String, Object>> facultyMatrix = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_DISTINCT_FACULTY_MATRIX_BY_SEQ_AND_SEM, map);
        if(facultyMatrix!=null){
            return facultyMatrix;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> fetchDistinctCourseFacultyMatrix(Integer facultyMstrSeq) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.FACULTY_MSTR_SEQ, facultyMstrSeq);
        List<Map<String, Object>> facultyMatrix = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_DISTINCT_COURSE_MATRIX_BY_SEQ, map);
        if(facultyMatrix!=null){
            return facultyMatrix;
        }
        return null;
    }

    @Override
    public List<Integer> fetchDistinctSemFacultyMatrix(Integer facultyMstrSeq){

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.FACULTY_MSTR_SEQ, facultyMstrSeq);
        List<Map<String, Object>> facultyMatrix = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_DISTINCT_SEM_MATRIX_BY_SEQ, map);
        if(facultyMatrix!=null){
            List<Integer> semList = new ArrayList<>();
            for(Map<String,Object> sem : facultyMatrix){
                semList.add(Integer.valueOf(sem.get("SEM").toString()));
            }
            return semList;
        }

        return null;
    }

    @Override
    public List<Map<String, Object>> fetchSubmittedFilesBySeq(Integer assignmentMstrSeq) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.ASSIGNMENT_MSTR_SEQ, assignmentMstrSeq);
        List<Map<String, Object>> facultyMatrix = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_SUBMITTED_FILES_BY_ASSIGNMENT_SEQ, map);
        return facultyMatrix;
    }
}

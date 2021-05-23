package com.vjti.service.impl;

import com.vjti.constant.JdbcConstants;
import com.vjti.model.StudentVO;
import com.vjti.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
@Service
public class StudentService implements IStudentService {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public StudentVO findStudentByUserMstrSeq(Integer userMstrSeq) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(JdbcConstants.USER_MSTR_SEQ, userMstrSeq);
        StudentVO studentVO = (StudentVO) namedParameterJdbcTemplate.queryForObject(JdbcConstants.FETCH_STUDENT_BY_USER_MSTR_SEQ, map, new StudentVO());
        return studentVO;
    }

    @Override
    public String createStudentCookie(StudentVO studentVO) {
        return studentVO.getCookieString();
    }
}

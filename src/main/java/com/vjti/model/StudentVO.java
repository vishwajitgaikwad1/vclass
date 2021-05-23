package com.vjti.model;

import com.vjti.constant.ApplicationConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vishwajit_gaikwad on 20/5/21.
 */
@Getter
@Setter
public class StudentVO implements RowMapper {
    private Integer studentMstrSeq;
    private Integer userMstrSeq;
    private String firstname;
    private String lastname;
    private String email;
    private Integer courseMstrSeq;
    private Integer sem;

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        StudentVO studentVO = new StudentVO();
        studentVO.setStudentMstrSeq(resultSet.getInt("STUDENT_MSTR_SEQ"));
        studentVO.setUserMstrSeq(resultSet.getInt("USER_MSTR_SEQ"));
        studentVO.setFirstname(resultSet.getString("FIRSTNAME"));
        studentVO.setLastname(resultSet.getString("LASTNAME"));
        studentVO.setEmail(resultSet.getString("EMAIL"));
        studentVO.setCourseMstrSeq(resultSet.getInt("COURSE_MSTR_SEQ"));
        studentVO.setSem(resultSet.getInt("SEM"));
        return studentVO;
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "studentMstrSeq=" + studentMstrSeq +
                ", userMstrSeq=" + userMstrSeq +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", courseMstrSeq=" + courseMstrSeq +
                ", sem=" + sem +
                '}';
    }

    public String getCookieString(){
        String str = ApplicationConstants.STUDENT_MSTR_SEQ + ":" + studentMstrSeq + "\t"
                + ApplicationConstants.USER_MSTR_SEQ + ":" + userMstrSeq + "\t"
                + ApplicationConstants.FIRSTNAME + ":" + firstname + "\t"
                + ApplicationConstants.LASTNAME + ":" + lastname + "\t"
                + ApplicationConstants.EMAIL + ":" + email + "\t"
                + ApplicationConstants.COURSE_MSTR_SEQ + ":" + courseMstrSeq.toString() + "\t"
                + ApplicationConstants.SEM + ":" + sem.toString();
        return str;
    }
}

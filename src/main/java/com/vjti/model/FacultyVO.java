package com.vjti.model;

import com.vjti.constant.ApplicationConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
@Getter
@Setter
public class FacultyVO implements RowMapper{

    private Integer facultyMstrSeq;
    private Integer userMstrSeq;
    private String firstname;
    private String lastname;
    private String email;

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        FacultyVO facultyVO = new FacultyVO();
        facultyVO.setFacultyMstrSeq(resultSet.getInt("FACULTY_MSTR_SEQ"));
        facultyVO.setUserMstrSeq(resultSet.getInt("USER_MSTR_SEQ"));
        facultyVO.setFirstname(resultSet.getString("FIRSTNAME"));
        facultyVO.setLastname(resultSet.getString("LASTNAME"));
        facultyVO.setEmail(resultSet.getString("EMAIL"));
        return facultyVO;
    }

    @Override
    public String toString() {
        return "FacultyVO{" +
                "facultyMstrSeq=" + facultyMstrSeq +
                ", userMstrSeq=" + userMstrSeq +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getCookieString(){
        String str = ApplicationConstants.FACULTY_MSTR_SEQ + ":" + facultyMstrSeq + "\t"
                + ApplicationConstants.USER_MSTR_SEQ + ":" + userMstrSeq + "\t"
                + ApplicationConstants.FIRSTNAME + ":" + firstname + "\t"
                + ApplicationConstants.LASTNAME + ":" + lastname + "\t"
                + ApplicationConstants.EMAIL + ":" + email;
        return str;
    }
}

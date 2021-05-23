package com.vjti.model;

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
public class TimetableVO implements RowMapper {
    private String time;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;

    public TimetableVO() {
    }


    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        TimetableVO timetableVO = new TimetableVO();
        timetableVO.setTime(resultSet.getString("TIME"));
        timetableVO.setMon(resultSet.getString("MON"));
        timetableVO.setTue(resultSet.getString("TUE"));
        timetableVO.setWed(resultSet.getString("WED"));
        timetableVO.setThu(resultSet.getString("THU"));
        timetableVO.setFri(resultSet.getString("FRI"));
        timetableVO.setSat(resultSet.getString("SAT"));
        return null;
    }

}

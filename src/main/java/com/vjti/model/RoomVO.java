package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by vishwajit_gaikwad on 13/6/21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomVO {

    String courseName;
    String hostName;
    String sem;
    String subject;
    String meetingId;
    String className;
    String url;
    String date;
    String time;
    String password;

    public RoomVO(String hostname, String className, String url, String date, String password){
        this.hostName = hostname;
        this.className = className;
        this.url = url;
        this.password = password;
        this.date = date;
    }

    public RoomVO(String courseName, String subject, String sem, String meetingId, String className, String url, String date, String time, String password) {
        this.courseName = courseName;
        this.sem = sem;
        this.subject = subject;
        this.meetingId = meetingId;
        this.className = className;
        this.url = url;
        this.date = date;
        this.time = time;
        this.password = password;
    }
}

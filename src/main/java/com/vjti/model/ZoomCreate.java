package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

/**
 * Created by vishwajit_gaikwad on 27/6/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ZoomCreate {

    String meetingId;
    String topic;
    String date;
    String time;
    String course;
    String subject;
    String sem;
    String start_time;
}

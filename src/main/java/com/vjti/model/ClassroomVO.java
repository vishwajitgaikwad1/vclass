package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 26/6/21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classroom_mstr")
public class ClassroomVO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CLASSROOM_MSTR_SEQ", unique = true, nullable = false)
    Integer classroomMstrSeq;

    @Column(name = "COURSE_MSTR_SEQ")
    Integer courseMstrSeq;

    @Column(name = "SEM")
    Integer sem;

    @Column(name = "SUBJECT_MSTR_SEQ")
    Integer subjectMstrSeq;

    @Column(name = "FACULTY_MSTR_SEQ")
    Integer facultyMstrSeq;

    @Column(name = "MEETING_ID")
    String meetingId;

    @Column(name = "CLASS_NAME")
    String className;

    @Column(name = "CLASS_URL")
    String classUrl;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "DATE")
    String dateTime;

    @Column(name = "STATUS")
    char status;
}

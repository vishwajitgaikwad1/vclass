package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 29/6/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcement_mstr")
public class AnnouncementVO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ANNOUNCEMENT_MSTR_SEQ", unique = true, nullable = false)
    Integer announcementMstrSeq;

    @Column(name = "COURSE_MSTR_SEQ")
    Integer courseMstrSeq;

    @Column(name = "SEM")
    Integer sem;

    @Column(name = "FACULTY_MSTR_SEQ")
    Integer facultyMstrSeq;

    @Column(name = "NAME")
    String name;

    @Column(name = "FILE_NAME")
    String fileName;

    @Column(name = "FILE_PATH")
    String filePath;

}

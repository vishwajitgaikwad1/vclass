package com.vjti.model;

import com.vjti.model.SubmittedFilesVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 4/7/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assignment_mstr")
public class Assignment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ASSIGNMENT_MSTR_SEQ", unique = true, nullable = false)
    Integer assignmentMstrSeq;

    @Column(name = "COURSE_MSTR_SEQ")
    Integer courseMstrSeq;

    @Column(name = "SEM")
    Integer sem;

    @Column(name = "SUBJECT_MSTR_SEQ")
    Integer subjectMstrSeq;

    @Column(name = "FACULTY_MSTR_SEQ")
    Integer facultyMstrSeq;

    @Column(name = "ASSIGNMENT_NAME")
    String assignmentName;

    @Column(name = "MARKS")
    Integer marks;

    @Column(name = "FILE_NAME")
    String fileName;

    @Column(name = "FILE_PATH")
    String path;


    public Assignment(Integer courseMstrSeq, Integer sem, Integer subjectMstrSeq, Integer facultyMstrSeq, String assignmentName, Integer marks, String fileName, String path) {
        this.courseMstrSeq = courseMstrSeq;
        this.sem = sem;
        this.subjectMstrSeq = subjectMstrSeq;
        this.facultyMstrSeq = facultyMstrSeq;
        this.assignmentName = assignmentName;
        this.marks = marks;
        this.fileName = fileName;
        this.path = path;
    }
}

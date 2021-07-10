package com.vjti.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 4/7/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "submission_mstr")
public class SubmittedFilesVO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SUBMISSION_MSTR_SEQ", unique = true, nullable = false)
    Integer submissionMstrSeq;

    @Column(name = "ASSIGNMENT_MSTR_SEQ")
    Integer assignmentMstrSeq;

    @Column(name = "USER_MSTR_SEQ")
    Integer userMstrSeq;

    @Column(name = "COURSE_MSTR_SEQ")
    Integer courseMstrSeq;

    @Column(name = "SEM")
    Integer sem;

    @Column(name = "SUBJECT_MSTR_SEQ")
    Integer subjectMstrSeq;

    @Column(name = "MARKS")
    Integer marks;

    @Column(name = "STATUS")
    String status;

    @Column(name = "SUBMIT_DATE")
    String date;

    @Column(name = "FILE_NAME")
    String fileName;

    @Column(name = "FILE_PATH")
    String filePath;

    public SubmittedFilesVO(Integer submission_mstr_seq, Integer assignmentMstrSeq, Integer user_mstr_seq, Integer subject_mstr_seq, int marks, String status, String date, String fileName, String filePath) {
        this.submissionMstrSeq = submission_mstr_seq;
        this.assignmentMstrSeq = assignmentMstrSeq;
        this.userMstrSeq = user_mstr_seq;
        this.subjectMstrSeq = subject_mstr_seq;
        this.marks = marks;
        this.status = status;
        this.date = date;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}

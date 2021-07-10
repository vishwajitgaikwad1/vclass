package com.vjti.model;

import lombok.*;

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
public class AssignmentVO {
    Integer assignmentMstrSeq;
    Integer courseMstrSeq;
    Integer sem;
    Integer subjectMstrSeq;
    Integer facultyMstrSeq;
    String assignmentName;
    Integer marks;
    String fileName;
    String path;
    List<SubmittedFilesVO> submittedFilesList;

    public AssignmentVO(Integer courseMstrSeq, Integer sem, Integer subjectMstrSeq, Integer facultyMstrSeq, String assignmentName, Integer marks, String fileName, String path) {
        this.courseMstrSeq = courseMstrSeq;
        this.sem = sem;
        this.subjectMstrSeq = subjectMstrSeq;
        this.facultyMstrSeq = facultyMstrSeq;
        this.assignmentName = assignmentName;
        this.marks = marks;
        this.fileName = fileName;
        this.path = path;
    }

    public AssignmentVO(Integer assignmentMstrSeq, Integer faculty_mstr_seq, String assignment_name, Integer marks, String file_path, List<SubmittedFilesVO> submittedFilesVOList) {
        this.assignmentMstrSeq = assignmentMstrSeq;
        this.facultyMstrSeq = faculty_mstr_seq;
        this.assignmentName = assignment_name;
        this.marks = marks;
        this.path = file_path;
        this.submittedFilesList = submittedFilesVOList;
    }
}

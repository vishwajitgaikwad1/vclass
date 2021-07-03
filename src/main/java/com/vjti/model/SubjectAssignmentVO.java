package com.vjti.model;

import lombok.Data;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 4/7/21.
 */
@Data
public class SubjectAssignmentVO extends SubjectVO {
    List<AssignmentVO> assignmentList;

    public SubjectAssignmentVO(Integer sem, Integer subjectMstrSeq, String subject, List<AssignmentVO> assignmentList) {
        super(sem, subjectMstrSeq, subject);
        this.assignmentList = assignmentList;
    }
}

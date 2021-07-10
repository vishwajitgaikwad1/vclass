package com.vjti.model;

import lombok.Data;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 4/7/21.
 */
@Data
public class SemAssignmentVO extends SemVO {
    List<SubjectAssignmentVO> subjectList;

    public SemAssignmentVO(Integer sem, List<SubjectAssignmentVO> subjectList) {
        super(sem);
        this.subjectList = subjectList;
    }
}

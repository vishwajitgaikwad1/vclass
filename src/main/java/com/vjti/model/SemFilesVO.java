package com.vjti.model;

import lombok.*;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 1/6/21.
 */
@Data
public class SemFilesVO extends SemVO {
    List<SubjectFilesVO> subjectFilesVO;

    public SemFilesVO(Integer sem, List<SubjectFilesVO> subjectFilesVO) {
        super(sem);
        this.subjectFilesVO = subjectFilesVO;
    }
}

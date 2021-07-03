package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 30/5/21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectFilesVO extends SubjectVO {
    List<FileVO> files;

    public SubjectFilesVO(Integer sem, Integer subjectMstrSeq, String subject, List<FileVO> files) {
        super(sem, subjectMstrSeq, subject);
        this.files = files;
    }
}

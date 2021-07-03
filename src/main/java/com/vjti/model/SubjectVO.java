package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by vishwajit_gaikwad on 3/7/21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVO extends SemVO {
    Integer subjectMstrSeq;
    String subject;

    SubjectVO(Integer sem, Integer subjectMstrSeq, String subject){
        super(sem);
        this.subjectMstrSeq = subjectMstrSeq;
        this.subject = subject;
    }
}

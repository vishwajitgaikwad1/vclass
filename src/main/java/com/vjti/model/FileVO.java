package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 30/5/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files_mstr")
public class FileVO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "FILES_MSTR_SEQ", unique = true, nullable = false)
    Integer filesMstrSeq;

    @Column(name = "COURSE_MSTR_SEQ")
    Integer courseMstrSeq;

    @Column(name = "SEM")
    Integer sem;

    @Column(name = "SUBJECT_MSTR_SEQ")
    Integer subjectMstrSeq;

    @Column(name = "FILE_NAME")
    String fileName;

    @Column(name = "FILE_PATH")
    String filePath;

    public FileVO(Integer courseMstrSeq, Integer sem, Integer subjectMstrSeq, String fileName, String uploadDir) {
        this.courseMstrSeq=courseMstrSeq;
        this.sem=sem;
        this.subjectMstrSeq=subjectMstrSeq;
        this.fileName = fileName;
        this.filePath=uploadDir;
    }
}

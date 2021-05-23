package com.vjti.service;

import com.vjti.model.StudentVO;

import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
public interface IStudentService {
    StudentVO findStudentByUserMstrSeq(Integer userMstrSeq);

    String createStudentCookie(StudentVO studentVO);
}

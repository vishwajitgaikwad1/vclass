package com.vjti.service;

import com.vjti.model.FacultyVO;

import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
public interface IFacultyService {
    FacultyVO findFacultyByUserMstrSeq(Integer userMstrSeq);

    String createFacultyCookie(FacultyVO facultyVO);

    List<Map<String, Object>> fetchFacultyMatrix(Integer facultyMstrSeq);

    List<Map<String, Object>> fetchDistinctFacultyMatrix(Integer facultyMstrSeq);

    List<Map<String, Object>> fetchDistinctCourseFacultyMatrix(Integer facultyMstrSeq);

    List<Integer> fetchDistinctSemFacultyMatrix(Integer facultyMstrSeq);

    List<Map<String,Object>> fetchSubmittedFilesBySeq(Integer assignmentMstrSeq);

    List<Map<String,Object>> fetchDistinctFacultyMatrix(Integer integer, Integer sem);
}

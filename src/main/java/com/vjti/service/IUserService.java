package com.vjti.service;


import com.vjti.model.*;
import org.json.simple.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 12/5/21.
 */
public interface IUserService {

    Map<String, String> findByUserMstrSeq(Integer userMstrSeq, String role, String ifCookie);

    List<NewsVO> fetchNews();

    List<Map<String, Object>> fetchTimetableForUser(Integer courseMstrSeq, Integer sem);

    String fetchUserRoleBySeq(Integer userRoleMstrSeq);

    Integer fetchSemByCourseMstrSeq(Integer courseMstrSeq);

    List<Map<String, Object>> fetchSubjectsByCourseMstrSeqAndSem(Integer courseMstrSeq, Integer sem);

    List<FileVO> fetchFilesBySemAndSubjectMstrSeq(Integer sem, Integer subjectMstrSeq);

    String fetchCourseNameById(Integer courseMstrSeq);

    String fetchSubjectNameByIdAndSem(Integer subjectMstrSeq, Integer sem);

    List<RoomVO> fetchRoomsWithParams(List<Integer> courseMstrSeq, List<Integer> sem, List<Integer> subjectMstrSeq, Integer facultyMstrSeq);

    List<RoomVO> fetchRoomsBySemAndSubjectMstrSeq(Integer courseMstrSeq, Integer sem, Integer subjectMstrSeq);

    void saveFileVO(FileVO fileVO);

    void saveAnnouncementVO(AnnouncementVO announcementVO);

    JSONObject getRequestObject(String configName, ZoomCreate zoomCreate) throws Exception;

    List<Map<String,Object>> findAllByCourseMstrSeq(List<Integer> courseMstrSeq);

    List<Map<String,Object>> fetchAssignmentsBySeqAndSubjectSeq(Integer facultyMstrSeq, Integer subjectMstrSeq);

    List<Map<String,Object>> fetchAssignmentsBySubjectSeq(Integer subjectMstrSeq);

    List<Map<String,Object>> fetchSubmittedFilesByStudentSeqAndAssignmentSeq(Integer studentMstrSeq, Integer assignmentMstrSeq);

    void saveSubmittedFilesVO(SubmittedFilesVO submittedFilesVO);

    void saveAssignment(Assignment assignment);

    SubmittedFilesVO fetchSubmittedFilesBySeq(Integer submissionMstrSeq);

    List<Map<String,Object>> getEmailTemplate(String templateType, String templateCd);
}

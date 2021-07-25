package com.vjti.constant;

/**
 * Created by vishwajit_gaikwad on 6/5/21.
 */
public interface JdbcConstants {


    String FETCH_USER_LOGIN_BY_ID_AND_PASSWORD = "SELECT * FROM login_mstr WHERE LOGIN_ID =:loginId AND PASSWORD= :password;";
    String FETCH_USER_PROFILE_BY_USER_MSTR_SEQ_ID = "SELECT * FROM user_mstr WHERE USER_MSTR_SEQ= :userMstrSeq";

    String FETCH_STUDENT_BY_USER_MSTR_SEQ = "SELECT sm.*\n" +
            "FROM user_mstr um \n" +
            "INNER JOIN student_mstr sm \n" +
            "ON um.USER_MSTR_SEQ = sm.USER_MSTR_SEQ \n" +
            "WHERE um.USER_MSTR_SEQ = :userMstrSeq;";

    String FETCH_FACULTY_BY_USER_MSTR_SEQ = "SELECT fm.* \n" +
            "FROM user_mstr um \n" +
            "INNER JOIN faculty_mstr fm \n" +
            "ON um.USER_MSTR_SEQ = fm.USER_MSTR_SEQ \n" +
            "WHERE um.USER_MSTR_SEQ = :userMstrSeq;";

    //QUERY TO FETCH THE TIMETABLE FOR THE STUDENT OR THE FACULTY
    String FETCH_TIMETABLE_COURSE_MSTR_SEQ_AND_SEM = "SELECT tm.`TIME` , sm.SHORTHAND_NAME AS 'MON',\n" +
            "\t   sm_tue.SHORTHAND_NAME AS 'TUE',\n" +
            "\t   sm_wed.SHORTHAND_NAME AS 'WED',\n" +
            "\t   sm_thu.SHORTHAND_NAME AS 'THU',\n" +
            "\t   sm_fri.SHORTHAND_NAME AS 'FRI',\n" +
            "\t   sm_sat.SHORTHAND_NAME AS 'SAT'\t   \n" +
            "FROM south_vclass.timetable_mstr tm\n" +
            "LEFT JOIN south_vclass.shorthand_mstr sm \n" +
            "ON tm.MON = sm.SUBJECT_MSTR_SEQ \n" +
            "LEFT JOIN south_vclass.shorthand_mstr sm_tue\n" +
            "ON tm.TUE = sm_tue.SUBJECT_MSTR_SEQ\n" +
            "LEFT JOIN south_vclass.shorthand_mstr sm_wed\n" +
            "ON tm.WED = sm_wed.SUBJECT_MSTR_SEQ \n" +
            "LEFT JOIN south_vclass.shorthand_mstr sm_thu\n" +
            "ON tm.THU = sm_thu.SUBJECT_MSTR_SEQ \n" +
            "LEFT JOIN south_vclass.shorthand_mstr sm_fri\n" +
            "ON tm.FRI = sm_fri.SUBJECT_MSTR_SEQ \n" +
            "LEFT JOIN south_vclass.shorthand_mstr sm_sat\n" +
            "ON tm.SAT = sm_sat.SUBJECT_MSTR_SEQ \n" +
            "WHERE tm.COURSE_MSTR_SEQ = :courseMstrSeq\n" +
            "AND tm.SEM = :sem;";

    String FETCH_USER_ROLE_BY_SEQ = "SELECT * FROM user_role_mstr WHERE USER_ROLE_MSTR_SEQ = :userRoleMstrSeq";

    //QUERY TO FETCH THE COURSE, SUBJECT NAME AND OTHER DETAIL TAUGHT BY THE FACULTY.
    String FETCH_FACULTY_MATRIX_BY_SEQ = "SELECT cm.COURSE_MSTR_SEQ, cm.COURSE_NAME , sm.SUBJECT_NAME , fmm.SEM \n" +
            "FROM south_vclass.faculty_matrix_mstr fmm \n" +
            "LEFT JOIN south_vclass.course_mstr cm \n" +
            "ON fmm.COURSE_MSTR_SEQ = cm.COURSE_MSTR_SEQ \n" +
            "LEFT JOIN south_vclass.subject_mstr sm\n" +
            "ON fmm.SUBJECT_MSTR_SEQ = sm.SUBJECT_MSTR_SEQ \n" +
            "WHERE fmm.FACULTY_MSTR_SEQ = :facultyMstrSeq;";

    String FETCH_DISTINCT_FACULTY_MATRIX_BY_SEQ = "SELECT DISTINCT fmm.COURSE_MSTR_SEQ ,cm.COURSE_NAME ,fmm.SUBJECT_MSTR_SEQ , sm.SUBJECT_NAME , fmm.SEM \n" +
            "FROM south_vclass.faculty_matrix_mstr fmm \n" +
            "INNER JOIN south_vclass.course_mstr cm \n" +
            "ON fmm.COURSE_MSTR_SEQ = cm.COURSE_MSTR_SEQ \n" +
            "INNER JOIN south_vclass.subject_mstr sm\n" +
            "ON fmm.SUBJECT_MSTR_SEQ = sm.SUBJECT_MSTR_SEQ \n" +
            "WHERE fmm.FACULTY_MSTR_SEQ = :facultyMstrSeq;";

    String FETCH_DISTINCT_FACULTY_MATRIX_BY_SEQ_AND_SEM = "SELECT DISTINCT fmm.COURSE_MSTR_SEQ ,cm.COURSE_NAME ,fmm.SUBJECT_MSTR_SEQ , sm.SUBJECT_NAME , fmm.SEM \n" +
            "FROM south_vclass.faculty_matrix_mstr fmm \n" +
            "INNER JOIN south_vclass.course_mstr cm \n" +
            "ON fmm.COURSE_MSTR_SEQ = cm.COURSE_MSTR_SEQ \n" +
            "INNER JOIN south_vclass.subject_mstr sm\n" +
            "ON fmm.SUBJECT_MSTR_SEQ = sm.SUBJECT_MSTR_SEQ \n" +
            "WHERE fmm.FACULTY_MSTR_SEQ = :facultyMstrSeq \n"+
            "AND fmm.SEM = :sem;";

    String FETCH_DISTINCT_COURSE_MATRIX_BY_SEQ = "SELECT DISTINCT cm.COURSE_MSTR_SEQ ,cm.COURSE_NAME \n" +
            "FROM south_vclass.faculty_matrix_mstr fmm \n" +
            "INNER JOIN south_vclass.course_mstr cm \n" +
            "ON fmm.COURSE_MSTR_SEQ = cm.COURSE_MSTR_SEQ \n" +
            "INNER JOIN south_vclass.subject_mstr sm\n" +
            "ON fmm.SUBJECT_MSTR_SEQ = sm.SUBJECT_MSTR_SEQ \n" +
            "WHERE fmm.FACULTY_MSTR_SEQ = :facultyMstrSeq;";

    String FETCH_DISTINCT_SEM_MATRIX_BY_SEQ = "SELECT DISTINCT fmm.SEM \n" +
            "FROM south_vclass.faculty_matrix_mstr fmm \n" +
            "INNER JOIN south_vclass.course_mstr cm \n" +
            "ON fmm.COURSE_MSTR_SEQ = cm.COURSE_MSTR_SEQ \n" +
            "INNER JOIN south_vclass.subject_mstr sm\n" +
            "ON fmm.SUBJECT_MSTR_SEQ = sm.SUBJECT_MSTR_SEQ \n" +
            "WHERE fmm.FACULTY_MSTR_SEQ = :facultyMstrSeq;";

    String FETCH_SEM_BY_COURSE_MSTR_SEQ = "SELECT SEM\n" +
            "FROM course_mstr cm \n" +
            "WHERE COURSE_MSTR_SEQ =:courseMstrSeq;";

    String FETCH_SUBJECTS_BY_COURSE_MSTR_SEQ_AND_SEM="SELECT *\n" +
            "FROM subject_mstr sm \n" +
            "WHERE COURSE_MSTR_SEQ =:courseMstrSeq AND SEM =:sem;";

    String FETCH_COURSE_BY_COURSE_MSTR_SEQ = "SELECT COURSE_NAME\n" +
            "FROM course_mstr cm \n" +
            "WHERE COURSE_MSTR_SEQ =:courseMstrSeq;";

    String FETCH_SUBJECT_BY_SUBJECT_MSTR_SEQ = "SELECT SUBJECT_NAME\n" +
            "FROM subject_mstr sm \n" +
            "WHERE SUBJECT_MSTR_SEQ =:subjectMstrSeq AND SEM =:sem;";

    String FETCH_ROOMS_WITH_PARAMS = "SELECT cm2.COURSE_NAME, sm.SUBJECT_NAME, cm.SEM, cm.MEETING_ID, cm.CLASS_NAME, cm.CLASS_URL, cm.PASSWORD, cm.`DATE`\n" +
            "FROM classroom_mstr cm \n" +
            "INNER JOIN course_mstr cm2 \n" +
            "ON cm.COURSE_MSTR_SEQ = cm2.COURSE_MSTR_SEQ \n" +
            "INNER JOIN faculty_mstr fm \n" +
            "ON cm.FACULTY_MSTR_SEQ = fm.FACULTY_MSTR_SEQ \n" +
            "INNER JOIN subject_mstr sm \n" +
            "ON cm.SUBJECT_MSTR_SEQ = sm.SUBJECT_MSTR_SEQ \n" +
            "WHERE cm.COURSE_MSTR_SEQ IN (:courseMstrSeq)\n" +
            "AND cm.SEM IN (:sem)\n" +
            "AND cm.SUBJECT_MSTR_SEQ IN (:subjectMstrSeq)\n" +
            "AND cm.FACULTY_MSTR_SEQ = :facultyMstrSeq \n" +
            "AND cm.STATUS ='A';";

    String FETCH_ROOMS_WITH_CMS_SEM_AND_SMS="SELECT fm.FIRSTNAME, cm.CLASS_NAME, cm.CLASS_URL, cm.PASSWORD, cm.`DATE`\n" +
            "FROM classroom_mstr cm \n" +
            "INNER JOIN course_mstr cm2 \n" +
            "ON cm.COURSE_MSTR_SEQ = cm2.COURSE_MSTR_SEQ \n" +
            "INNER JOIN faculty_mstr fm \n" +
            "ON cm.FACULTY_MSTR_SEQ = fm.FACULTY_MSTR_SEQ \n" +
            "INNER JOIN subject_mstr sm \n" +
            "ON cm.SUBJECT_MSTR_SEQ = sm.SUBJECT_MSTR_SEQ \n" +
            "WHERE cm.COURSE_MSTR_SEQ = :courseMstrSeq\n" +
            "AND cm.SEM = :sem\n" +
            "AND cm.SUBJECT_MSTR_SEQ = :subjectMstrSeq\n" +
            "AND cm.STATUS ='A';";

    String FETCH_ANNOUNCEMENT = "SELECT cm.COURSE_NAME , am.SEM , am.NAME , am.FILE_NAME , am.FILE_PATH \n" +
            "FROM announcement_mstr am \n" +
            "INNER JOIN course_mstr cm \n" +
            "ON cm.COURSE_MSTR_SEQ = am.COURSE_MSTR_SEQ \n" +
            "WHERE am.COURSE_MSTR_SEQ IN(:courseMstrSeq);";

    String FETCH_ASSIGNMENT_BY_FACULTY_AND_SUBJECT_SEQ= "SELECT *\n" +
            "FROM assignment_mstr am \n" +
            "WHERE am.FACULTY_MSTR_SEQ = :facultyMstrSeq\n" +
            "AND am.SUBJECT_MSTR_SEQ = :subjectMstrSeq;";

    String FETCH_ASSIGNMENT_BY_SUBJECT_SEQ= "SELECT *\n" +
            "FROM assignment_mstr am \n" +
            "WHERE am.SUBJECT_MSTR_SEQ = :subjectMstrSeq;";

    String COURSE_MSTR_SEQ = "courseMstrSeq";
    String SEM = "sem";
    String USER_ROLE_MSTR_SEQ = "userRoleMstrSeq";
    String USER_MSTR_SEQ = "userMstrSeq";
    String FACULTY_MSTR_SEQ = "facultyMstrSeq";
    String SUBJECT_MSTR_SEQ = "subjectMstrSeq";
    String ASSIGNMENT_MSTR_SEQ = "assignmentMstrSeq";
    String STUDENT_MSTR_SEQ = "studentMstrSeq";
    String TEMPLATE_CD = "templateCd";
    String TEMPLATE_TYPE = "templateType";


    String CONFIG_NAME = "configName";
    String FETCH_REQUEST_BY_CONFIGNAME = "SELECT VALUE \n"+
            "FROM config_mstr cm \n"+
            "WHERE CONFIG_NAME = :configName ;";


    String FETCH_SUBMITTED_FILES_BY_ASSIGNMENT_SEQ="SELECT *\n" +
            "FROM submission_mstr sm \n" +
            "WHERE sm.ASSIGNMENT_MSTR_SEQ = :assignmentMstrSeq;";

    String FETCH_SUBMITTED_FILES_BY_STUDENT_MSTR_SEQ_AND_ASSIGNMENT_SEQ="SELECT *\n" +
            "FROM submission_mstr sm \n" +
            "WHERE sm.ASSIGNMENT_MSTR_SEQ = :assignmentMstrSeq\n" +
            "AND sm.USER_MSTR_SEQ = :studentMstrSeq;";

    String FETCH_EMAIL_TEMPLATE_BY_TEMP_CD_AND_TYPE = "SELECT * \n" +
            "FROM email_template_mstr etm \n" +
            "WHERE etm.TEMPLATE_TYPE = :templateType\n" +
            "AND etm.TEMPLATE_CD = :templateCd;";

}

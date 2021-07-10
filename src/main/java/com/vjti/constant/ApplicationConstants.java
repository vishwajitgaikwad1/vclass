package com.vjti.constant;

/**
 * Created by vishwajit_gaikwad on 15/5/21.
 */
public interface ApplicationConstants {
     char CHAR_Y = 'Y';

//COOKIE CONSTANTS
    String COOKIE_USER = "COOKIE_USER";
    String COOKIE_USER_PROFILE = "COOKIE_USER_PROFILE";
    String COOKIE_LOGIN = "COOKIE_LOGIN";

//PARAM CONSTANTS
    String ERRORPARAM = "error";
    String COURSEPARAM = "course";
    String SEMPARAM = "sem";
    String ACTIONPARAM = "action";
    String SUBJECTPARAM = "subject";
    String FILEPARAM = "file";
    String CODEPARAM = "code";
    String AUTHPARAM = "auth";
    String ASSIGNMENTPARAM = "assignment";
    String UPLOADPARAM = "upload";
    String ANNOUNCEMENTNAMEPARAM = "announcementName";
    String MARKSPARAM = "marks";
    String ASSIGNMENTNAMEPARAM = "assignmentName";

    String ACTION_ANNOUNCEMENT = "announcement";
    String ACTION_NOTES = "notes";
    String ACTION_NEWS = "news";
    String ACTION_ASSIGNMENT = "assignment";

//ZOOM PARAMS CONSTANT
    String TOPICPARAM = "topic";
    String AGENDAPARAM = "agenda";
    String DATEPARAM = "date";
    String PASSPARAM = "pass";
    String MEETINGIDPARAM = "meetingId";
    String DATAPARAM = "requestData";
    String GRANT_TYPE_PARAM = "grant_type";
    String REFRESH_TOKEN_PARAM = "refresh_token";
    String ACCESS_TOKEN_PARAM = "access_token";

//APPLICATION CONSTANTS
    String STR_EMPTY ="";
    String STR_Y = "Y";
    String STR_N = "Y";
    String STR_NA = "NA";
    String RESPONSE = "RESPONSE";
    String LOGIN_ID = "LOGIN_ID";
    String USER_MSTR_SEQ = "USER_MSTR_SEQ";
    String SUCCESS = "SUCCESS";
    String FAILED = "FAILED";
    String BOOLEAN_TRUE = "TRUE";
    String BOOLEAN_FALSE = "FALSE";
    String LOGGED_IN = "LOGGED_IN";
    String EMAIL = "EMAIL";
    String FIRSTNAME = "FIRSTNAME";
    String LASTNAME = "LASTNAME";
    String COURSE_MSTR_SEQ = "COURSE_MSTR_SEQ";
    String SEM = "SEM";
    String USER_ROLE_MSTR_SEQ = "USER_ROLE_MSTR_SEQ";
    String ROLE = "ROLE";
    String STUDENT = "STUDENT";
    String FACULTY = "FACULTY";
    String STUDENT_MSTR_SEQ = "STUDENT_MSTR_SEQ";
    String FACULTY_MSTR_SEQ = "FACULTY_MSTR_SEQ";

//MODEL CONSTANTS
    String TIMETABLE_MODEL = "TIMETABLE_MODEL";
    String ROLE_MODEL = "ROLE_MODEL";
    String NEWS_MODEL = "NEWS_MODEL";
    String FACULTY_MATRIX_MODEL = "FACULTY_MATRIX_MODEL";
    String FILES_VO_LIST_MODEL = "FILES_VO_LIST_MODEL";
    String SEM_VO_LIST_MODEL = "SEM_VO_LIST_MODEL";
    String COURSE_MATRIX_MODEL = "COURSE_MATRIX_MODEL";
    String ROOM_VO_LIST_MODEL = "ROOM_VO_LIST_MODEL";
    String ANNOUNEMENT_VO_LIST_MODEL = "ANNOUNCEMENT_VO_LIST_MODEL";
    String FACULTY_ASSIGNMENT_MODEL = "FACULTY_ASSIGNMENT_MODEL";
    String STUDENT_ASSIGNMENT_MODEL = "STUDENT_ASSIGNMENT_MODEL";

//ZOOM CONSTANTS
    public static final String NO_OAUTH_CODE_FOUND = "No authorization code found.";
    public static final String VC_UNABLE_TO_GENERATE_TOKEN = "Failed to generate token.";

//REST-POST CONSTANTS
    String REQUEST_AUTHORIZATION = "Authorization";

// ZOOM CONSTANTS
    String ZOOM_CLIENT_ID = "kgYUMMChRnWlQ_WGfQiUlg";
    String ZOOM_CLIENT_SECRET = "PHD6jUU9p6nJczVK16J8u4pecU4vgAsT";
    String COOKIE_ZOOM = "COOKIE_ZOOM";
    String STR_COMMA_AND_SPACE = ", ";
    String STR_COLON = ":";
    Integer CONVERT_TO_TIME = 60000;
    String QUARTZ_UNIT_HOUR = "HOUR";
    String QUARTZ_UNIT_MIN = "MIN";
    String QUARTZ_UNIT_SEC = "SEC";


    /*
    * String zoomClientId = "kgYUMMChRnWlQ_WGfQiUlg";
     String zoomClientSecret = "PHD6jUU9p6nJczVK16J8u4pecU4vgAsT";*/
}

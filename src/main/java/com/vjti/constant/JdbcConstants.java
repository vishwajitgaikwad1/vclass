package com.vjti.constant;

/**
 * Created by vishwajit_gaikwad on 6/5/21.
 */
public interface JdbcConstants {

    String FETCH_USER_LOGIN_BY_ID_AND_PASSWORD = "SELECT * FROM login_mstr WHERE LOGIN_ID =:loginId AND PASSWORD= :password;";
    String FETCH_USER_PROFILE_BY_LOGIN_ID = "SELECT * FROM user_mstr WHERE USER_MSTR_SEQ= :userMstrSeq";
}

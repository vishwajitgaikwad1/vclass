package com.vjti.model;

import com.vjti.constant.ApplicationConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 11/5/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_mstr")
public class LoginVO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_MSTR_SEQ", unique = true, nullable = false)
    Integer userMstrSeq;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "FIRSTNAME")
    String firstname;

    @Column(name = "LASTNAME")
    String lastname;

    @Column(name = "LOGIN_ID")
    String loginId;

    @Column(name = "PASS")
    String password;

    @Column(name = "USER_ROLE_MSTR_SEQ")
    Integer userRoleMstrSeq;

    @Override
    public String toString() {
        return "LoginVO{" +
                "userMstrSeq=" + userMstrSeq +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", userRoleMstrSeq=" + userRoleMstrSeq +
                '}';
    }

    public String toCookieString(){
        String cookieString = ApplicationConstants.LOGIN_ID + ":" + loginId + "\t"
                + ApplicationConstants.USER_MSTR_SEQ + ":" + userMstrSeq + "\t"
                + ApplicationConstants.USER_ROLE_MSTR_SEQ + ":" + userRoleMstrSeq;
        return cookieString;
    }

}

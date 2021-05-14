package com.vjti.model;

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
@Table(name = "login_mstr")
public class LoginVO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "LOGIN_MSTR_SEQ", unique = true, nullable = false)
    Integer loginMstrSeq;

    @Column(name = "LOGIN_ID")
    String loginId;

    @Column(name = "USER_MSTR_SEQ")
    Integer userMstrSeq;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "PASSWORD")
    String password;

//    public LoginVO(Integer login_mstr_seq, String login_id, Integer user_mstr_seq, String email, String password) {
//        this.loginMstrSeq = login_mstr_seq;
//        this.loginId = login_id;
//        this.userMstrSeq = user_mstr_seq;
//        this.email = email;
//        this.password = password;
//    }


    @Override
    public String toString() {
        return "LoginVO{" +
                "loginMstrSeq=" + loginMstrSeq +
                ", loginId='" + loginId + '\'' +
                ", userMstrSeq=" + userMstrSeq +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

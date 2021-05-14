package com.vjti.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vishwajit_gaikwad on 12/5/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_mstr")
public class UserVO {
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
    @Column(name = "COURSE_MSTR_SEQ")
    Integer courseMstrSeq;
    @Column(name = "SEM")
    Integer sem;

    @Override
    public String toString() {
        return "UserVO{" +
                "userMstrSeq=" + userMstrSeq +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", courseMstrSeq=" + courseMstrSeq +
                ", sem=" + sem +
                '}';
    }
}

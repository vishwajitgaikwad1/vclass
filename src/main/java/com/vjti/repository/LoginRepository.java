package com.vjti.repository;

import com.vjti.model.LoginVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by vishwajit_gaikwad on 11/5/21.
 */
@Repository
public interface LoginRepository extends JpaRepository<LoginVO,Integer> {

    @Query("SELECT lm FROM LoginVO lm WHERE lm.loginId = :loginId and lm.password = :password")
    LoginVO findLoginByLoginIdAndPassword(
            @Param("loginId") String loginId,
            @Param("password") String password);

}

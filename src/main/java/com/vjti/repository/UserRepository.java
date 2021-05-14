package com.vjti.repository;

import com.vjti.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vishwajit_gaikwad on 12/5/21.
 */
@Repository
public interface UserRepository extends JpaRepository<UserVO,Integer> {
}

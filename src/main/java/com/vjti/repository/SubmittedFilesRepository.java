package com.vjti.repository;

import com.vjti.model.SubmittedFilesVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vishwajit_gaikwad on 8/7/21.
 */
@Repository
public interface SubmittedFilesRepository extends JpaRepository<SubmittedFilesVO,Integer> {
}

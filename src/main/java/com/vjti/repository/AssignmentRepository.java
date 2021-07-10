package com.vjti.repository;

import com.vjti.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vishwajit_gaikwad on 9/7/21.
 */
@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
}

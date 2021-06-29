package com.vjti.repository;

import com.vjti.model.ClassroomVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vishwajit_gaikwad on 26/6/21.
 */
@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomVO,Integer> {

    ClassroomVO findByMeetingId(String meetingId);
}

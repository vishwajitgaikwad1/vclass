package com.vjti.repository;

import com.vjti.model.AnnouncementVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 29/6/21.
 */
@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementVO,Integer> {

    List<AnnouncementVO> findAllByCourseMstrSeq(Integer courseMstrSeq);
}

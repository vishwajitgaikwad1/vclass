package com.vjti.repository;

import com.vjti.model.FileVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vishwajit_gaikwad on 30/5/21.
 */
@Repository
public interface FileRepository extends JpaRepository<FileVO, Integer> {
     List<FileVO> findAllBySemAndSubjectMstrSeq(Integer sem, Integer subjectMstrSeq);
}

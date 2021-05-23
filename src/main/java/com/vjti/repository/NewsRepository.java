package com.vjti.repository;

import com.vjti.model.NewsVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vishwajit_gaikwad on 22/5/21.
 */
@Repository
public interface NewsRepository extends JpaRepository<NewsVO, Integer> {
}

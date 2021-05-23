package com.vjti.service;


import com.vjti.model.NewsVO;

import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 12/5/21.
 */
public interface IUserService {

    Map<String, String> findByUserMstrSeq(Integer userMstrSeq, String role, String ifCookie);

    List<NewsVO> fetchNews();

    List<Map<String, Object>> fetchTimetableForUser(Integer courseMstrSeq, Integer sem);

    String fetchUserRoleBySeq(Integer userRoleMstrSeq);
}

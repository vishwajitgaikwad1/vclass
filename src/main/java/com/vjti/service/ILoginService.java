package com.vjti.service;

import com.vjti.model.LoginVO;

import java.util.Map;
import java.util.Optional;

/**
 * Created by vishwajit_gaikwad on 11/5/21.
 */
public interface ILoginService {

    LoginVO findById(Integer Id);

    Map<String, String> findByLoginIdAndPassword(String loginId, String password);
}

package com.vjti.service.impl;

import com.vjti.constant.JdbcConstants;
import com.vjti.model.LoginVO;
import com.vjti.repository.LoginRepository;
import com.vjti.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vishwajit_gaikwad on 11/5/21.
 */
@Service
public class LoginService implements ILoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public LoginVO findById(Integer Id) {
        return loginRepository.getOne(Id);
    }

    public Map<String, String> findByLoginIdAndPassword(String loginId, String password){

     /*   MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("loginId", loginId);
        map.addValue("password", password);
        List<Map<String, Object>> loginList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_USER_LOGIN_BY_ID_AND_PASSWORD, map);

        if(loginList!=null && loginList.size()>0){
         LoginVO login = convertListToLoginVO(loginList.get(0));
        }*/

     LoginVO login = loginRepository.findUserByLoginIdAndPassword(loginId,password);
        if(login !=null){
            Map<String,String> response = new HashMap<>();
            response.put("RESPONSE", "SUCCESS");
            response.put("LOGIN_ID", login.getLoginId());
            response.put("USER_MSTR_SEQ", login.getUserMstrSeq().toString());
            return response;
        }
        Map<String,String> response = new HashMap<>();
        response.put("RESPONSE", "FAILED");

        return response;
    }

    private LoginVO convertListToLoginVO(Map<String, Object> loginList) {
        LoginVO login = new LoginVO(Integer.valueOf(loginList.get("LOGIN_MSTR_SEQ").toString()),
                                    loginList.get("LOGIN_ID").toString(),
                                    Integer.valueOf(loginList.get("USER_MSTR_SEQ").toString()),
                                    loginList.get("EMAIL").toString(),
                                    loginList.get("PASSWORD").toString());

        return login;
    }
}

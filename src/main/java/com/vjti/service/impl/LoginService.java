package com.vjti.service.impl;

import com.vjti.constant.ApplicationConstants;
import com.vjti.model.LoginVO;
import com.vjti.repository.LoginRepository;
import com.vjti.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by vishwajit_gaikwad on 11/5/21.
 */
@Service
public class LoginService implements ILoginService {

    @Autowired
    private LoginRepository loginRepository;

//    @Autowired
//    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public LoginVO findById(Integer Id) {
        return loginRepository.getOne(Id);
    }

    public Map<String, String> findByLoginIdAndPassword(String loginId, String password)
    {
        Map<String,String> response = new HashMap<>();
        response.put(ApplicationConstants.RESPONSE, ApplicationConstants.FAILED);
        LoginVO loginVO = loginRepository.findLoginByLoginIdAndPassword(loginId,password);
            if(loginVO !=null)
            {
                response.put(ApplicationConstants.RESPONSE, ApplicationConstants.SUCCESS);
                response.put(ApplicationConstants.LOGIN_ID, loginVO.getLoginId());
                response.put(ApplicationConstants.USER_MSTR_SEQ, loginVO.getUserMstrSeq().toString());
                response.put(ApplicationConstants.USER_ROLE_MSTR_SEQ, loginVO.getUserRoleMstrSeq().toString());
                response.put(ApplicationConstants.COOKIE_LOGIN, loginVO.toCookieString());

                return response;
            }
        return response;
    }

}



     /*   MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("loginId", loginId);
        map.addValue("password", password);
        List<Map<String, Object>> loginList = namedParameterJdbcTemplate.queryForList(JdbcConstants.FETCH_USER_LOGIN_BY_ID_AND_PASSWORD, map);

        if(loginList!=null && loginList.size()>0){
         LoginVO loginVO = convertListToLoginVO(loginList.get(0));
        }*/
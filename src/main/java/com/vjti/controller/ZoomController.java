package com.vjti.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vjti.common.CommonUtil;
import com.vjti.common.CommonWebUtil;
import com.vjti.common.DateUtil;
import com.vjti.constant.ApplicationConstants;
import com.vjti.model.ClassroomVO;
import com.vjti.model.ZoomCreate;
import com.vjti.repository.ClassroomRepository;
import com.vjti.service.IFacultyService;
import com.vjti.service.IUserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.apache.tomcat.util.net.SocketEvent.TIMEOUT;

/**
 * Created by vishwajit_gaikwad on 10/6/21.
 */
@Controller
@RequestMapping("/zoom")
public class ZoomController {

//    @Autowired
//    private PropertiesUtil propertiesUtil;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    IFacultyService facultyService;

    @Autowired
    IUserService userService;

    @Autowired
    ClassroomRepository classroomRepository;

@RequestMapping("/signin")
 public String zoomSignin(){
    return "redirect:https://zoom.us/oauth/authorize?response_type=code&client_id=kgYUMMChRnWlQ_WGfQiUlg&redirect_uri=http://localhost:8080/zoom/oauthredirect";
//    return "testpage";
}

    @RequestMapping("/oauthredirect")
    public String oAuthRedirect(@RequestParam(name = ApplicationConstants.CODEPARAM,defaultValue = "") String code,
                                HttpServletResponse response){
        String path = "https://zoom.us/oauth/token?code="+code+"&grant_type=authorization_code&redirect_uri=http://localhost:8080/zoom/oauthredirect";
        String authcode = CommonUtil.generateBasicAuth(ApplicationConstants.ZOOM_CLIENT_ID,ApplicationConstants.ZOOM_CLIENT_SECRET);
        HttpHeaders headers = new HttpHeaders();
        headers.add(ApplicationConstants.REQUEST_AUTHORIZATION,authcode);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HttpEntity httpEntity = new HttpEntity(null,headers);
        ResponseEntity<String> responseEntity =  restTemplate.exchange(path, HttpMethod.POST,httpEntity,String.class);
        System.out.println("Response Entity"+responseEntity);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object s = mapper.readValue(responseEntity.getBody(),HashMap.class);
            Map<String, Object > responseMap = mapper.convertValue(s, Map.class);
            String zoomcookieString = ApplicationConstants.REFRESH_TOKEN_PARAM + ":" + responseMap.get(ApplicationConstants.REFRESH_TOKEN_PARAM).toString() + "\t"
                                        + ApplicationConstants.ACCESS_TOKEN_PARAM + ":" + responseMap.get(ApplicationConstants.ACCESS_TOKEN_PARAM).toString();
            CommonWebUtil.createCookie(ApplicationConstants.COOKIE_ZOOM,zoomcookieString, response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/faculty/classroom?auth=success";
    }

    @RequestMapping("/createmeeting")
    public String createMeeting(@ModelAttribute(name = "accessToken")String accessToken,
                                @ModelAttribute(name = "zoomCreateVO")ZoomCreate zoomCreateVO,
                                @CookieValue(name = ApplicationConstants.COOKIE_ZOOM)String zoomCookie,
                                @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE)String userProfileCookie){

        if(zoomCookie.length()>0){
            JSONObject request=null;
            try{
                request = userService.getRequestObject("CREATE_MEETING",zoomCreateVO);
            }catch (Exception e){
                System.out.println(e);
            }

            String path = "https://api.zoom.us/v2/users/me/meetings";

            String authcode = "Bearer " + accessToken;
            HttpHeaders headers = new HttpHeaders();
            headers.add(ApplicationConstants.REQUEST_AUTHORIZATION,authcode);
            headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity httpEntity = new HttpEntity(request,headers);

            try{
                ResponseEntity<String> responseEntity =  restTemplate.exchange(path, HttpMethod.POST,httpEntity,String.class);

                if(responseEntity!=null && responseEntity.getStatusCode().equals(HttpStatus.CREATED)){
                    ObjectMapper mapper = new ObjectMapper();
                    Object s = mapper.readValue(responseEntity.getBody(),HashMap.class);
                    Map<String,Object> responseMap = mapper.convertValue(s, Map.class);


                    String dateTime = zoomCreateVO.getStart_time().replace("T"," ");

                    Map<String,String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                    ClassroomVO classroomVO = new ClassroomVO(null,
                            Integer.valueOf(zoomCreateVO.getCourse()),
                            Integer.valueOf(zoomCreateVO.getSem()),
                            Integer.valueOf(zoomCreateVO.getSubject()),
                            Integer.valueOf(userProfileCookieMap.get("FACULTY_MSTR_SEQ")),
                            responseMap.get("id").toString(),
                            responseMap.get("topic").toString(),
                            responseMap.get("join_url").toString(),
                            responseMap.get("password").toString(),
                            dateTime,
                            'A',
                            Integer.valueOf(userProfileCookieMap.get("FACULTY_MSTR_SEQ")),
                            DateUtil.getCurrentDate(),
                            null,
                            null);

                    classroomRepository.save(classroomVO);
                    return "redirect:/faculty/classroom?auth=create_success";
                }
            }catch (Exception e){
                System.out.println(e);
            }

            return "redirect:/faculty/classroom?auth=create_fail";
        }

        return "redirect:/faculty/classroom";
    }

    @RequestMapping("/deletemeeting")
    public String deleteMeeting(@ModelAttribute(name = "accessToken")String accessToken,
                                @ModelAttribute(name = "zoomDeleteVO")ZoomCreate zoomCreateVO,
                                @CookieValue(name = ApplicationConstants.COOKIE_ZOOM)String zoomCookie,
                                @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE)String userProfileCookie){

        if(zoomCookie.length()>0){

            String path = "https://api.zoom.us/v2/meetings/";
            String newPath = path + zoomCreateVO.getMeetingId();

            String authcode = "Bearer " + accessToken;
            HttpHeaders headers = new HttpHeaders();
            headers.add(ApplicationConstants.REQUEST_AUTHORIZATION,authcode);
            headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity httpEntity = new HttpEntity(null,headers);

            try{
                try{
                    ResponseEntity<String> responseEntity =  restTemplate.exchange(newPath, HttpMethod.DELETE,httpEntity,String.class);

                }catch (Exception e){

                }

                Map<String, String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                ClassroomVO classroomVO = classroomRepository.findByMeetingId(zoomCreateVO.getMeetingId());
                classroomVO.setStatus('I');
                classroomVO.setUpdatedBy(Integer.valueOf(userProfileCookieMap.get("FACULTY_MSTR_SEQ")));
                classroomVO.setUpdateDttm(DateUtil.getCurrentDate());
                classroomRepository.save(classroomVO);
                return "redirect:/faculty/classroom?auth=delete_success";

            }catch (Exception e){
                System.out.println(e);
            }
            return "redirect:/faculty/classroom?auth=delete_fail";
        }
        return "redirect:/faculty/classroom";
    }

    @RequestMapping("/updatemeeting")
    public String updateMeeting(@ModelAttribute(name = "accessToken")String accessToken,
                                @ModelAttribute(name = "zoomUpdateVO")ZoomCreate zoomCreateVO,
                                @CookieValue(name = ApplicationConstants.COOKIE_ZOOM)String zoomCookie,
                                @CookieValue(name = ApplicationConstants.COOKIE_USER_PROFILE)String userProfileCookie){

        if(zoomCookie.length()>0){

            JSONObject request = new JSONObject();

            try{
                Field fields[] = ZoomCreate.class.getDeclaredFields();
                for(Field field: fields){
                    Object dataVal = CommonUtil.callGetterMethod(ZoomCreate.class,zoomCreateVO,field.getName());
                    if(dataVal!=null && !dataVal.equals("") && !field.getName().equals("meetingId")){
                        request.put(field.getName(),dataVal);
                    }
                }

                Map<String, String> zoomCookieMap = CommonWebUtil.fetchCookie(zoomCookie);
                String path = "https://api.zoom.us/v2/meetings/";
                String newPath = path + zoomCreateVO.getMeetingId();
                String authcode = "Bearer " + accessToken;
                HttpHeaders headers = new HttpHeaders();
                headers.add(ApplicationConstants.REQUEST_AUTHORIZATION,authcode);
                headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                HttpEntity httpEntity = new HttpEntity(request,headers);
                ResponseEntity<String> responseEntity =  restTemplate.exchange(newPath, HttpMethod.PATCH,httpEntity,String.class);

                Map<String, String> userProfileCookieMap = CommonWebUtil.fetchCookie(userProfileCookie);
                ClassroomVO classroomVO = classroomRepository.findByMeetingId(zoomCreateVO.getMeetingId());
                if(zoomCreateVO.getTopic()!=null){
                    classroomVO.setClassName(zoomCreateVO.getTopic());
                }
                if(zoomCreateVO.getStart_time()!=null){
                    classroomVO.setDateTime(zoomCreateVO.getStart_time().replace("T"," "));
                }

                classroomVO.setUpdatedBy(Integer.valueOf(userProfileCookieMap.get("FACULTY_MSTR_SEQ")));
                classroomVO.setUpdateDttm(DateUtil.getCurrentDate());
                classroomRepository.save(classroomVO);
                return "redirect:/faculty/classroom?auth=update_success";


            }catch (Exception e){
                System.out.println(CommonUtil.getStackTrace(e));
            }

            //update db
            return "redirect:/faculty/classroom?auth=update_fail";
        }
        return "redirect:/faculty/classroom";
    }


    @RequestMapping("/refreshtoken")
    public RedirectView refreshToken(@ModelAttribute("zoomCreateVO") ZoomCreate zoomCreateVO,
                                     @RequestParam(name = "action", defaultValue = "") String action,
                                     @CookieValue(name = ApplicationConstants.COOKIE_ZOOM)String zoomCookie,
                                     HttpServletResponse response,
                                     RedirectAttributes redir){
        String accesstoken = null;
        if(zoomCreateVO.getDate()!=null && (!zoomCreateVO.getDate().equals("")) || (zoomCreateVO.getTime()!=null && !zoomCreateVO.getTime().equals(""))) {
            zoomCreateVO.setStart_time(zoomCreateVO.getDate() + "T" + zoomCreateVO.getTime() + ":00");
        }


        if(zoomCookie.length()>0){
            Map<String, String> zoomCookieMap = CommonWebUtil.fetchCookie(zoomCookie);
            String path = "https://zoom.us//oauth/token";
            String newPath = path + "?" + ApplicationConstants.GRANT_TYPE_PARAM + "=refresh_token" + "&"+ ApplicationConstants.REFRESH_TOKEN_PARAM +"=" + zoomCookieMap.get(ApplicationConstants.REFRESH_TOKEN_PARAM);
            String authcode = CommonUtil.generateBasicAuth(ApplicationConstants.ZOOM_CLIENT_ID,ApplicationConstants.ZOOM_CLIENT_SECRET);

            HttpHeaders headers = new HttpHeaders();
            headers.add(ApplicationConstants.REQUEST_AUTHORIZATION,authcode);
            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            HttpEntity httpEntity = new HttpEntity(null,headers);
            ResponseEntity<String> responseEntity =  restTemplate.exchange(newPath, HttpMethod.POST,httpEntity,String.class);
            if(responseEntity.getStatusCode()!=HttpStatus.OK){
                accesstoken = zoomCookieMap.get("access_token");
            }
            System.out.println("Response Entity"+responseEntity);
            ObjectMapper mapper = new ObjectMapper();
            try {
                Object s = mapper.readValue(responseEntity.getBody(),HashMap.class);
                Map<String, Object > responseMap = mapper.convertValue(s, Map.class);
                String zoomcookieString = ApplicationConstants.REFRESH_TOKEN_PARAM + ":" + responseMap.get(ApplicationConstants.REFRESH_TOKEN_PARAM).toString() + "\t"
                        + ApplicationConstants.ACCESS_TOKEN_PARAM + ":" + responseMap.get(ApplicationConstants.ACCESS_TOKEN_PARAM).toString();
                CommonWebUtil.createCookie(ApplicationConstants.COOKIE_ZOOM,zoomcookieString, response);
                accesstoken = responseMap.get(ApplicationConstants.ACCESS_TOKEN_PARAM).toString();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            RedirectView redirectView;
            switch (action){
                case "createMeeting" :  redirectView = new RedirectView("/zoom/createmeeting",true);
                                        redir.addFlashAttribute("zoomCreateVO",zoomCreateVO);
                                        redir.addFlashAttribute("accessToken",accesstoken);
                                        return redirectView;

                case "updateMeeting" :  redirectView = new RedirectView("/zoom/updatemeeting",true);
                                        redir.addFlashAttribute("zoomUpdateVO",zoomCreateVO);
                                        redir.addFlashAttribute("accessToken",accesstoken);
                                        return redirectView;

                case "deleteMeeting" :  redirectView = new RedirectView("/zoom/deletemeeting",true);
                                        redir.addFlashAttribute("zoomDeleteVO",zoomCreateVO);
                                        redir.addFlashAttribute("accessToken",accesstoken);
                                        return redirectView;

                default:break;
            }

        }
//        return "redirect:/faculty/classroom";
        return (new RedirectView("/faculty/classroom",true));
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(3000);

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }


}

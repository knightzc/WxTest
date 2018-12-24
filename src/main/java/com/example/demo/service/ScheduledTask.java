package com.example.demo.service;

import com.example.demo.bean.AccessTokenInfo;
import com.example.demo.bean.CourseTemplete;
import com.example.demo.bean.TempleteInfo;
import com.example.demo.domain.model.WxOpenId;
import com.example.demo.repositories.WxOpenIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTask {
    @Autowired
    private FeignClientService feignClientService;
    private WxOpenIdService wxOpenIdService;
    @Autowired
    private WxOpenIdRepository wxOpenIdRepository;
    @Value("${appconfig.appid}")
    private String appid;
    @Value("${appconfig.secret}")
    private String secret;
    @Value("${appconfig.appno}")
    private String appno;
    @Scheduled(fixedRate = 7000000)
    public void updateAccessToken(){
        AccessTokenInfo accessTokenInfo = feignClientService.getAccessToken("client_credential",appid,secret);
        AccessTokenInfo.accessTokenForUse = accessTokenInfo.getAccess_token();
        System.out.println(AccessTokenInfo.accessTokenForUse);
    }

    @Scheduled(fixedRate = 1000000000)
    public void sendTempleteMessage(){
        if(AccessTokenInfo.accessTokenForUse != null){
            List<WxOpenId> wxOpenIdList = wxOpenIdRepository.findAll();
            for (WxOpenId wxOpenId : wxOpenIdList){
                if(wxOpenId.getBindStatus()){
                    CourseTemplete courseTemplete = new CourseTemplete();
                    courseTemplete.setTouser(wxOpenId.getOpenId());
                    courseTemplete.setTemplate_id("LINwX_aCi7a47_Wom1Q-Cy7uI8_bH3vxyJnFXxQq91I");
                    CourseTemplete.DataBean dataBean = new CourseTemplete.DataBean();
                    dataBean.setTeacher(new CourseTemplete.DataBean.ValueBean("李洋"));
                    dataBean.setClassRoom(new CourseTemplete.DataBean.ValueBean("A102"));
                    dataBean.setCourseCount(new CourseTemplete.DataBean.ValueBean("1"));
                    dataBean.setCourseInfo(new CourseTemplete.DataBean.ValueBean("大学英语"));
                    dataBean.setCourseSection(new CourseTemplete.DataBean.ValueBean("1-2"));
                    dataBean.setRemark(new CourseTemplete.DataBean.ValueBean(""));
                    courseTemplete.setData(dataBean);
                    courseTemplete.setUrl("http://www.baidu.com/");
                    feignClientService.sendTempleteMsg(AccessTokenInfo.accessTokenForUse, courseTemplete);
                }
            }

        }
    }
}

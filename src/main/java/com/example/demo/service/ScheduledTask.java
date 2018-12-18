package com.example.demo.service;

import com.example.demo.bean.AccessTokenInfo;
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
                    TempleteInfo templeteInfo = new TempleteInfo();
                    templeteInfo.setTouser(wxOpenId.getOpenId());
                    templeteInfo.setTemplate_id("trbyB_nu0BBlQGYlRuEYEwtm5aNaFgLgl121sOK965k");
                    TempleteInfo.DataBean dataBean = new TempleteInfo.DataBean();
                    dataBean.setCourse(new TempleteInfo.DataBean.CourseBean("大学英语"));
                    dataBean.setCourseDate(new TempleteInfo.DataBean.CourseDateBean("2018-12-12"));
                    dataBean.setStartTime(new TempleteInfo.DataBean.StartTimeBean("08:00"));
                    dataBean.setEndTime(new TempleteInfo.DataBean.EndTimeBean("10:00"));
                    dataBean.setRemark(new TempleteInfo.DataBean.RemarkBean("测试"));
                    templeteInfo.setData(dataBean);
                    templeteInfo.setUrl("http://www.baidu.com/");
                    feignClientService.sendTempleteMsg(AccessTokenInfo.accessTokenForUse, templeteInfo);
                }
            }

        }
    }
}

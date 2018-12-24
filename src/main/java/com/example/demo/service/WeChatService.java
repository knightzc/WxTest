package com.example.demo.service;

import com.example.demo.bean.AccessTokenInfo;
import com.example.demo.bean.ButtonInfo;
import com.example.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WeChatService {
    @Value("${appconfig.token}")
    private String token;
    @Value("${appconfig.appid}")
    private String appId;
    @Value("${appconfig.encodingAESKey}")
    private String encodingAESKey;

    @Autowired
    private FeignClientService feignClientService;

    public void checkSignature(HttpServletRequest request, HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
//                feignClientService.deleteButton(AccessTokenInfo.accessTokenForUse);
//                feignClientService.createButton(AccessTokenInfo.accessTokenForUse, createbutton());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public void handleMessage(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        System.out.println("encrypt_type=" + request.getParameter("encrypt_type"));
        Map<String,String> map = null;
        if("aes".equals(request.getParameter("encrypt_type"))){
           map  = MessageUtil.xmlToMapWithDecrypt(request,token,encodingAESKey,appId);
        }else {
            map = MessageUtil.xmlToMap(request);
        }
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String message = null;
        System.out.println("===============" + map);
        if (Constants.MSGTYPE_EVENT.equals(MsgType)) {
            if (Constants.MSGTYPE_EVENT_SUBSCRIBE.equals(map.get("Event"))) {
                if("aes".equals(request.getParameter("encrypt_type"))){
                    PicTextMessageUtil picTextMessageUtil = new PicTextMessageUtil();
                    message = MessageUtil.encryptMessage(request,token,encodingAESKey,appId,picTextMessageUtil.initMessage(FromUserName, ToUserName));
                }else {
                    PicTextMessageUtil picTextMessageUtil = new PicTextMessageUtil();
                    message = picTextMessageUtil.initMessage(FromUserName, ToUserName);
                }

            }
        }else if(Constants.MSGTYPE_TEXT.equals(MsgType)){
            TextMessageUtil textMessageUtil = new TextMessageUtil();
            if("aes".equals(request.getParameter("encrypt_type"))){
                message = MessageUtil.encryptMessage(request,token,encodingAESKey,appId,textMessageUtil.initMessage(FromUserName,ToUserName));
            }else {
                message = textMessageUtil.initMessage(FromUserName,ToUserName);
            }

        }
        if(null != message){
            try {
                out = response.getWriter();
                out.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
        }

    }


    private ButtonInfo createbutton() {
        ButtonInfo buttonInfo = new ButtonInfo();
        List<ButtonInfo.ButtonBean> buttonBeans = new ArrayList<>();
        ButtonInfo.ButtonBean buttonBean = new ButtonInfo.ButtonBean();
        buttonBean.setName("测试");
        buttonBean.setKey("1");
        buttonBean.setType("miniprogram");
        buttonBean.setAppid("wx989f803b8975810f");
        buttonBean.setUrl("http://mp.weixin.qq.com");
        buttonBean.setPagepath("home/home");
        buttonBeans.add(buttonBean);
        ButtonInfo.ButtonBean buttonBean2 = new ButtonInfo.ButtonBean();
        buttonBean2.setName("绑定");
        buttonBean2.setKey("2");
        buttonBean2.setType("click");
        buttonBeans.add(buttonBean2);
        buttonInfo.setButton(buttonBeans);
        return buttonInfo;
    }
}

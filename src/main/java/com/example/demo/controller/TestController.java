package com.example.demo.controller;

import com.example.demo.bean.AccessTokenInfo;
import com.example.demo.bean.ButtonInfo;
import com.example.demo.bean.TempleteInfo;
import com.example.demo.bean.TempleteMsgResponse;
import com.example.demo.service.FeignClientService;
import com.example.demo.utils.CheckUtil;
import com.example.demo.utils.MessageUtil;
import com.example.demo.utils.PicTextMessageUtil;
import com.example.demo.utils.TextMessageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/12/8.
 */
@RestController
public class TestController {
    @Autowired
    FeignClientService feignClientService;

    @Value("${appconfig.appid}")
    String appid;

    @Value("${appconfig.secret}")
    String secret;

    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
                feignClientService.deleteButton(AccessTokenInfo.accessTokenForUse);
                feignClientService.createButton(AccessTokenInfo.accessTokenForUse, createbutton());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }

    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public void dopost(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String message = null;
        System.out.println("===============" + map);
        //处理文本类型，实现输入1，回复相应的封装的内容
        if ("text".equals(map.get("MsgType"))) {
            if ("1".equals(map.get("Content"))) {
//                TextMessageUtil textMessage = new TextMessageUtil();
//                message = textMessage.initMessage(FromUserName, ToUserName);
                TempleteInfo templeteInfo = new TempleteInfo();
                templeteInfo.setTouser(FromUserName);
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
        } else if ("event".equals(MsgType)) {
            if ("CLICK".equals(map.get("Event"))) {
                PicTextMessageUtil picTextMessageUtil = new PicTextMessageUtil();
                message = picTextMessageUtil.initMessage(FromUserName, ToUserName);
//                TextMessageUtil textMessage = new TextMessageUtil();
//                message = textMessage.initMessage(FromUserName, ToUserName);
                try {
                    out = response.getWriter();
                    out.write(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.close();
            }
        }
    }

    private ButtonInfo createbutton() {
        ButtonInfo buttonInfo = new ButtonInfo();
        List<ButtonInfo.ButtonBean> buttonBeans = new ArrayList<>();
        ButtonInfo.ButtonBean buttonBean = new ButtonInfo.ButtonBean();
        buttonBean.setName("测试");
        buttonBean.setKey("1");
        buttonBean.setType("view");
        buttonBean.setUrl("http://cqbciu.natappfree.cc/weixin/bindweixin?openId=1");
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
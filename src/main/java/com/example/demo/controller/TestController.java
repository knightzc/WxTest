package com.example.demo.controller;

import com.example.demo.bean.AccessTokenInfo;
import com.example.demo.bean.ButtonInfo;
import com.example.demo.bean.TempleteInfo;
import com.example.demo.bean.TempleteMsgResponse;
import com.example.demo.service.FeignClientService;
import com.example.demo.service.WeChatService;
import com.example.demo.utils.*;
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
    private WeChatService weChatService;

    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        weChatService.checkSignature(request,response);
    }

    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public void dopost(HttpServletRequest request, HttpServletResponse response) {
        weChatService.handleMessage(request,response);
    }


}
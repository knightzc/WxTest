package com.example.demo.controller;

import com.example.demo.service.WxOpenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
@RequestMapping(value = "/bindweixin")
public class LoginController {
    @Autowired
    private WxOpenIdService wxOpenIdService;

    @RequestMapping()
    public ModelAndView getLoginPage(@RequestParam("openId") String openId) {
        ModelAndView modelAndView = new ModelAndView("bindweixin");
        modelAndView.addObject("openId", openId);
        return modelAndView;
    }

    @PostMapping(value = "/getBindStatus")
    @ResponseBody
    public Map<String,Object> getBindStatus(String openId){
        return wxOpenIdService.getBindStatus(openId);
    }

    @PostMapping(value = "/bindAccount")
    @ResponseBody
    public Map<String,Object> bindAccount(String openId,String userAccount,String password){
        return wxOpenIdService.bindAccount(openId, userAccount, password);
    }



}

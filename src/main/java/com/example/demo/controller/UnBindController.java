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
@RequestMapping(value = "/unBind")
public class UnBindController {
    @Autowired
    private WxOpenIdService wxOpenIdService;
    @RequestMapping()
    public ModelAndView getUnBindPage(@RequestParam("openId") String openId) {
        ModelAndView modelAndView = new ModelAndView("bindsuccess");
        modelAndView.addObject("openId", openId);
        return modelAndView;
    }

    @PostMapping(value = "/unBindAccount")
    @ResponseBody
    public Map<String,Object> unBind(String openId){
        return wxOpenIdService.unBindAccount(openId);
    }
}

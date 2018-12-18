package com.example.demo.service;

import com.example.demo.domain.model.User;
import com.example.demo.domain.model.WxOpenId;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WxOpenIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WxOpenIdService {
    @Autowired
    private WxOpenIdRepository wxOpenIdRepository;
    @Autowired
    private UserRepository userRepository;

    public Map<String,Object> getBindStatus(String openId){
        WxOpenId wxOpenId =  wxOpenIdRepository.findByOpenId(openId);
        Map<String,Object> result = new HashMap<>();
        if(wxOpenId != null && wxOpenId.getBindStatus()){
            result.put("status",1);
        }else {
            result.put("status",0);
        }
        return result;
    }

    public Map<String,Object> unBindAccount(String openId){
        WxOpenId wxOpenId = wxOpenIdRepository.findByOpenId(openId);
        Map<String,Object> result = new HashMap<>();
        if(openId != null){
            wxOpenId.setBindStatus(false);
            wxOpenIdRepository.save(wxOpenId);
            result.put("status",1);
        }else {
            result.put("status",0);
        }
        return result;

    }

    public Map<String,Object> bindAccount(String openId,String account,String password){
        Map<String,Object> result = new HashMap<>();
        User user = userRepository.findByUserNo(account);
        if(user == null){
            result.put("status",0);
            result.put("msg","账号不存在");
            return result;
        }
        if(!user.getPassword().equals(password)){
            result.put("status",2);
            result.put("msg","密码有误");
            return result;
        }
        WxOpenId bindWxOpenId = wxOpenIdRepository.findByUser(user);
        if(bindWxOpenId != null && bindWxOpenId.getBindStatus()){
                result.put("status",3);
                result.put("msg","该账号已被绑定");
                return result;
        }
        WxOpenId wxOpenId = wxOpenIdRepository.findByOpenId(openId);
        if(wxOpenId != null && wxOpenId.getBindStatus()){
            result.put("status",4);
            result.put("msg","该openId已被绑定");
            return result;
        }else if(wxOpenId == null){
            wxOpenId = new WxOpenId();
        }
        wxOpenId.setBindStatus(true);
        wxOpenId.setOpenId(openId);
        wxOpenId.setUser(user);
        wxOpenIdRepository.save(wxOpenId);
        result.put("status",1);
        result.put("msg","绑定成功");
        return result;
    }

}

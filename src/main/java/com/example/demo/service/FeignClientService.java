package com.example.demo.service;

import com.example.demo.FeignConfiguration;
import com.example.demo.bean.AccessTokenInfo;
import com.example.demo.bean.ButtonInfo;
import com.example.demo.bean.TempleteInfo;
import com.example.demo.bean.TempleteMsgResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://api.weixin.qq.com", name = "feign",configuration = FeignConfiguration.class)
public interface FeignClientService {
    @RequestMapping(value = "/cgi-bin/token",method = RequestMethod.GET)
    AccessTokenInfo getAccessToken(@RequestParam("grant_type") String grant_type,
                                   @RequestParam("appid") String appId,
                                   @RequestParam("secret") String secret);

    @RequestMapping(value = "/cgi-bin/message/template/send",method = RequestMethod.POST)
    TempleteMsgResponse sendTempleteMsg(@RequestParam("access_token") String access_token,
                                        @RequestBody TempleteInfo templeteInfo);

    @RequestMapping(value = "/cgi-bin/menu/create",method = RequestMethod.POST,consumes = "application/json")
    TempleteMsgResponse createButton(@RequestParam("access_token") String access_token, @RequestBody ButtonInfo buttonInfo);

    @RequestMapping(value = "/cgi-bin/menu/delete",method = RequestMethod.GET)
    TempleteMsgResponse deleteButton(@RequestParam("access_token") String access_token);
}

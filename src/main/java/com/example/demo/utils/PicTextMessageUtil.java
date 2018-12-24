package com.example.demo.utils;

import com.example.demo.bean.MessagePic;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PicTextMessageUtil implements BaseMessageUtil<MessagePic> {
    @Override
    public String messageToxml(MessagePic messagePic) {
        XStream xstream  = new XStream();
        xstream.alias("xml", MessagePic.class);
        xstream.alias("item", MessagePic.Item.class);
        System.out.println(xstream.toXML(messagePic));
        return xstream.toXML(messagePic);
    }

    @Override
    public String initMessage(String FromUserName, String ToUserName) {
        MessagePic messagePic = new MessagePic();
        messagePic.setArticleCount(1);
        MessagePic.Item articalInfo = new MessagePic.Item();
        articalInfo.setDescription("账号绑定");
        articalInfo.setTitle("ssd");
        articalInfo.setPicUrl("http://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@2o.jpg");
        articalInfo.setUrl("http://fyn7ng.natappfree.cc/weixin/bindweixin?openId=" + FromUserName);
        List<MessagePic.Item> list = new ArrayList<>();
        list.add(articalInfo);
        messagePic.setArticles(list);
        messagePic.setToUserName(FromUserName);
        messagePic.setFromUserName(ToUserName);
        messagePic.setCreateTime(new Date().getTime());
        messagePic.setMsgType(Constants.MSGTYPE_NEWS);
        return messageToxml(messagePic);
    }
}

package com.example.demo.bean;

public class MessageText extends BaseMessage {
    private String Content;
    private String MsgId;


    public MessageText() {
    }

    public MessageText(String toUserName, String fromUserName,
                       long createTime, String msgType, String content, String msgId) {
        super(toUserName,fromUserName,createTime,msgType);
        MsgType = msgType;
        Content = content;
        MsgId = msgId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}

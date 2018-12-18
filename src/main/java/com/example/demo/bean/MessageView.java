package com.example.demo.bean;

public class MessageView extends BaseMessage {
    private String Event;
    private String EventKey;
    private String MenuId;


    public MessageView(String toUserName, String fromUserName,
                       long createTime, String msgType,String event, String eventKey, String menuId) {
        super(toUserName,fromUserName,createTime,msgType);
        Event = event;
        EventKey = eventKey;
        MenuId = menuId;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}

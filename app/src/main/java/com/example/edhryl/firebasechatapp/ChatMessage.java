package com.example.edhryl.firebasechatapp;

import java.util.Date;


public class ChatMessage {

    private String uId;
    private String messageText;
    private String messageUser;
    private long messageTime;

    public ChatMessage(String messageText,String messageUser,String uId){
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.uId = uId;
        messageTime = new Date().getTime();
    }
    public ChatMessage(){

    }

    public String getuId() {
        return uId;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}

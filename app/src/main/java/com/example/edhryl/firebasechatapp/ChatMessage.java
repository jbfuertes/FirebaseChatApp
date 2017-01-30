package com.example.edhryl.firebasechatapp;

import java.util.Date;


public class ChatMessage {

    private String uId;
    private String messageUserText;
    private String messageUser;
    //private long messageTime;
    private String messageUserTime;

    /*public ChatMessage(String messageUserText,String messageUser,String uId){
        this.messageUserText = messageUserText;
        this.messageUser = messageUser;
        this.uId = uId;
        messageTime = new Date().getTime();
    }*/
    public ChatMessage(String messageUserText, String messageUser, String messageUserTime){
        this.messageUserText = messageUser;
        this.messageUser = messageUser;
        this.messageUserTime = messageUserTime;
    }
    public ChatMessage(){

    }

    public String getMessageUserText() {
        return messageUserText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public String getMessageUserTime() {
        return messageUserTime;
    }

    public void setMessageUserText(String messageUserText) {
        this.messageUserText = messageUserText;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public void setMessageUserTime(String messageUserTime) {
        this.messageUserTime = messageUserTime;
    }
}

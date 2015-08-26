package com.example.yzha502.movienight.entities;

/**
 * Created by yzha502 on 6/06/15.
 */
public class Message {

    private Integer messageId;
    private String senderId;
    private String conten;
    private String resiverId;

    public Message() {
    }

    public Message(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public String getResiverId() {
        return resiverId;
    }

    public void setResiverId(String resiverId) {
        this.resiverId = resiverId;
    }


}

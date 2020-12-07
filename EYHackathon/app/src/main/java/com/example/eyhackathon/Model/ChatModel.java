package com.example.eyhackathon.Model;

public class ChatModel {

    public String senderID;
    public String receiverID;
    public String message;

    ChatModel(){}
    public ChatModel(String senderID, String receiverID, String message){
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.message = message;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

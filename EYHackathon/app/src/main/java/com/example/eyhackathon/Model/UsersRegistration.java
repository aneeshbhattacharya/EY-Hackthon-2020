package com.example.eyhackathon.Model;

public class UsersRegistration {

    public String name,email,phoneNumber,password,userID;
    public int points;
    public boolean buddyStatus;
    public String buddyID;
    public String buddyChatID;
    public boolean doneTask1;
    public boolean doneTask2;


    public UsersRegistration(String name, String email, String phoneNumber,String password,String userID) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        buddyStatus = false;
        points = 0;
        this.userID=userID;
        buddyID="";
        buddyChatID="";
        doneTask1=false;
        doneTask2=false;
    }
    public UsersRegistration(){}

    public String getBuddyID() {
        return buddyID;
    }

    public void setBuddyID(String buddyID) {
        this.buddyID = buddyID;
    }

    public String getName() {
        return name;
    }

    public boolean isDoneTask1() {
        return doneTask1;
    }

    public void setDoneTask1(boolean doneTask1) {
        this.doneTask1 = doneTask1;
    }

    public boolean isDoneTask2() {
        return doneTask2;
    }

    public void setDoneTask2(boolean doneTask2) {
        this.doneTask2 = doneTask2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isBuddyStatus() {
        return buddyStatus;
    }

    public void setBuddyStatus(boolean buddyStatus) {
        this.buddyStatus = buddyStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

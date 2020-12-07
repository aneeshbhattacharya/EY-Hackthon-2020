package com.example.eyhackathon.Model;

import java.util.LinkedList;

public class CoursesModel {

    public String courseCodes;
    public String userID;

    public CoursesModel(String courseCodes, String userID) {
        this.courseCodes = courseCodes;
        this.userID = userID;
    }

    public String getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(String courseCodes) {
        this.courseCodes = courseCodes;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

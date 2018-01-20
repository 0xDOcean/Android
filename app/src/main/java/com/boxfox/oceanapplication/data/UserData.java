package com.boxfox.oceanapplication.data;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by boxfox on 2018-01-20.
 */

public class UserData extends RealmObject {
    public static String PARAM_NAME = "token";

    private String accessToken;
    private String name;
    private String profileImageUrl;
    private int grade, clazz, number;
    private String school;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static String getDefaultAccessToken() {
        return getDefault().getAccessToken();
    }

    public static UserData getDefault() {
        return Realm.getDefaultInstance().where(UserData.class).findFirst();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static UserData getDefaultUser() {
        return Realm.getDefaultInstance().where(UserData.class).findFirst();
    }
}
package com.boxfox.oceanapplication.login.data;

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

    public static UserData getDefaultUser() {
        return Realm.getDefaultInstance().where(UserData.class).findFirst();
    }
}
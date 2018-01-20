package com.boxfox.oceanapplication.login.facebook;

import android.content.Context;
import android.util.Log;

import com.boxfox.oceanapplication.data.UserData;
import com.boxfox.oceanapplication.login.JobResultCallback;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;

import io.realm.Realm;

/**
 * Created by boxfox on 2018-01-20.
 */

public class FacebookCallbackImpl implements FacebookCallback<LoginResult> {
    private Context context;
    private JobResultCallback<UserData> callback;


    public FacebookCallbackImpl(Context ctx, JobResultCallback<UserData> callback){
        this.context = ctx;
        this.callback = callback;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.e("DEBUG", "success" + loginResult.getAccessToken().getToken());
        Profile profile = Profile.getCurrentProfile();
        String name = profile.getName();
        String profileUrl = profile.getProfilePictureUri(300, 300).toString();
        String token = loginResult.getAccessToken().getToken();
        UserData userData = new UserData();
        userData.setName(name);
        userData.setProfileImageUrl(profileUrl);
        userData.setAccessToken(token);
        callback.end(true, userData);
    }

    @Override
    public void onCancel() {
        Log.e("DEBUG", "취소하셨습니다.");
        callback.end(false, null);
    }

    @Override
    public void onError(FacebookException error) {
        Log.e("DEBUG", error.toString());
        callback.end(false, null);
    }
}

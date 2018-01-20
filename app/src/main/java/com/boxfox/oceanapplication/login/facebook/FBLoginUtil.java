package com.boxfox.oceanapplication.login.facebook;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.boxfox.oceanapplication.login.data.UserData;
import com.boxfox.oceanapplication.login.JobResultCallback;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by boxfox on 2018-01-20.
 */

public class FBLoginUtil {

    public static void initButton(Context context, com.facebook.login.widget.LoginButton login_facebook, JobResultCallback<UserData> callback) {
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(context);
        CallbackManager callbackManager = CallbackManager.Factory.create();
        login_facebook.setReadPermissions("public_profile", "user_friends");
        login_facebook.registerCallback(callbackManager, new FacebookCallbackImpl(context, callback));
    }

    public static void print_facebook_keyhash(Context context) {
        try {
            Log.d("test", context.getPackageName());
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("boxfox", "facebook_keyhash : " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}

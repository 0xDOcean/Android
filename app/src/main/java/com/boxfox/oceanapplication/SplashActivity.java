package com.boxfox.oceanapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.boxfox.oceanapplication.login.data.UserData;
import com.boxfox.oceanapplication.login.facebook.FBLoginUtil;
import com.buffaloes.oceanapplication.R;
import com.facebook.login.widget.LoginButton;

import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {
    private LoginButton login_facebook;
    private Button login_facebook_fake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        login_facebook_fake = findViewById(R.id.btn_splash_login_facebook_fake);
        login_facebook = findViewById(R.id.btn_splash_login_facebook);
        checkUserData();
        initLoginButton();
    }

    private void checkUserData() {
        Realm.init(this);
        if (UserData.getDefaultUser() != null) {
            //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void initLoginButton() {
        FBLoginUtil.print_facebook_keyhash(this);
        login_facebook_fake.setOnClickListener((v) -> login_facebook.performClick());
        FBLoginUtil.initButton(this, login_facebook, (result, data) -> {

        });
    }

}
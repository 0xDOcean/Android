package com.boxfox.oceanapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.buffaloes.oceanapplication.R;
import com.squareup.picasso.Picasso;

public class FishInfoActivity extends AppCompatActivity {
    private TextView tv_title, tv_desc;
    private ImageView iv_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fish_info);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_description);
        iv_background = findViewById(R.id.iv_fish_background);

        tv_title.setText(getIntent().getStringExtra("title"));
        tv_desc.setText(getIntent().getStringExtra("desc"));

        String url = getIntent().getStringExtra("image");

        Picasso.with(this).load(url).fit().into(iv_background);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            case KeyEvent.KEYCODE_VOLUME_UP:
                finish();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

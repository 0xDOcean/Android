package com.boxfox.oceanapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.boxfox.oceanapplication.data.FishInfo;
import com.buffaloes.oceanapplication.R;
import com.bumptech.glide.Glide;

import io.realm.Realm;
import io.realm.RealmResults;

public class FishListActivity extends AppCompatActivity {
    private ScrollView scrollView_main;
    private LinearLayout layout_recent;

    private FloatingActionButton btn_open_camera;
    private View searchBarBack, searchBarBackFirst;
    private boolean visible;
    private boolean init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        layout_recent = findViewById(R.id.layout_recent);
        btn_open_camera = findViewById(R.id.btn_open_camera);

        searchBarBack = findViewById(R.id.layout_searchbar_background);
        searchBarBackFirst = findViewById(R.id.layout_searchbar_background_first);

        searchBarBack.animate().alpha(0.0f);
        searchBarBack.setVisibility(View.VISIBLE);

        scrollView_main = findViewById(R.id.scrollview_main);
        scrollView_main.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView_main.getScrollY();
                if (scrollY > 0 && !visible) {
                    visible = true;
                    searchBarBackFirst.animate().alpha(0.0f).setDuration(300);
                    searchBarBack.animate().alpha(1.0f).setDuration(300);
                } else if (scrollY <= 0 && visible) {
                    visible = false;
                    searchBarBackFirst.animate().alpha(1.0f).setDuration(300);
                    searchBarBack.animate().alpha(0.0f).setDuration(300);
                }
            }
        });
        loadItems();
    }

    private void loadItems() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<FishInfo> results = realm.where(FishInfo.class).findAll();
        for (FishInfo info : results) {
            addItem(info);
        }
    }

    private void addItem(FishInfo info) {
        View view = getLayoutInflater().inflate(R.layout.view_fish_card, null);
        ((TextView) view.findViewById(R.id.tv_fish_name)).setText(info.getName());
        ((TextView) view.findViewById(R.id.tv_date)).setText(info.getDate());
        ((TextView) view.findViewById(R.id.tv_location)).setText(info.getLocation());
        Glide.with(this).load(info.getImageUrl()).into((ImageView) findViewById(R.id.iv_fish));
        if (!init) {
            layout_recent.addView(view, 1);
            init = true;
        } else {
            layout_recent.addView(view);
        }
    }
}

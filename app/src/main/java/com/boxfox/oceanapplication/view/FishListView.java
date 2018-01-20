package com.boxfox.oceanapplication.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.boxfox.oceanapplication.data.FishInfo;
import com.buffaloes.oceanapplication.R;
import com.bumptech.glide.Glide;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by boxfox on 2018-01-20.
 */

public class FishListView extends LinearLayout {
    private ScrollView scrollView_main;
    private LinearLayout layout_recent;

    private FloatingActionButton btn_open_camera;
    private View searchBarBack, searchBarBackFirst;
    private boolean visible;
    private boolean init;

    public FishListView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.view_fish_list, this, false);
        addView(view);

        layout_recent = view.findViewById(R.id.layout_recent);
        btn_open_camera = view.findViewById(R.id.btn_open_camera);

        searchBarBack = view.findViewById(R.id.layout_searchbar_background);
        searchBarBackFirst = view.findViewById(R.id.layout_searchbar_background_first);

        searchBarBack.animate().alpha(0.0f);
        searchBarBack.setVisibility(View.VISIBLE);

        scrollView_main = view.findViewById(R.id.scrollview_main);
        scrollView_main.getViewTreeObserver().addOnScrollChangedListener(() -> {
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
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.view_fish_card, null);
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
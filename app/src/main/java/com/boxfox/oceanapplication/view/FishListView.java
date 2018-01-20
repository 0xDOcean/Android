package com.boxfox.oceanapplication.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.boxfox.oceanapplication.CameraActivity;
import com.boxfox.oceanapplication.data.FishInfo;
import com.buffaloes.oceanapplication.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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

    public FishListView(Context context, View searchBarBack) {
        super(context);
        this.searchBarBack = searchBarBack;
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.view_fish_list, this, false);
        layout_recent = view.findViewById(R.id.layout_recent);
        btn_open_camera = view.findViewById(R.id.btn_open_camera);

        btn_open_camera.setOnClickListener(v -> {
            getContext().startActivity(new Intent(getContext(), CameraActivity.class));
        });

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
        addView(view);
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

        final int radius = 15;
        final int margin = 5;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        Picasso.with(getContext()).load(info.getImageUrl()).fit().transform(transformation).into((ImageView) view.findViewById(R.id.iv_fish));
        if (!init) {
            layout_recent.addView(view, 1);
            init = true;
        } else {
            layout_recent.addView(view);
        }
    }
}
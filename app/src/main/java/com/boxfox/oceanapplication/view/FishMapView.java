package com.boxfox.oceanapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.buffaloes.oceanapplication.R;

/**
 * Created by boxfox on 2018-01-20.
 */

public class FishMapView extends LinearLayout {


    public FishMapView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View view = li.inflate(R.layout.view_fish_map, this, false);



        addView(view);
    }
}

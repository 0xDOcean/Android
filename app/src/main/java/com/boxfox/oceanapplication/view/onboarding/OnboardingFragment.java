package com.boxfox.oceanapplication.view.onboarding;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.buffaloes.oceanapplication.R;

/**
 * Created by boxfox on 2018-01-21.
 */

public class OnboardingFragment extends Fragment {
    private String title, description;
    private int drawable;

    public static OnboardingFragment create(String title, String description, int drawable) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putInt("drawable", drawable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.title = getArguments().getString("title");
        this.description = getArguments().getString("description");
        this.drawable = getArguments().getInt("drawable");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_onboarder, container, false);
        ((TextView) rootView.findViewById(R.id.tv_onboarder_title)).setText(title);
        ((TextView) rootView.findViewById(R.id.tv_onboarder_description)).setText(description);
        ((ImageView) rootView.findViewById(R.id.iv_onboarder_image)).setImageDrawable(getResources().getDrawable(drawable));
        return rootView;
    }

}

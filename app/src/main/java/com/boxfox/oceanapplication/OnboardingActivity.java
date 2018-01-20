package com.boxfox.oceanapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boxfox.oceanapplication.view.onboarding.OnboardingFragment;
import com.buffaloes.oceanapplication.R;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {
    private LinearLayout layout_points;
    private View btn_next, btn_prev;

    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;

    private static final int PAGE_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        layout_points = findViewById(R.id.layout_points);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);

        btn_next.setOnClickListener(v -> {
            if (mPager.getCurrentItem() < mPagerAdapter.getCount()-1) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            } else {
                finish();
            }
        });

        btn_prev.setOnClickListener(v -> {
            if (mPager.getCurrentItem() > 0) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });

        initPages();
    }

    private void initPages() {
        mPagerAdapter.addPage("테스트", "테스트");
        mPagerAdapter.addPage("테스트", "테스트");
        mPagerAdapter.addPage("테스트", "테스트");
        mPagerAdapter.addPage("테스트", "테스트");
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < layout_points.getChildCount(); i++)
                    layout_points.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.background_onboarding_point_default));
                layout_points.getChildAt(position).setBackground(getResources().getDrawable(R.drawable.background_onboarding_point_activate));

                if (position== mPagerAdapter.getCount()-1) {
                    ((TextView) btn_next).setText("끝내기");
                } else {
                    ((TextView) btn_next).setText("다음");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initPoints();
    }

    private void initPoints() {
        for (int i = 0; i < PAGE_COUNT; i++) {
            layout_points.addView(getLayoutInflater().inflate(R.layout.view_onboarding_point, null));
        }
        layout_points.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.background_onboarding_point_activate));
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> pageList;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            pageList = new ArrayList<>();
        }

        public void addPage(String title, String desc) {
            pageList.add(OnboardingFragment.create(title, desc));
            this.notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {
            return pageList.get(position);
        }

        @Override
        public int getCount() {
            return pageList.size();
        }
    }


}

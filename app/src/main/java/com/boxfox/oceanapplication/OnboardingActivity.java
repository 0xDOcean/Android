package com.boxfox.oceanapplication;

import android.content.Intent;
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

    private static final int PAGE_COUNT = 3;

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
            if (mPager.getCurrentItem() < mPagerAdapter.getCount() - 1) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(OnboardingActivity.this, FishListActivity.class));
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
        mPagerAdapter.addPage("너는 이름이 뭐니?", "오르블루는 스킨 다이빙시\n모르는 물고기를 딥러닝 영상인식을 통해\n어떤 물고기인지 알려줍니다.", R.drawable.onboarding_1);
        mPagerAdapter.addPage(" 마! 적시라!", "요즘 스마트폰들은 전체 방수가 기본입니다.\n이 방수 기능을 이용해서, 바다 친구들을\n스캔하세요!", R.drawable.onboarding_2);
        mPagerAdapter.addPage("아.. 다음 휴가 언제와...", "오르블루 앱을 통해 수집한\n바다 친구들과의 추억을 만드세요\n오르블루에 저장하세요", R.drawable.onboarding_3);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < layout_points.getChildCount(); i++)
                    ((LinearLayout)layout_points.getChildAt(i)).getChildAt(0).setBackground(getResources().getDrawable(R.drawable.background_onboarding_point_default));
                ((LinearLayout)layout_points.getChildAt(position)).getChildAt(0).setBackground(getResources().getDrawable(R.drawable.background_onboarding_point_activate));

                if (position == mPagerAdapter.getCount() - 1) {
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
        ((LinearLayout)layout_points.getChildAt(0)).getChildAt(0).setBackground(getResources().getDrawable(R.drawable.background_onboarding_point_activate));
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

        public void addPage(String title, String desc, int drawable) {
            pageList.add(OnboardingFragment.create(title, desc, drawable));
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

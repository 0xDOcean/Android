package com.boxfox.oceanapplication;

import android.os.Bundle;

import com.buffaloes.oceanapplication.R;
import com.chyrta.onboarder.OnboarderActivity;
import com.chyrta.onboarder.OnboarderPage;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends OnboarderActivity {

    private List<OnboarderPage> onboarderPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onboarderPages = new ArrayList<OnboarderPage>();

        // Create your first page
        OnboarderPage onboarderPage1 = new OnboarderPage("Title 1", "Description 1");
        onboarderPage1.setTitleColor(R.color.white);
        onboarderPage1.setDescriptionColor(R.color.colorSubText);
        onboarderPage1.setBackgroundColor(R.color.colorPrimaryBlue);


        OnboarderPage onboarderPage2 = new OnboarderPage("test2", " asdas ads", R.drawable.ic_launcher_foreground);
        onboarderPage2.setTitleColor(R.color.white);
        onboarderPage2.setDescriptionColor(R.color.colorSubText);
        onboarderPage2.setBackgroundColor(R.color.colorPrimaryBlueDark);

        onboarderPages.add(onboarderPage1);
        onboarderPages.add(onboarderPage2);
        setSkipButtonTitle("Skip");
        setFinishButtonTitle("Finish");

        setOnboardPagesReady(onboarderPages);

    }

    @Override
    public void onSkipButtonPressed() {
        // Optional: by default it skips onboarder to the end
        super.onSkipButtonPressed();
        // Define your actions when the user press 'Skip' button
    }

    @Override
    public void onFinishButtonPressed() {
        // Define your actions when the user press 'Finish' button
    }

}

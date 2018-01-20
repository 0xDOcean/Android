package com.boxfox.oceanapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.boxfox.oceanapplication.data.FishInfo;
import com.boxfox.oceanapplication.view.FishListView;
import com.boxfox.oceanapplication.view.FishMapView;
import com.buffaloes.oceanapplication.R;

import io.realm.Realm;

public class FishListActivity extends AppCompatActivity {
    private RelativeLayout layout_root;
    private View searchBarBack;
    private ImageView btn_toggle;

    private boolean map = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        searchBarBack = findViewById(R.id.layout_searchbar_background);

        layout_root = findViewById(R.id.layout_root);
        btn_toggle = findViewById(R.id.btn_toggle);
        btn_toggle.setOnClickListener(v -> toggle());
        toggle();
    }

  /*  private void createRandomImage(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for(int i = 0 ; i < 10 ; i++){
            FishInfo fishInfo = realm.createObject(FishInfo.class);
            fishInfo.setName("민둥산");
            fishInfo.setDate("어제 잡은 물고기");
            fishInfo.setImageUrl("http://www.mcst.go.kr/attachFiles/cultureInfoCourt/localFestival/notifyFestival/1503280262897.jpg");
        }
        realm.commitTransaction();
    }*/

    private void toggle() {
        if (map) {
            layout_root.removeAllViews();
            layout_root.addView(new FishListView(this, searchBarBack));
            searchBarBack.animate().alpha(0.0f);
            searchBarBack.setVisibility(View.VISIBLE);
            btn_toggle.setImageDrawable(getResources().getDrawable(R.drawable.ic_map));
            map = false;
        } else {
            layout_root.removeAllViews();
            layout_root.addView(new FishMapView(this));
            searchBarBack.animate().alpha(1.0f);
            btn_toggle.setImageDrawable(getResources().getDrawable(R.drawable.view_list));
            map = true;
        }
    }
}

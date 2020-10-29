package ir.mahoorsoft.app.stationsfanclub.view.activity_station_feature;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.view.activity_station_feature.fragment_comment.FragmentComment;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterViewPager;


public class ActivityStationFeature extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView txtAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        G.context = this;
        G.activity = this;
        setContentView(R.layout.activity_station_feature);
        init();
    }

    private void init() {
        pointers();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtAction.setText(R.string.app_name);
        getSupportActionBar().setTitle("");
        tabLayout.setupWithViewPager(viewPager);
        settingUpViewPager();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void pointers() {
        txtAction = (TextView) findViewById(R.id.txtActionBarStation);
        toolbar = (Toolbar) findViewById(R.id.tlbStationFeature);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutStationFeature);
        viewPager = (ViewPager) findViewById(R.id.VPStationFeature);

    }

    private void settingUpViewPager(){

        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager());
        adapterViewPager.add(new FragmentComment(), "نظرات و امتیازات");
        adapterViewPager.add(new FragmentStationFeature(), "مشخصات جایگاه");
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(1);
        viewPager.setOffscreenPageLimit(5);
    }

}

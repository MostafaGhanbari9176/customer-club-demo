package ir.mahoorsoft.app.stationsfanclub.view.activity_station_feature;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.view.activity_station_feature.fragment_comment.FragmentComment;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterViewPager;


public class ActivityStationFeature extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
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

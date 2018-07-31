package ir.mahoorsoft.app.stationsfanclub.view.avtivity_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.Pref;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.PrefKey;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.home.FragmentHome;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.profile.FragmentPelakValidation;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.profile.FragmentProfile;

public class MainActivity extends AppCompatActivity {

    public RelativeLayout pBarMain;
    Toolbar toolbar;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        G.context = this;
        G.activity = this;
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        pointers();
        setSupportActionBar(toolbar);
        setUpNavigationItemListener();
        G.disableShiftModeNavigation(navigationView);
        replaceView(new FragmentHome());
    }

    private void pointers() {
        toolbar = (Toolbar) findViewById(R.id.tlbMain);
        navigationView = (BottomNavigationView) findViewById(R.id.BNVMain);
        pBarMain = (RelativeLayout) findViewById(R.id.RLPBarMain);

    }

    private void setUpNavigationItemListener() {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuHome:
                        replaceView(new FragmentHome());
                        getSupportActionBar().show();
                        getSupportActionBar().setTitle("خانه");
                        return true;
                    case R.id.menuMap:
                        replaceView(new FragmentMap());
                        getSupportActionBar().hide();
                        return true;
                    case R.id.menuLottery:
                        replaceView(new FragmentLottery());
                        getSupportActionBar().show();
                        getSupportActionBar().setTitle("قرعه کشی ها");
                        return true;
                    case R.id.menuProfile:
                        if (Pref.getBollValue(PrefKey.isLogin, false))
                            replaceView(new FragmentProfile());
                        else
                            replaceView(new FragmentPelakValidation());
                        getSupportActionBar().hide();
                        return true;
                }
                return false;
            }
        });
    }

    public void replaceView(Fragment fragment) {
        G.activity.getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).commit();
    }
}

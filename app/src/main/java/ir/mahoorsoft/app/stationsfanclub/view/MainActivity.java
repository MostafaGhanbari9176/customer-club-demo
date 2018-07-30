package ir.mahoorsoft.app.stationsfanclub.view;

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
import ir.mahoorsoft.app.stationsfanclub.view.matboat.FragmentMatboat;
import ir.mahoorsoft.app.stationsfanclub.view.profile.FragmentGetCustomerData;
import ir.mahoorsoft.app.stationsfanclub.view.profile.FragmentPelakValidation;
import ir.mahoorsoft.app.stationsfanclub.view.profile.FragmentProfile;

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
                    case R.id.menuProfile:
                        replaceView(new FragmentPelakValidation());
                        getSupportActionBar().setTitle("پروفایل");
                        return true;
                    case R.id.menuProfile2:
                        replaceView(new FragmentGetCustomerData());
                        getSupportActionBar().setTitle("پروفایل2");
                        return true;
                    case R.id.menuProfile3:
                        replaceView(new FragmentProfile());
                        getSupportActionBar().setTitle("پروفایل3");
                        return true;
                    case R.id.menuMatboat:
                        replaceView(new FragmentMatboat());
                        getSupportActionBar().setTitle("مطبوعات");
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

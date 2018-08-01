package ir.mahoorsoft.app.stationsfanclub.view.avtivity_main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.Pref;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.PrefKey;
import ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ActivityTicket;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.home.FragmentHome;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.profile.FragmentPelakValidation;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.profile.FragmentProfile;

public class ActivityMain extends AppCompatActivity {

    public RelativeLayout pBarMain;
    Toolbar toolbar;
    BottomNavigationView navigationView;
    TextView txtActionBar;
    private ImageView imgBtnTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        G.context = this;
        G.activity = this;
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about_club)
            showDialogAboutClub();
        return super.onOptionsItemSelected(item);
    }

    private void showDialogAboutClub() {
        Dialog dialog = new Dialog(this);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_about_club, null, false);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void init() {
        pointers();
        setSupportActionBar(toolbar);
        setUpNavigationItemListener();
        G.disableShiftModeNavigation(navigationView);
        replaceView(new FragmentHome());
        txtActionBar.setText("خانه");
    }

    private void pointers() {
        txtActionBar = (TextView) findViewById(R.id.txtActionBarMain);
        ((ImageView) findViewById(R.id.imgBtnTicketActivityMAin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityTicket.class);
                startActivity(intent);
            }
        });
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
                        txtActionBar.setText("خانه");
                        return true;
                    case R.id.menuMap:
                        replaceView(new FragmentMap());
                        getSupportActionBar().hide();
                        return true;
                    case R.id.menuLottery:
                        replaceView(new FragmentLottery());
                        getSupportActionBar().show();
                        txtActionBar.setText("قرعه کشی ها");
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

    @Override
    protected void onResume() {
        super.onResume();
        G.context = this;
        G.activity = this;
    }
}

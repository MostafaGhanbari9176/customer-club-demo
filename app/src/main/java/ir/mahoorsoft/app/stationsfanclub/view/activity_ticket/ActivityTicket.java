package ir.mahoorsoft.app.stationsfanclub.view.activity_ticket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.motedavel.FragmentMotedavelTicketList;
import ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_list.FragmentTicketList;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.profile.FragmentProfile;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class ActivityTicket extends AppCompatActivity {

    Toolbar toolbar;
    Fragment fragment;
    TextView txtActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        G.context = this;
        G.activity = this;
        setContentView(R.layout.activity_ticket);
        init();
    }

    private void init() {
        pointers();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        replaceView(new FragmentTicketList(), "تیکت و پشتیبانی");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ticket, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_motedavel_message)
            replaceView(new FragmentMotedavelTicketList(), "سوالات متداول");
        return super.onOptionsItemSelected(item);
    }

    public void replaceView(Fragment fragment, String actionBarText) {
        this.fragment = fragment;
        txtActionBar.setText(actionBarText);
        G.activity.getSupportFragmentManager().beginTransaction().replace(R.id.contentTicket, fragment).commit();
    }

    private void pointers() {
        txtActionBar = (TextView) findViewById(R.id.txtActionBarTicket);
        toolbar = (Toolbar) findViewById(R.id.tlbTicket);
    }

    @Override
    public void onBackPressed() {
        if (fragment instanceof FragmentTicketList) {
            super.onBackPressed();
            this.finish();
        }
        else
            replaceView(new FragmentTicketList(), "تیکت و پشتیبانی");
    }
}

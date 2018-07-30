package ir.mahoorsoft.app.stationsfanclub.view.matboat.fragment_tablighat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTablighat;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Tablighat;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterLotteryList;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterViewPager;


/**
 * Created by RCC1 on 4/28/2018.
 */

public class FragmentTablighat extends Fragment implements Tablighat.OnTablighatResponseListener {

    View view;
    ArrayList<StNews> source = new ArrayList<>();
    RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tablighat, container, false);
        init();

        return view;
    }

    private void init() {
        pointer();
        getDataFromServer();
    }

    private void pointer() {
        list = (RecyclerView) view.findViewById(R.id.RVFragmentTablighat);
    }

    private void getDataFromServer() {
        (new Tablighat(this)).getTablighat();
    }


    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {

    }

    @Override
    public void onReceiveTablighat(ArrayList<StTablighat> tablighats) {

        AdapterTablighatList adapterLotteryList = new AdapterTablighatList(G.context, tablighats);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, LinearLayout.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapterLotteryList);
        adapterLotteryList.notifyDataSetChanged();
    }

    @Override
    public void sendMessage(String message) {

    }
}
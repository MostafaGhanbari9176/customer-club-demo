package ir.mahoorsoft.app.stationsfanclub.view;


import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.IndicatorConfiguration;
import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;
import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTablighat;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentLottery;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentNews;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentTablighat;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterLotteryList;
import ir.mahoorsoft.app.stationsfanclub.view.date.DateCreator;
import ir.mahoorsoft.app.stationsfanclub.view.home.AdapterNewsList;


/**
 * Created by RCC1 on 4/28/2018.
 */

public class FragmentLottery extends Fragment implements PresentLottery.OnPresentLotteryListener, AdapterLotteryList.OnLotteryItemClickListener {

    View view;
    RecyclerView list;
    ArrayList<StLottery> source = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lottery, container, false);
        init();

        return view;
    }

    private void init() {
        pointer();
        getNewsDataFromServer();

    }

    private void pointer() {

        list = (RecyclerView) view.findViewById(R.id.RVFragmentLottery);
    }


    private void getNewsDataFromServer() {
        (new PresentLottery(this)).getLottery();

    }

    @Override
    public void messageFromLottery(String message) {

    }

    @Override
    public void flagFromLottery(boolean flag) {

    }

    @Override
    public void dataFromLottery(ArrayList<StLottery> lotteries) {
        source.clear();
        source.addAll(lotteries);
        AdapterLotteryList adapterLotteryList = new AdapterLotteryList(G.context, lotteries, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, LinearLayout.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapterLotteryList);
        adapterLotteryList.notifyDataSetChanged();

    }

    @Override
    public void itemLotteryClicked(int position) {
        if ((source.get(position).lDate).compareTo(DateCreator.todayDate()) > 0) {
            showDialog("تاریخ برگزاری : "+source.get(position).lDate, "حداقل امتیاز جهت شرکت در قرعه کشی = 100");
        } else {
            showDialog("مصطفی قنبری", "جایزه : "+source.get(position).lAward);
        }
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(G.context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
package ir.mahoorsoft.app.stationsfanclub.view.avtivity_main;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentLottery;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterLotteryList;
import ir.mahoorsoft.app.stationsfanclub.view.date.DateCreator;


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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, RecyclerView.VERTICAL, false);
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
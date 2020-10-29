package ir.mahoorsoft.app.stationsfanclub.view.activity_station_feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.Pref;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.PrefKey;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentLottery;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterLotteryList;

/**
 * Created by RCC1 on 7/28/2018.
 */

public class FragmentStationFeature extends Fragment implements View.OnClickListener, PresentLottery.OnPresentLotteryListener {


    View view;
    TextView txtEmpty;
    TextView txtName;
    TextView txtFamily;
    TextView txtPhone;
    TextView txtSokhtType;
    TextView txtCarType;
    TextView txtPelak;
    RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_station_feature, container, false);
        init();
        return view;
    }

    private void init() {
       // pointers();
       // setData();
       // getLotteryList();
    }

    private void setData(){

        txtName.setText(Pref.getStringValue(PrefKey.cName,""));
        txtFamily.setText(Pref.getStringValue(PrefKey.cFamily,""));
        txtPhone.setText(Pref.getStringValue(PrefKey.cPhone,""));
        txtSokhtType.setText(Pref.getStringValue(PrefKey.sokhtTyp,""));
        txtCarType.setText(Pref.getStringValue(PrefKey.carType,""));
        txtPelak.setText(Pref.getStringValue(PrefKey.pelak,""));
    }

    private void getLotteryList() {

        (new PresentLottery(this)).getLottery();
    }

    private void pointers() {

        list = (RecyclerView) view.findViewById(R.id.RVLotteryFrafmentProfile);
        txtEmpty = (TextView) view.findViewById(R.id.txtEmptyLotteryFragmentProfile);
        txtName = (TextView) view.findViewById(R.id.txtNameFragmentProfile);
        txtFamily = (TextView) view.findViewById(R.id.txtFamilyFragmentProfile);
        txtPhone = (TextView) view.findViewById(R.id.txtPhoneFragmentProfile);
        txtCarType = (TextView) view.findViewById(R.id.txtCarTypeFragmentProfile);
        txtSokhtType = (TextView) view.findViewById(R.id.txtSokhtTypeFragmentProfile);
        txtPelak = (TextView) view.findViewById(R.id.txtStationNameFragmentFeature);
        ((LinearLayout) view.findViewById(R.id.LLBtnShowCarFeature)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LLBtnShowCarFeature:

                break;
        }
    }


    @Override
    public void messageFromLottery(String message) {

    }

    @Override
    public void flagFromLottery(boolean flag) {

    }

    @Override
    public void dataFromLottery(ArrayList<StLottery> lotteries) {

        if (lotteries.get(0).empty == 1) {
            txtEmpty.setVisibility(View.VISIBLE);
            return;
        }
        txtEmpty.setVisibility(View.GONE);
        AdapterLotteryList adapterLotteryList = new AdapterLotteryList(G.context, lotteries);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, LinearLayout.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapterLotteryList);
        adapterLotteryList.notifyDataSetChanged();
    }
}

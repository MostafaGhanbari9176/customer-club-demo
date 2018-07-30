package ir.mahoorsoft.app.stationsfanclub.view.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.Pref;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.PrefKey;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentLottery;
import ir.mahoorsoft.app.stationsfanclub.view.MainActivity;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterLotteryList;

/**
 * Created by RCC1 on 7/28/2018.
 */

public class FragmentProfile extends Fragment implements View.OnClickListener, PresentLottery.OnPresentLotteryListener {

    RelativeLayout pbar;
    View view;
    LinearLayout LLCArFeature;
    ImageView imgCarFeature;
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
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        init();
        return view;
    }

    private void init() {
        pointers();
        setData();
        pbar = ((MainActivity) (G.context)).pBarMain;
        getLotteryList();
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
        pbar.setVisibility(View.VISIBLE);
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
        txtPelak = (TextView) view.findViewById(R.id.txtPelakFragmentProfile);
        ((LinearLayout) view.findViewById(R.id.LLBtnShowCarFeature)).setOnClickListener(this);
        LLCArFeature = (LinearLayout) view.findViewById(R.id.LLCarFeature);
        imgCarFeature = (ImageView) view.findViewById(R.id.imgBtnShowCarFeature);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LLBtnShowCarFeature:
                if (LLCArFeature.getVisibility() == View.GONE) {
                    G.animatingForVisible(LLCArFeature, 0f, 1f, 0);
                    imgCarFeature.setImageResource(R.drawable.ic_up);
                } else {
                    G.animatingForGone(LLCArFeature, 1f, 0f, -LLCArFeature.getHeight());
                    imgCarFeature.setImageResource(R.drawable.ic_down);
                }
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
        pbar.setVisibility(View.GONE);
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

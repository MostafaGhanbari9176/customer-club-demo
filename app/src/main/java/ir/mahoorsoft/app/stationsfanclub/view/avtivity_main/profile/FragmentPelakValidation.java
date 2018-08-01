package ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.Pref;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.PrefKey;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Car;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentCar;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.ActivityMain;

/**
 * Created by RCC1 on 7/28/2018.
 */

public class FragmentPelakValidation extends Fragment implements View.OnClickListener, PresentCar.OnPresentCarResponseListener {

    TextView pelak1;
    TextView pelak2;
    TextView pelak3;
    TextView pelak4;
    RelativeLayout pbar;
    View view;
    String pelak;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pelak_validation, container, false);
        init();
        return view;
    }

    private void init() {
        pointers();
        pbar = ((ActivityMain) (G.context)).pBarMain;
    }

    private void pointers() {
        pelak1 = (TextView) view.findViewById(R.id.txtPelak1PelakValidation);
        pelak2 = (TextView) view.findViewById(R.id.txtPelak2PelakValidation);
        pelak3 = (TextView) view.findViewById(R.id.txtPelak3PelakValidation);
        pelak4 = (TextView) view.findViewById(R.id.txtPelak4PelakValidation);
        ((Button) view.findViewById(R.id.btnConfirmPelak)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirmPelak:
                checkData();
                break;
        }
    }

    private void checkData() {
        pelak1.setActivated(false);
        pelak2.setActivated(false);
        pelak3.setActivated(false);
        pelak4.setActivated(false);
        String s1 = pelak1.getText().toString().trim();
        String s2 = pelak2.getText().toString().trim();
        String s3 = pelak3.getText().toString().trim();
        String s4 = pelak4.getText().toString().trim();
        if (!(TextUtils.isDigitsOnly(s1)) || s1.length() != 2) {
            pelak1.setError("لطفا صحیح وارد کنید");
            pelak1.requestFocus();
        } else if (s2.length() == 0 || TextUtils.isDigitsOnly(s2)) {
            pelak2.setError("لطفا صحیح وارد کنید");
            pelak2.requestFocus();
        } else if (!(TextUtils.isDigitsOnly(s3)) || s3.length() != 3) {
            pelak3.setError("لطفا صحیح وارد کنید");
            pelak3.requestFocus();
        } else if (!(TextUtils.isDigitsOnly(s4)) || s4.length() != 2) {
            pelak4.setError("لطفا صحیح وارد کنید");
            pelak4.requestFocus();
        } else {
            pelak = s1 + "-" + s2 + "-" + s3 + "-" + s4;
            sendDataForServer(s1 + s2 + s3 + s4);
        }
    }

    private void sendDataForServer(String pelak) {
        pbar.setVisibility(View.VISIBLE);
        (new PresentCar(this)).pelakValidation(pelak);
    }

    @Override
    public void messageFromCar(String message) {
        pbar.setVisibility(View.GONE);
        Toast.makeText(G.context, message, Toast.LENGTH_SHORT).show();
        savePelak();

    }

    private void savePelak() {
        Pref.saveStringValue(PrefKey.pelak, pelak);
        ((ActivityMain) (G.context)).replaceView(new FragmentGetCustomerData());
    }

    @Override
    public void flagFromCar(boolean flag) {
        pbar.setVisibility(View.GONE);
    }

    @Override
    public void dataFromCar(ArrayList<Car> cars) {
        pbar.setVisibility(View.GONE);

    }
}

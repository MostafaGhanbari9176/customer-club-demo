package ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.profile;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.Pref;
import ir.mahoorsoft.app.stationsfanclub.model.preferences.PrefKey;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StCustomer;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Car;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentCar;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentCustomer;
import ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.ActivityMain;

/**
 * Created by RCC1 on 7/28/2018.
 */

public class FragmentGetCustomerData extends Fragment implements View.OnClickListener, PresentCar.OnPresentCarResponseListener, PresentCustomer.OnPresentCustomerListener {

    RelativeLayout pbar;
    View view;
    TextView txtName;
    TextView txtFamily;
    TextView txtPhone;
    TextView txtPelak;
    TextView txtSokhtType;
    TextView txtCarType;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_get_customer_data, container, false);
        init();
        return view;
    }

    private void init() {
        pointers();
        txtPelak.setText(Pref.getStringValue(PrefKey.pelak, "error"));
        pbar = ((ActivityMain) (G.context)).pBarMain;
    }

    private void pointers() {
        txtCarType = (TextView) view.findViewById(R.id.txtCarTypeFragmrntGetCustomerData);
        txtSokhtType = (TextView) view.findViewById(R.id.txtSokhtTypeFragmrntGetCustomerData);
        txtFamily = (TextView) view.findViewById(R.id.txtFamilyFragmrntGetCustomerData);
        txtName = (TextView) view.findViewById(R.id.txtNameFragmrntGetCustomerData);
        txtPhone = (TextView) view.findViewById(R.id.txtPhoneFragmrntGetCustomerData);
        txtPelak = (TextView) view.findViewById(R.id.txtPelakFragmrntGetCustomerData);
        ((Button) view.findViewById(R.id.btnCarTypeFragmrntGetCustomerData)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnSokhtTypeFragmrntGetCustomerData)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnSaveCustomerData)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCarTypeFragmrntGetCustomerData:
                showDialogCarType();
                break;
            case R.id.btnSokhtTypeFragmrntGetCustomerData:
                showDialogSokhtType();
                break;
            case R.id.btnSaveCustomerData:
                checkInPutData();
                break;
        }
    }

    private void checkInPutData() {
        if (TextUtils.isEmpty(txtName.getText().toString().trim())) {
            txtName.setError("لطفا نام خودرا وارد کنید");
            txtName.requestFocus();
        } else if (TextUtils.isEmpty(txtFamily.getText().toString().trim())) {
            txtFamily.setError("لطفا فامیلی خودرا وارد کنید");
            txtFamily.requestFocus();
        } else if (txtPhone.getText().toString().trim().length() != 11 || !(TextUtils.isDigitsOnly(txtPhone.getText().toString().trim()))) {
            txtPhone.setError("لطفا صحیح وارد کنید");
            txtPhone.requestFocus();
        } else {
            sendCarDataForServer();
        }
    }

    private void sendCarDataForServer() {
        pbar.setVisibility(View.VISIBLE);
        (new PresentCar(this)).saveCar(txtCarType.getText().toString().trim(), txtSokhtType.getText().toString().trim());
    }

    private void showDialogSokhtType() {
        final Dialog dialog = new Dialog(G.context);
        LayoutInflater layoutInflater = (LayoutInflater) G.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_sokht_type, null, false);
        ((LinearLayout) view.findViewById(R.id.LLBtnBenzin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSokhtType.setText("بنزین");
                dialog.cancel();
            }
        });
        ((LinearLayout) view.findViewById(R.id.LLBtnGaz)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSokhtType.setText("گاز");
                dialog.cancel();
            }
        });
        ((LinearLayout) view.findViewById(R.id.LLBtnGazoile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSokhtType.setText("گازوئیل");
                dialog.cancel();
            }
        });
        ((LinearLayout) view.findViewById(R.id.LLBtnDoGaneSoz)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSokhtType.setText("دوگانه سوز");
                dialog.cancel();
            }
        });

        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showDialogCarType() {
        final Dialog dialog = new Dialog(G.context);
        LayoutInflater layoutInflater = (LayoutInflater) G.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_car_type, null, false);
        ((LinearLayout) view.findViewById(R.id.LLBtnSabok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCarType.setText("سبک");
                dialog.cancel();
            }
        });
        ((LinearLayout) view.findViewById(R.id.LLBtnSangin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCarType.setText("سنگین");
                dialog.cancel();
            }
        });

        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void messageFromCar(String message) {
        pbar.setVisibility(View.GONE);
        Toast.makeText(G.context, message, Toast.LENGTH_SHORT).show();
        flagFromCar(true);
    }

    @Override
    public void flagFromCar(boolean flag) {
        pbar.setVisibility(View.GONE);
        if (flag)
            sendCustomerDataForServer();
        else
            messageFromCar("error try again");
    }

    @Override
    public void dataFromCar(ArrayList<Car> cars) {
        pbar.setVisibility(View.GONE);

    }


    private void sendCustomerDataForServer() {
        pbar.setVisibility(View.VISIBLE);
        (new PresentCustomer(this)).saveCustomer(txtName.getText().toString().trim(), txtFamily.getText().toString().trim(), txtPhone.getText().toString().trim());
    }

    @Override
    public void messageFromCustomer(String message) {
        pbar.setVisibility(View.GONE);

        Toast.makeText(G.context, message, Toast.LENGTH_SHORT).show();
    }

    private void saveCustomerData() {

        Pref.saveStringValue(PrefKey.cName, txtName.getText().toString().trim());
        Pref.saveStringValue(PrefKey.cFamily, txtFamily.getText().toString().trim());
        Pref.saveStringValue(PrefKey.cPhone, txtPhone.getText().toString().trim());
        Pref.saveStringValue(PrefKey.carType, txtCarType.getText().toString().trim());
        Pref.saveStringValue(PrefKey.sokhtTyp, txtSokhtType.getText().toString().trim());
        Pref.saveBollValue(PrefKey.isLogin, true);
        ((ActivityMain) (G.context)).replaceView(new FragmentProfile());
    }

    @Override
    public void flagFromCustomer(boolean flag) {
        pbar.setVisibility(View.GONE);
        saveCustomerData();
    }

    @Override
    public void dataFromCustomer(ArrayList<StCustomer> customers) {
        pbar.setVisibility(View.GONE);
    }
}

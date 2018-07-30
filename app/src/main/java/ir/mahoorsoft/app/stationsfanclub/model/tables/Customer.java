package ir.mahoorsoft.app.stationsfanclub.model.tables;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.api.Api;
import ir.mahoorsoft.app.stationsfanclub.model.api.ApiClient;
import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StCustomer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RCC1 on 7/29/2018.
 */

public class Customer {

    public interface OnCustomerResponseListener {
        void onReceiveFlag(ArrayList<RFServer> res);

        void onReceiveCustomer(ArrayList<StCustomer> customers);

        void sendMessage(String message);
    }

    private OnCustomerResponseListener onCustomerResponseListener;

    public Customer(OnCustomerResponseListener onCustomerResponseListener) {
        this.onCustomerResponseListener = onCustomerResponseListener;
    }

    public void saveCustomer(String name, String family, String phone) {

        Api api = ApiClient.getClient().create(Api.class);
        Call<ArrayList<RFServer>> savCar = api.saveCustomerSelfData(name, family, phone);
        savCar.enqueue(new Callback<ArrayList<RFServer>>() {
            @Override
            public void onResponse(Call<ArrayList<RFServer>> call, Response<ArrayList<RFServer>> response) {
                onCustomerResponseListener.onReceiveFlag(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<RFServer>> call, Throwable t) {
                onCustomerResponseListener.sendMessage(t.getMessage());
            }
        });
    }
}

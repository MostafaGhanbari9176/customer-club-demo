package ir.mahoorsoft.app.stationsfanclub.model.tables;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.api.Api;
import ir.mahoorsoft.app.stationsfanclub.model.api.ApiClient;
import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by RCC1 on 7/29/2018.
 */

public class Car {

    public interface OnCarResponseListener{
        void onReceiveFlag(ArrayList<RFServer> res);
        void onReceiveCar(ArrayList<Car> cars);
        void sendMessage(String message);
    }

    private OnCarResponseListener onCarResponseListener;

    public Car(OnCarResponseListener onCarResponseListener){
        this.onCarResponseListener = onCarResponseListener;
    }

    public void checkPelakValidation(String pelak){
/*        Api api = ApiClient.getClient().create(Api.class);
        Call<ArrayList<RFServer>> validation = api.pelakValidation(pelak);
        validation.enqueue(new Callback<ArrayList<RFServer>>() {
            @Override
            public void onResponse(Call<ArrayList<RFServer>> call, Response<ArrayList<RFServer>> response) {
                onCarResponseListener.onReceiveFlag(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<RFServer>> call, Throwable t) {
                onCarResponseListener.sendMessage(t.getMessage());
            }
        });*/
        onCarResponseListener.onReceiveFlag(null);
    }

    public void saveCustomerCarData(String pelak, String carType, String sokhtType){

        Api api = ApiClient.getClient().create(Api.class);
        Call<ArrayList<RFServer>> savCar = api.saveCustomerCarData(pelak, carType, sokhtType);
        savCar.enqueue(new Callback<ArrayList<RFServer>>() {
            @Override
            public void onResponse(Call<ArrayList<RFServer>> call, Response<ArrayList<RFServer>> response) {
                onCarResponseListener.onReceiveFlag(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<RFServer>> call, Throwable t) {
                onCarResponseListener.sendMessage(t.getMessage());
            }
        });
    }


}

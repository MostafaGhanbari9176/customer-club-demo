package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Car;

/**
 * Created by RCC1 on 7/29/2018.
 */

public class PresentCar implements Car.OnCarResponseListener {


    public interface OnPresentCarResponseListener {
        void messageFromCar(String message);

        void flagFromCar(boolean flag);

        void dataFromCar(ArrayList<Car> cars);
    }

    private OnPresentCarResponseListener onPresentCarResponseListener;

    public PresentCar(OnPresentCarResponseListener onPresentCarResponseListener) {
        this.onPresentCarResponseListener = onPresentCarResponseListener;
    }

    public void pelakValidation(String pelak) {
        (new Car(this)).checkPelakValidation(pelak);
    }

    public void saveCar(String carType, String sokhtType){
        (new Car(this)).saveCustomerCarData("", carType, sokhtType);
    }

    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {
        if (res == null || res.size() == 0)
            onPresentCarResponseListener.flagFromCar(true);
    }

    @Override
    public void onReceiveCar(ArrayList<Car> cars) {
        if (cars == null || cars.size() == 0)
            return;
        onPresentCarResponseListener.dataFromCar(cars);
    }

    @Override
    public void sendMessage(String message) {
        onPresentCarResponseListener.messageFromCar(message);
    }
}

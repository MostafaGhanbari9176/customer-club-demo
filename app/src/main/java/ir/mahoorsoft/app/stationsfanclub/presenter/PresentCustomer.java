package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StCustomer;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Customer;

/**
 * Created by RCC1 on 7/29/2018.
 */

public class PresentCustomer implements Customer.OnCustomerResponseListener {

   public interface OnPresentCustomerListener {
        void messageFromCustomer(String message);

        void flagFromCustomer(boolean flag);

        void dataFromCustomer(ArrayList<StCustomer> customers);
    }

    private OnPresentCustomerListener onPresentCustomerListener;

    public PresentCustomer(OnPresentCustomerListener onPresentCustomerListener) {
        this.onPresentCustomerListener = onPresentCustomerListener;
    }

    public void saveCustomer(String name, String family, String phone) {
        (new Customer(this)).saveCustomer(name, family, phone);
    }

    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {
/*        if (res == null || res.size() == 0)
            return;*/
        onPresentCustomerListener.flagFromCustomer(true);
    }

    @Override
    public void onReceiveCustomer(ArrayList<StCustomer> customers) {
        if (customers == null || customers.size() == 0)
            return;
        onPresentCustomerListener.dataFromCustomer(customers);
    }

    @Override
    public void sendMessage(String message) {

        onPresentCustomerListener.messageFromCustomer(message);
    }
}

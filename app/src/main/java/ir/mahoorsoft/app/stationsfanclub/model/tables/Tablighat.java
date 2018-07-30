package ir.mahoorsoft.app.stationsfanclub.model.tables;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTablighat;

/**
 * Created by RCC1 on 7/30/2018.
 */

public class Tablighat {

    public interface OnTablighatResponseListener {
        void onReceiveFlag(ArrayList<RFServer> res);

        void onReceiveTablighat(ArrayList<StTablighat> tablighats);

        void sendMessage(String message);
    }

    private OnTablighatResponseListener onTablighatResponseListener;

    public Tablighat(OnTablighatResponseListener onTablighatResponseListener) {
        this.onTablighatResponseListener = onTablighatResponseListener;
    }

    public void getTablighat() {

        ArrayList<StTablighat> stLotteries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StTablighat stLottery = new StTablighat();
            stLottery.tSubject = "اپلیکیشن اختصاصی ای\u200Cنتورک برای آگهی\u200Cدهندگان";
            stLotteries.add(stLottery);
        }

        onTablighatResponseListener.onReceiveTablighat(stLotteries);
    }
}

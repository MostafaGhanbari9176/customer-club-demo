package ir.mahoorsoft.app.stationsfanclub.model.tables;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;

/**
 * Created by M-gh on 30-Jul-18.
 */

public class Lottery {

    public interface OnLotteryResponseListener {
        void onReceiveFlag(ArrayList<RFServer> res);

        void onReceiveLottery(ArrayList<StLottery> lottery);

        void sendMessage(String message);
    }

    private OnLotteryResponseListener onLotteryResponseListener;

    public Lottery(OnLotteryResponseListener onLotteryResponseListener) {
        this.onLotteryResponseListener = onLotteryResponseListener;
    }

    public void getLottery() {

        ArrayList<StLottery> stLotteries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StLottery stLottery = new StLottery();
            stLottery.lAward = "Award" + i;
            stLottery.lSubject = "subject" + i;
            stLottery.lDate = "1377-04-" + i;
            stLotteries.add(stLottery);
        }

        onLotteryResponseListener.onReceiveLottery(stLotteries);
    }
}

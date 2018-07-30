package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Lottery;

/**
 * Created by M-gh on 30-Jul-18.
 */

public class PresentLottery implements Lottery.OnLotteryResponseListener {


    public interface OnPresentLotteryListener {
        void messageFromLottery(String message);

        void flagFromLottery(boolean flag);

        void dataFromLottery(ArrayList<StLottery> lotteries);
    }

    private OnPresentLotteryListener onPresentLotteryListener;

    public PresentLottery(OnPresentLotteryListener onPresentLotteryListener) {
        this.onPresentLotteryListener = onPresentLotteryListener;
    }

    public void getLottery() {
        (new Lottery(this)).getLottery();
    }

    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {
        if (res == null || res.size() == 0)
            return;
        onPresentLotteryListener.flagFromLottery(res.get(0).code == 1);
    }

    @Override
    public void onReceiveLottery(ArrayList<StLottery> lottery) {
        if (lottery == null || lottery.size() == 0)
            return;
        onPresentLotteryListener.dataFromLottery(lottery);
    }

    @Override
    public void sendMessage(String message) {
        onPresentLotteryListener.messageFromLottery(message);
    }
}

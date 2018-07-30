package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTablighat;
import ir.mahoorsoft.app.stationsfanclub.model.tables.News;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Tablighat;

/**
 * Created by RCC1 on 7/30/2018.
 */

public class PresentTablighat implements Tablighat.OnTablighatResponseListener {

    public interface OnPresentTablighatListener {
        void messageFromTablighat(String message);

        void flagFromTablighat(boolean flag);

        void dataFromTablighat(ArrayList<StTablighat> tablighats);
    }

    private OnPresentTablighatListener onPresentTablighatListener;

    public PresentTablighat(OnPresentTablighatListener onPresentTablighatListener) {
        this.onPresentTablighatListener = onPresentTablighatListener;
    }

    public void getTablighat() {
        (new Tablighat(this)).getTablighat();
    }


    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {
        if (res == null || res.size() == 0)
            sendMessage("error");
        else
            onPresentTablighatListener.flagFromTablighat(res.get(0).code == 1);
    }

    @Override
    public void onReceiveTablighat(ArrayList<StTablighat> tablighats) {
        if (tablighats == null || tablighats.size() == 0)
            sendMessage("error");
        else
            onPresentTablighatListener.dataFromTablighat(tablighats);
    }

    @Override
    public void sendMessage(String message) {
        onPresentTablighatListener.messageFromTablighat(message);
    }
}

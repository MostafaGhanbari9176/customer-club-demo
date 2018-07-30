package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;

import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;

import ir.mahoorsoft.app.stationsfanclub.model.tables.News;

/**
 * Created by RCC1 on 7/30/2018.
 */

public class PresentNews implements News.OnNewsResponseListener {

    public interface OnPresentNewsListener {
        void messageFromNews(String message);

        void flagFromNews(boolean flag);

        void dataFromNews(ArrayList<StNews> newses);
    }

    private OnPresentNewsListener onPresentNewsListener;

    public PresentNews(OnPresentNewsListener onPresentNewsListener) {
        this.onPresentNewsListener = onPresentNewsListener;
    }

    public void getNews() {
        (new News(this)).getNews();
    }


    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {
        if (res == null || res.size() == 0)
            sendMessage("error");
        else
            onPresentNewsListener.flagFromNews(res.get(0).code == 1);
    }

    @Override
    public void onReceiveNews(ArrayList<StNews> newses) {
        if (newses == null || newses.size() == 0)
            sendMessage("error");
        else
            onPresentNewsListener.dataFromNews(newses);
    }

    @Override
    public void sendMessage(String message) {
        onPresentNewsListener.messageFromNews(message);
    }
}

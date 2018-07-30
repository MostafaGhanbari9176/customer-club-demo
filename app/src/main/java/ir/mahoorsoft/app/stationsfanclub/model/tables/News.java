package ir.mahoorsoft.app.stationsfanclub.model.tables;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTablighat;

/**
 * Created by RCC1 on 7/30/2018.
 */

public class News {


    public interface OnNewsResponseListener {
        void onReceiveFlag(ArrayList<RFServer> res);

        void onReceiveNews(ArrayList<StNews> newses);

        void sendMessage(String message);
    }

    private OnNewsResponseListener onNewsResponseListener;

    public News(OnNewsResponseListener onNewsResponseListener) {
        this.onNewsResponseListener = onNewsResponseListener;
    }

    public void getNews() {

        ArrayList<StNews> stLotteries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StNews stLottery = new StNews();
            stLottery.nSubject = "مهاجم استقلال رسما از این تیم جدا شد";
            stLottery.nText = "به گزارش گروه ورزشی خبرگزاری برنا، مامه تیام که فصل بسیار خوبی را با استقلال پشت سر گذاشت، سرانجام پس از کلی کش و قوس به تیم عجمان امارات پیوست.  تیام در فصلی که گذشت عملکرد بسیار خوبی با استقلال داشت، این بازیکن سنگالی مورد توجه تیم های عربستانی هم بود اما در نهایت تیم اماراتی پیشنهاد بهتری داد و راهی این تیم شد.";
            stLottery.nDate = "1397-04-2" + i;
            stLottery.nHourse = "12:1" + i;
            stLotteries.add(stLottery);
        }

        onNewsResponseListener.onReceiveNews(stLotteries);
    }
}

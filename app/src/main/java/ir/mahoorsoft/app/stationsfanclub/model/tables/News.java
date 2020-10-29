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
            stLottery.nSubject = "اندروید استودیو 4.1 با قابلیت\u200Cهای جدید منتشر شد";
            stLottery.nText = "قابلیتی که احتمالا می\u200Cتواند تأثیر بسزایی بر بهبود فرایند توسعه\u200Cی اپلیکیشن بگذارد، امکان اجرای شبیه\u200Cساز اندروید در اندروید استودیو است. اوایل تابستان امسال، گوگل در بیانیه\u200Cای گفت این قابلیت ازطریق به\u200C\u200Cروزرسانی به اندروید استودیو می\u200Cآید؛ بنابراین چندان متعجب نشدیم. بدین\u200Cترتیب، توسعه\u200Cدهندگان مجبور نیستند برای آزمایش اپلیکیشن\u200Cهایشان، مدام در بین پنجره\u200Cها و ابزارهای مختلف جابه\u200Cجا شوند. ";
            stLottery.nDate = "1399-07-26";
            stLottery.nHourse = "12:1" + i;
            stLotteries.add(stLottery);
        }

        onNewsResponseListener.onReceiveNews(stLotteries);
    }
}

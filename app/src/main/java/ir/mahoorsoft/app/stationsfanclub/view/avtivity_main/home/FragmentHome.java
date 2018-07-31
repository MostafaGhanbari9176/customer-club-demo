package ir.mahoorsoft.app.stationsfanclub.view.avtivity_main.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.IndicatorConfiguration;
import cn.lightsky.infiniteindicator.InfiniteIndicator;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;
import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTablighat;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentNews;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentTablighat;
import ir.mahoorsoft.app.stationsfanclub.view.GlideLoader;


/**
 * Created by RCC1 on 4/28/2018.
 */

public class FragmentHome extends Fragment implements PresentNews.OnPresentNewsListener, PresentTablighat.OnPresentTablighatListener, OnPageClickListener {

    View view;
    cn.lightsky.infiniteindicator.InfiniteIndicator viewPager;
    private ArrayList<Page> pageViews;
    ArrayList<StNews> source = new ArrayList<>();
    RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();

        return view;
    }

    private void init() {
        pointer();
        setPaletteSize();
        getNewsDataFromServer();

    }

    private void pointer() {
        viewPager = (cn.lightsky.infiniteindicator.InfiniteIndicator) view.findViewById(R.id.viewPagerMain);
        list = (RecyclerView) view.findViewById(R.id.RVNews);
    }

    private void setPaletteSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        G.activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        viewPager.getLayoutParams().height = (int) (width / 1.5);
    }

    private void getNewsDataFromServer() {
        (new PresentNews(this)).getNews();

    }

    private void getTablighatDataFromServer(){
        (new PresentTablighat(this)).getTablighat();
    }


    @Override
    public void messageFromNews(String message) {

    }

    @Override
    public void flagFromNews(boolean flag) {

    }

    @Override
    public void dataFromNews(ArrayList<StNews> newses) {
        AdapterNewsList adapterLotteryList = new AdapterNewsList(G.context, newses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, LinearLayout.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapterLotteryList);
        adapterLotteryList.notifyDataSetChanged();

        getTablighatDataFromServer();
    }

    @Override
    public void messageFromTablighat(String message) {

    }

    @Override
    public void flagFromTablighat(boolean flag) {

    }

    @Override
    public void dataFromTablighat(ArrayList<StTablighat> tablighats) {
        pageViews = new ArrayList<>();
        for (int i = 0; i < tablighats.size(); i++) {
            pageViews.add(new Page(tablighats.get(i).img+"", "https://static2.borna.news/thumbnail/5KMtLPIam7ls/zKlnR2CgDMts54GkxMYEj8fvMMUiCETIT3Il7Z8QJ7YCZYBL8OGb1tkXRmfG0ZrOzG4dCpLzLSmmrKvCe4wUTpsw1L7Dn2vC/20795.jpg", this));
        }
        settingUpVPager();
    }

    private void settingUpVPager() {
        InfiniteIndicator  mAnimCircleIndicator = (InfiniteIndicator) view.findViewById(R.id.viewPagerMain);
        IndicatorConfiguration configuration = new IndicatorConfiguration.Builder()
                .imageLoader(new GlideLoader())
                .isAutoScroll(true)
                .isLoop(true)
                .isDrawIndicator(true)
                .onPageClickListener(this)
                .direction(1)
                .position(IndicatorConfiguration.IndicatorPosition.Center_Bottom)
                .internal(5000)
                .scrollDurationFactor(5)
                .build();

        mAnimCircleIndicator.init(configuration);
        mAnimCircleIndicator.notifyDataChange(pageViews);


    }

    @Override
    public void onPageClick(int position, Page page) {

    }
}
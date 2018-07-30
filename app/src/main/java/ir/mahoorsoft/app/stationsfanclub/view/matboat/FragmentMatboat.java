package ir.mahoorsoft.app.stationsfanclub.view.matboat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.view.adapters.AdapterViewPager;
import ir.mahoorsoft.app.stationsfanclub.view.matboat.fragment_news.FragmentNews;
import ir.mahoorsoft.app.stationsfanclub.view.matboat.fragment_tablighat.FragmentTablighat;


/**
 * Created by RCC1 on 4/28/2018.
 */

public class FragmentMatboat extends Fragment {

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    AdapterViewPager adapterViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_matboat, container, false);
        init();

        return view;
    }

    private void init() {
        pointer();
        tabLayout.setupWithViewPager(viewPager);
        settingUpViewPager();
    }

    private void pointer() {
        viewPager = (ViewPager) view.findViewById(R.id.VPFragmentMatboat);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutFragmentMatboat);
    }

    private void settingUpViewPager() {
        adapterViewPager = new AdapterViewPager(getChildFragmentManager());
        adapterViewPager.add(new FragmentNews(), "news");
       adapterViewPager.add(new FragmentTablighat(), "tablighat");
        viewPager.setAdapter(adapterViewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);

    }


}
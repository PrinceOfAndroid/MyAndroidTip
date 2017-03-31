package com.bdrk.myandroidtip.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bdrk.myandroidtip.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 5u51_5 on 2017/3/14.
 */

public class TabDemoActivity extends AppCompatActivity {
    @InjectView(R.id.tab_vp)
    TabLayout tabVp;
    @InjectView(R.id.vp_test)
    ViewPager vpTest;
    private List<Fragment> fragments;
    private List<String> titles;
    private MyPagerAdapter adapter;
    private int posi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_demo);
        ButterKnife.inject(this);
        initData();
        initView();
    }

    private void initView() {
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        vpTest.setAdapter(adapter);
        tabVp.setupWithViewPager(vpTest);
        for (int i = 0; i < titles.size(); i++) {
            TabLayout.Tab tab = tabVp.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }


        vpTest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //上次选中的tab
                TextView tv = (TextView) tabVp.getTabAt(posi).getCustomView().findViewById(R.id.tv_title);
                tv.setTextColor(Color.parseColor("#000000"));

                //当前选中的tab
                TextView tv2 = (TextView) tabVp.getTabAt(position).getCustomView().findViewById(R.id.tv_title);
                tv2.setTextColor(Color.parseColor("#4cc5ea"));
                posi = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private View getTabView(int i) {
        View view = getLayoutInflater().inflate(R.layout.my_tab, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(titles.get(i));

        //初始状态下，第一个选中，颜色设置为蓝色
        if (i==0){
            tvTitle.setTextColor(Color.parseColor("#4cc5ea"));
        }
        return view;
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new VpFragment());
        fragments.add(new VpFragment());
        fragments.add(new VpFragment());
        fragments.add(new VpFragment());
        titles = new ArrayList<>();
        titles.add("Tab1");
        titles.add("Tab2");
        titles.add("Tab3");
        titles.add("Tab4");
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}

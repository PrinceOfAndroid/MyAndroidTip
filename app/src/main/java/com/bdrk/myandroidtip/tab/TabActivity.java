package com.bdrk.myandroidtip.tab;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdrk.myandroidtip.R;
import com.bdrk.myandroidtip.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 5u51_5 on 2017/3/2.
 */

public class TabActivity extends BaseActivity {


    @InjectView(R.id.my_tab)
    TabLayout myTab;
    @InjectView(R.id.tab_vp)
    ViewPager tabVp;
    private List<Fragment> fragments;
    private MyPagerAdapter adapter;

    private String[] titles = {"TAB ONE", "TAB TWO", "TAB THREE"};
    private int[] imageResId = {R.mipmap.one, R.mipmap.two, R.mipmap.three};
    private int posi = 0;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_tab;
    }

    @Override
    protected void initTitle() {
        setTitleText("TAB ANIM");
    }

    @Override
    protected void initListener() {
        tabVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("ssssssssss", position + "    " + positionOffset + "       " + positionOffsetPixels);
                myTab.getTabAt(posi).getCustomView().findViewById(R.id.iv_tab).setVisibility(View.GONE);
                myTab.getTabAt(posi).getCustomView().findViewById(R.id.tv_tab).setVisibility(View.VISIBLE);

                myTab.getTabAt(position).getCustomView().findViewById(R.id.iv_tab).setVisibility(View.VISIBLE);
                myTab.getTabAt(position).getCustomView().findViewById(R.id.tv_tab).setVisibility(View.GONE);
            }

            @Override
            public void onPageSelected(int position) {

                myTab.getTabAt(posi).getCustomView().findViewById(R.id.iv_tab).setVisibility(View.GONE);
                myTab.getTabAt(posi).getCustomView().findViewById(R.id.tv_tab).setVisibility(View.VISIBLE);

                myTab.getTabAt(position).getCustomView().findViewById(R.id.iv_tab).setVisibility(View.VISIBLE);
                myTab.getTabAt(position).getCustomView().findViewById(R.id.tv_tab).setVisibility(View.GONE);
                posi = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);
        fragments = new ArrayList<>();
        fragments.add(TabFragment.newInstance("TAB ONE"));
        fragments.add(TabFragment.newInstance("TAB TWO"));
        fragments.add(TabFragment.newInstance("TAB THREE"));

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        tabVp.setAdapter(adapter);
        myTab.setupWithViewPager(tabVp);
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = myTab.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }

        Log.e("sssssssss",myTab.getTabCount()+"");

    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_tab, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab);
        tv.setText(titles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.iv_tab);
        img.setImageResource(imageResId[position]);
        if (position == 0) {
            img.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
        } else {
            tv.setVisibility(View.VISIBLE);
            img.setVisibility(View.GONE);
        }
        return view;
    }


    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }


    }
}

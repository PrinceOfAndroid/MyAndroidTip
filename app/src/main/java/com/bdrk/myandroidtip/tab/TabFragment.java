package com.bdrk.myandroidtip.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bdrk.myandroidtip.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 5u51_5 on 2017/3/2.
 */

public class TabFragment extends Fragment {



    @InjectView(R.id.tv_tab)
    TextView tvTab;
    private String msg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            msg = args.getString("msg");
            Log.e("ssssss", msg);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(view);
        ButterKnife.inject(this, view);
        return view;
    }


    private void initView(View view) {
        tvTab = (TextView) view.findViewById(R.id.tv_tab);
        tvTab.setText(msg);
    }

    public static TabFragment newInstance(String msg) {
        TabFragment tabFragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString("msg", msg);
        tabFragment.setArguments(args);
        return tabFragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

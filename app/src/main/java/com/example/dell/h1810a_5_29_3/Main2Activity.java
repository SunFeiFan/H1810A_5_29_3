package com.example.dell.h1810a_5_29_3;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.h1810a_5_29_3.bean.GrilBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewPager mVp;
    private ArrayList<String> mList;
    private VpAdapter mVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mList = new ArrayList<>();
        mVpAdapter = new VpAdapter(this, mList);
        mVp.setAdapter(mVpAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void OnEventBus(MainActivity.EventBusMassage eventBusMassage){
        List<GrilBean.ResultsBean> resultsBeans = eventBusMassage.mResultsBeans;
        for (int i = 0; i < resultsBeans.size(); i++) {
            GrilBean.ResultsBean resultsBean = resultsBeans.get(i);
            String url = resultsBean.getUrl();
            mList.add(url);
            mVpAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

package com.example.dell.h1810a_5_29_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dell.h1810a_5_29_3.adapter.MainAdapter;
import com.example.dell.h1810a_5_29_3.bean.GrilBean;
import com.example.dell.h1810a_5_29_3.presenter.Presenter;
import com.example.dell.h1810a_5_29_3.view.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {
    private RecyclerView mRel;
    private ArrayList<GrilBean.ResultsBean> mList;
    private MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Presenter presenter = new Presenter(this);
        presenter.getIPresenter();
    }

    private void initView() {
        mRel = (RecyclerView) findViewById(R.id.rel);
        mRel.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mMainAdapter = new MainAdapter(this, mList);
        mRel.setAdapter(mMainAdapter);
        mMainAdapter.setOnClickListener(new MainAdapter.CallBack() {
            @Override
            public void OnClick(GrilBean.ResultsBean resultsBean, int position) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                EventBusMassage eventBusMassage = new EventBusMassage();
                eventBusMassage.mResultsBeans = mList;
                EventBus.getDefault().postSticky(eventBusMassage);
            }

            @Override
            public void OnLongClick(int position) {

            }
        });
    }

    public class EventBusMassage {
        List<GrilBean.ResultsBean> mResultsBeans;
    }

    @Override
    public void getIViewYes(GrilBean grilBean) {
        List<GrilBean.ResultsBean> results = grilBean.getResults();
        mList.addAll(results);
        mMainAdapter.setList(mList);
        mMainAdapter.notifyDataSetChanged();
    }

    private static final String TAG = "MainActivity";
    @Override
    public void getIViewNo(String string) {
        Log.d(TAG, "getIViewNo: string"+string);
    }
}

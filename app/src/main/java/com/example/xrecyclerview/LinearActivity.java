package com.example.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class LinearActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (XRecyclerView) this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        // init toolbar
        toolbar.setOnMenuItemClickListener(this);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(header);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        listData.clear();
                        for (int i = 0; i < 15; i++) {
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 15; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });

        listData = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            listData.add("item" + i);
        }
        mAdapter = new MyAdapter(listData);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setRefreshing(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SysProgress:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress);
                break;
            case R.id.BallPulse:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
                break;
            case R.id.BallGridPulse:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallGridPulse);
                break;
            case R.id.BallClipRotate:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
                break;
            case R.id.BallClipRotatePulse:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
                break;
            case R.id.SquareSpin:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.SquareSpin);
                break;
            case R.id.BallClipRotateMultiple:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
                break;
            case R.id.BallPulseRise:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulseRise);
                break;
            case R.id.BallRotate:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallRotate);
                break;
            case R.id.CubeTransition:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.CubeTransition);
                break;
            case R.id.BallZigZag:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag);
                break;
            case R.id.BallZigZagDeflect:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZagDeflect);
                break;
            case R.id.BallTrianglePath:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallTrianglePath);
                break;
            case R.id.BallScale:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallScale);
                break;
            case R.id.LineScale:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScale);
                break;
            case R.id.LineScaleParty:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScaleParty);
                break;
            case R.id.BallScaleMultiple:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallScaleMultiple);
                break;
            case R.id.BallPulseSync:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulseSync);
                break;
            case R.id.BallBeat:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallBeat);
                break;
            case R.id.LineScalePulseOut:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScalePulseOut);
                break;
            case R.id.LineScalePulseOutRapid:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
                break;
            case R.id.BallScaleRipple:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallScaleRipple);
                break;
            case R.id.BallScaleRippleMultiple:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
                break;
            case R.id.BallSpinFadeLoader:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
                break;
            case R.id.LineSpinFadeLoader:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
                break;
            case R.id.TriangleSkewSpin:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.TriangleSkewSpin);
                break;
            case R.id.Pacman:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.Pacman);
                break;
            case R.id.BallGridBeat:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallGridBeat);
                break;
            case R.id.SemiCircleSpin:
                mRecyclerView.setRefreshProgressStyle(ProgressStyle.SemiCircleSpin);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loading_menu, menu);
        return true;
    }
}

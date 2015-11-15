package com.zhuyongit.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuyongit.recyclerviewdemo.adapter.SwipeRefreshRecyclerAdapter;
import com.zhuyongit.recyclerviewdemo.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  zeno
 *
 * @email   zhuyongluck@qq.com
 *
 * @website http://www.zhuyongit.com
 *
 * @version 1.0
 *
 * RecyclerView 基本用法 ，配合SwipeLayout做出下拉刷新数据的效果
 */

public class SwipeRefreshRecyclerView extends AppCompatActivity {

    private static final String TAG = "SwipeRefreshRecyclerView";

    private SwipeRefreshLayout sflRecyclerView;
    private RecyclerView rvBasicRecyclerView;
    private FloatingActionButton fabAddItem;

    private SwipeRefreshRecyclerAdapter mAdapter = new SwipeRefreshRecyclerAdapter(getDatas("1"));
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_recycler_view);

        initView();

        bindData();

        setListener();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        fabAddItem = (FloatingActionButton) findViewById(R.id.fabAddItem);
        rvBasicRecyclerView = (RecyclerView) findViewById(R.id.rvBasicRecyclerView);
        sflRecyclerView = (SwipeRefreshLayout) findViewById(R.id.sflRecyclerView);
        // 设置刷新变换的颜色
        sflRecyclerView.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
    }

    /**
     * 呈现数据
     */
    private void bindData() {
        mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvBasicRecyclerView.setLayoutManager(mLayoutManager);
//        rvBasicRecyclerView.addItemDecoration(new RecyclerViewItemDivider(this));
        rvBasicRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rvBasicRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置事件监听
     */
    private void setListener() {
        /**
         * 向RecyclerView添加Item项
         */
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = ((LinearLayoutManager) mLayoutManager).findFirstCompletelyVisibleItemPosition();
                mAdapter.addItem(position + 1);
            }
        });

        // 设置Item像的长按事件监听
        mAdapter.setOnLongClickListener(new SwipeRefreshRecyclerAdapter.OnLongClickListener() {
            @Override
            public void onLongCick(View view, int position) {
                mAdapter.deleteItem(position);
            }
        });

        // 设置下拉刷新出发事件
        sflRecyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mAdapter.refreshAllData(getDatas("2"));
            }
        });

    }

    /**
     * 为列表提供数据 ， 设置不同的flag ， 区分刷新数据
     * @param flag
     * @return
     */
    public List<NewsBean> getDatas(String flag) {
        List<NewsBean> newsBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            newsBeans.add(new NewsBean("科技大神 " + flag, "这是神一样的新闻Content " + flag));
        }

        if (sflRecyclerView != null) {
            // 判断是不是正在刷新
            if (sflRecyclerView.isRefreshing()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 2秒之后关闭刷新
                        sflRecyclerView.setRefreshing(false);
                    }
                }, 2000) ;
            }
        }

        return newsBeans;
    }

}

package com.zhuyongit.recyclerviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuyongit.recyclerviewdemo.adapter.GridRecyclerAdapter;
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
 * RecyclerView 基本用法 ，gridView效果
 */

public class GridRecyclerView extends AppCompatActivity {

    private RecyclerView            rvGridRecyclerView ;
    private FloatingActionButton    fabAddItem ;

    private GridRecyclerAdapter    mAdapter = new GridRecyclerAdapter(getDatas()) ;
    private RecyclerView.LayoutManager mLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycler_view);

        initView();

        bindData();

        setListener();
    }


    /**
     * 初始化组件
     */
    private void initView() {
        fabAddItem = (FloatingActionButton) findViewById(R.id.fabAddItem);
        rvGridRecyclerView = (RecyclerView) findViewById(R.id.rvGridRecyclerView);

    }

    /**
     * 呈现数据
     */
    private void bindData() {
        mLayoutManager = new GridLayoutManager(this, 3) ;
        //StaggeredGrid
        rvGridRecyclerView.setLayoutManager(mLayoutManager);
//        rvBasicRecyclerView.addItemDecoration(new RecyclerViewItemDivider(this));
        rvGridRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rvGridRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置事件监听
     */
    private void setListener() {
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = ((GridLayoutManager)mLayoutManager).findFirstCompletelyVisibleItemPosition() ;
                mAdapter.addItem(position+1);
            }
        });

        mAdapter.setOnLongClickListener(new GridRecyclerAdapter.OnLongClickListener() {
            @Override
            public void onLongCick(View view, int position) {
                mAdapter.deleteItem(position);
            }
        });

    }


    public List<NewsBean> getDatas() {
        List<NewsBean> newsBeans = new ArrayList<>() ;
        for (int i=0 ; i< 100 ; i++) {
            newsBeans.add(new NewsBean("科技大神 "+i,"这是神一样的新闻Content "+i));
        }

        return newsBeans;
    }
}

package com.zhuyongit.recyclerviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zhuyongit.recyclerviewdemo.adapter.StaggerGridRecyclerAdapter;
import com.zhuyongit.recyclerviewdemo.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class StaggerGridRecyclerView extends AppCompatActivity {

    private RecyclerView            rvStaggerGridRecyclerView ;
    private FloatingActionButton    fabAddItem ;

    private StaggerGridRecyclerAdapter    mAdapter = new StaggerGridRecyclerAdapter(getDatas()) ;
    private RecyclerView.LayoutManager    mLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger_grid_recycler_view);

        initView();

        bindData();

        setListener();
    }


    /**
     * 初始化组件
     */
    private void initView() {
        fabAddItem = (FloatingActionButton) findViewById(R.id.fabAddItem);
        rvStaggerGridRecyclerView = (RecyclerView) findViewById(R.id.rvStaggerGridRecyclerView);

    }

    /**
     * 呈现数据
     */
    private void bindData() {
        // StaggeredGridLayoutManager.VERTICAL
        // StaggeredGridLayoutManager.HORIZONTAL
        mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL) ;
        //StaggeredGrid
        rvStaggerGridRecyclerView.setLayoutManager(mLayoutManager);
//        rvBasicRecyclerView.addItemDecoration(new RecyclerViewItemDivider(this));
        rvStaggerGridRecyclerView.setItemAnimator(new DefaultItemAnimator());
        rvStaggerGridRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置事件监听
     */
    private void setListener() {
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.addItem(1);
            }
        });

        mAdapter.setOnLongClickListener(new StaggerGridRecyclerAdapter.OnLongClickListener() {
            @Override
            public void onLongCick(View view, int position) {
                mAdapter.deleteItem(position);
            }
        });

    }


    /**
     * 创建数据
     * @return
     */
    public List<NewsBean> getDatas() {
        List<NewsBean> newsBeans = new ArrayList<>() ;
        for (int i=0 ; i< 100 ; i++) {
            // 模拟同样的布局不同的内容 ， 来展现瀑布流
            if (i % 2 == 0) {
                newsBeans.add(new NewsBean("科技大神 ","这是神一样的新闻Content 这是神一样的新闻Content 这是神一样的新闻Content 这是神一样的新闻Content"));
            }else
            {
                newsBeans.add(new NewsBean("科技大神 "+i,"这是神一样的新闻Content "+i));
            }
        }

        return newsBeans;
    }
}

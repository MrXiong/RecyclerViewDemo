package com.zhuyongit.recyclerviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuyongit.recyclerviewdemo.adapter.BasicRecyclerAdapter;
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
 * RecyclerView 基本用法 ，ListView效果
 */
public class BasicRecyclerView extends AppCompatActivity {

    private static final String TAG = "BasicRecyclerView" ;

    private RecyclerView    rvBasicRecyclerView ;
    private FloatingActionButton    fabAddItem ;

    private BasicRecyclerAdapter    mAdapter = new BasicRecyclerAdapter(getDatas()) ;
    private RecyclerView.LayoutManager mLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_recycler_view);

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

    }

    /**
     * 呈现数据
     */
    private void bindData() {
        // 设置显示布局 ，垂直方向显示list -- RecyclerView.VERTICAL
        // 水平方向显示list RecyclerView.HORIZONTAL
        mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false) ;
        rvBasicRecyclerView.setLayoutManager(mLayoutManager);
        // 使用ItemDecroation画出Divider
//        rvBasicRecyclerView.addItemDecoration(new RecyclerViewItemDivider(this));
        // 设置RecyclerView默认动画
        rvBasicRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置RecyclerView数据适配器
        rvBasicRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置事件监听
     */
    private void setListener() {
        // 添加RecyclerView Item
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = ((LinearLayoutManager)mLayoutManager).findFirstCompletelyVisibleItemPosition() ;
                mAdapter.addItem(position+1);
            }
        });

        /**
         * 长按删除
         */
        mAdapter.setOnLongClickListener(new BasicRecyclerAdapter.OnLongClickListener() {
            @Override
            public void onLongCick(View view, int position) {
                mAdapter.deleteItem(position);
            }
        });

    }


    /**
     * 初始化数据
     * @return
     */
    public List<NewsBean> getDatas() {
        List<NewsBean> newsBeans = new ArrayList<>() ;
        for (int i=0 ; i< 100 ; i++) {
            newsBeans.add(new NewsBean("科技大神 "+i,"这是神一样的新闻Content "+i));
        }

        return newsBeans;
    }

}

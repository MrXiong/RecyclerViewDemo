package com.zhuyongit.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author  Zeno
 *
 * @email   zhuyongluck@qq.com | http://www.zhuyongit.com
 *
 * @version  1.0
 *
 * @description : RecyclerView Demo
 *
 */

public class MainActivity extends AppCompatActivity {


    private Button      btnBasicRecyclerView ;
    private Button      btnGridRecyclerView ;
    private Button      btnStaggerGridRecyclerView ;
    private Button      btnSwipeRefreshRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setListener();
    }

    private void initView() {
        btnBasicRecyclerView = (Button) findViewById(R.id.btnBasicRecyclerView);
        btnGridRecyclerView = (Button) findViewById(R.id.btnGridRecyclerView);
        btnStaggerGridRecyclerView = (Button) findViewById(R.id.btnStaggerGridRecyclerView);
        btnSwipeRefreshRecyclerView = (Button) findViewById(R.id.btnSwipeRefreshRecyclerView);
    }

    private void setListener() {
        /**
         *  RecyclerView 基本用法 list布局
         */
        btnBasicRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(BasicRecyclerView.class);
            }
        });

        /**
         *  RecyclerView 实现GridView效果
         */
        btnGridRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(GridRecyclerView.class);
            }
        });

        btnStaggerGridRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(StaggerGridRecyclerView.class);
            }
        });

        btnSwipeRefreshRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(SwipeRefreshRecyclerView.class);
            }
        });
    }

    private void startNewActivity(Class cls) {
        Intent _intent = new Intent(this,cls) ;
        startActivity(_intent);
    }
}

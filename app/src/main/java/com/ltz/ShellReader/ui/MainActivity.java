package com.ltz.ShellReader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ltz.ShellReader.R;
import com.ltz.ShellReader.adapter.RecyclerAdapter;
import com.ltz.ShellReader.base.BaseActivity;
import com.ltz.ShellReader.utils.DataConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Qloop on 2016/9/6.
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.rv_unit_list)
    RecyclerView rvUnitList;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        DataConfig dataConfig = DataConfig.getInstance();
        rvUnitList.setHasFixedSize(true);
        rvUnitList.setLayoutManager(new LinearLayoutManager(rvUnitList.getContext()));
        //设置显示动画
        rvUnitList.setItemAnimator(new DefaultItemAnimator());
        RecyclerAdapter adapter = new RecyclerAdapter(dataConfig.articleInfos,MainActivity.this);
        rvUnitList.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ArticleContentActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}

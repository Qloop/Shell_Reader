package com.ltz.ShellReader.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ltz.ShellReader.R;
import com.ltz.ShellReader.base.BaseActivity;
import com.ltz.ShellReader.views.AlignTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Qloop on 2016/9/6.
 */
public class test extends BaseActivity {


    @BindView(R.id.tv_article_content)
    AlignTextView tvArticleContent;
    private Handler mHandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvArticleContent.setText("ltz");
                }
            });

            System.out.println("=========================");
        }
    };

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.test);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        tvArticleContent.setText("dafaddg");
        new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=0;i<10000000;i++ ){

                }
                mHandler.sendEmptyMessage(123);
            }
        }).start();
    }
}

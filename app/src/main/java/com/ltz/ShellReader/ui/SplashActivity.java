package com.ltz.ShellReader.ui;

import android.content.Intent;
import android.os.Bundle;

import com.ltz.ShellReader.R;
import com.ltz.ShellReader.base.BaseActivity;
import com.ltz.ShellReader.utils.DataConfig;

import java.io.IOException;

/**
 * Created by Qloop on 2016/9/8.
 */
public class SplashActivity extends BaseActivity {

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initData() {
        final DataConfig data = DataConfig.getInstance();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    data.getWordLevel(getAssets().open("nce4_words.txt"));
                    data.getArticle(getAssets().open("nce4_article.txt"));
                    sleep(2000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        thread.start();
    }
}

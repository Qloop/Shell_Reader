package com.ltz.ShellReader.ui;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenuItem;
import android.widget.TextView;

import com.ltz.ShellReader.R;
import com.ltz.ShellReader.base.BaseActivity;
import com.ltz.ShellReader.utils.DataConfig;
import com.ltz.ShellReader.utils.TextOptions;
import com.ltz.ShellReader.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Qloop on 2016/9/10.
 */
public class ArticleContentActivity extends BaseActivity {

    private static final int COMPLETED = 1;
    private boolean isAlign = false;
    @BindView(R.id.tv_article_content)
    TextView mArticleContent;
    @BindView(R.id.tv_article_content_align)
    TextView mArticleContentAlign;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.menu)
    SatelliteMenu menu;
    private Map<String, Object> currentContent;
    private DataConfig dataConfig;
    SpannableStringBuilder styled;
//    final private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == COMPLETED) {
//                System.out.println("handler");
//                mArticleContent.setText(styled);
//                ToastUtils.showToastShort(ArticleContentActivity.this, mArticleContent.getText().toString());
//                TextOptions.getWordClickedHighLight(mArticleContent, currentContent.get("article").toString());
//            }
//        }
//    };

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.MyTitle);

        //初始化卫星菜单
        List<SatelliteMenuItem> items = new ArrayList<>();
        items.add(new SatelliteMenuItem(1, R.mipmap.menu_1));
        items.add(new SatelliteMenuItem(2, R.mipmap.menu_2));
        items.add(new SatelliteMenuItem(3, R.mipmap.menu_3));
        items.add(new SatelliteMenuItem(4, R.mipmap.menu_4));
        items.add(new SatelliteMenuItem(5, R.mipmap.menu_5));
        items.add(new SatelliteMenuItem(6, R.mipmap.align));//对齐显示
        menu.addItems(items);
    }

    @Override
    public void initData() {
        int position = getIntent().getIntExtra("position", -1);
        dataConfig = DataConfig.getInstance();
        List<Map<String, Object>> articleInfos = dataConfig.articleInfos;
        currentContent = articleInfos.get(position);
        mArticleContent.setText(currentContent.get("article").toString());

        toolbarLayout.setTitle(currentContent.get("unit").toString().split("- ")[1]);

        //点击单词反馈
        TextOptions.getWordClickedHighLight(mArticleContent, currentContent.get("article").toString());

        menu.setOnItemClickedListener(new SatelliteMenu.SateliteClickedListener() {

            public void eventOccured(int id) {
                Log.i("sat", "Clicked on " + id);
                if (id == 6 && !isAlign) {
                    //设置对齐显示
                    mArticleContent.setVisibility(View.GONE);
                    mArticleContentAlign.setVisibility(View.VISIBLE);
                    mArticleContentAlign.setText(currentContent.get("article").toString());
                    isAlign = true;
                    ToastUtils.showToastShort(ArticleContentActivity.this,"对齐显示");
                } else if (id == 6 && isAlign) {
                    //设置对齐显示
                    mArticleContent.setVisibility(View.VISIBLE);
                    mArticleContentAlign.setVisibility(View.GONE);
                    isAlign = false;
                    ToastUtils.showToastShort(ArticleContentActivity.this,"自由显示");
                }
                LightWordOptions(id);
                TextOptions.getWordClickedHighLight(mArticleContent, currentContent.get("article").toString());
            }
        });
    }

    private void LightWordOptions(final int level) {
//        new Thread(new LightRunnable(level)).start();
        final String articleContent = currentContent.get("article").toString();
        new AsyncTask<Integer, SpannableStringBuilder, SpannableStringBuilder>() {

            @Override
            protected SpannableStringBuilder doInBackground(Integer... params) {
                styled = new SpannableStringBuilder(articleContent);

                switch (level) {
                    case 5:
                        System.out.println("5555555555");
                        for (String word : dataConfig.wordsOfLevel_5) {
                            int start = articleContent.indexOf(word);
                            int end = start + word.length();
                            if (start < 0) {
                                continue;
                            }
                            System.out.println("-=-=-=" + word);
                            styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
                                    start,
                                    end,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    case 4:
                        System.out.println("444");
                        for (String word : dataConfig.wordsOfLevel_4) {
                            int start = articleContent.indexOf(word);
                            int end = start + word.length();
                            if (start < 0) {
                                continue;
                            }
                            System.out.println("-=-=-=" + word);
                            styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
                                    start,
                                    end,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    case 3:
                        System.out.println("333");
                        for (String word : dataConfig.wordsOfLevel_3) {
                            int start = articleContent.indexOf(word);
                            int end = start + word.length();
                            if (start < 0) {
                                continue;
                            }
                            System.out.println("-=-=-=" + word);
                            styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
                                    start,
                                    end,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    case 2:
                        System.out.println("222");
                        for (String word : dataConfig.wordsOfLevel_2) {
                            int start = articleContent.indexOf(word);
                            int end = start + word.length();
                            if (start < 0) {
                                continue;
                            }
                            System.out.println("-=-=-=" + word);
                            styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
                                    start,
                                    end,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            );
                        }
                    case 1:
                        System.out.println("1111");
                        for (String word : dataConfig.wordsOfLevel_1) {
                            int start = articleContent.indexOf(word);
                            int end = start + word.length();
                            if (start < 0) {
                                continue;
                            }
                            System.out.println("-=-=-=" + word);
                            styled.setSpan(
                                    new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
                                    start,
                                    end,
                                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            );
                            System.out.println("start is " + start);
                            System.out.println("end is " + end);
                        }
                }
                return styled;
            }

            protected void onProgressUpdate(SpannableStringBuilder... progress) {
//                mArticleContent.setText(progress[0]);
//                ToastUtils.showToastShort(ArticleContentActivity.this,mArticleContent.getText().toString());
            }

            protected void onPostExecute(SpannableStringBuilder result) {
                mArticleContent.setText(result);
            }

            protected void onPreExecute() {
            }

            protected void onCancelled() {
            }
        }.execute(level);
    }

    /**
     * 匹配单词等级  准备高亮显示
     * TODO 这种方式存在问题【会出现textView不能更新的问题】  时间原因放弃使用，之后研究原因
     */
//    class LightRunnable implements Runnable {
//        String articleContent = currentContent.get("article").toString();
//        int mLevel;
//
//        public LightRunnable(int level) {
//            this.mLevel = level;
//        }
//
//        @Override
//        public void run() {
//            styled = new SpannableStringBuilder(articleContent);
//
//            switch (mLevel) {
//                case 5:
//                    System.out.println("5555555555");
//                    for (String word : dataConfig.wordsOfLevel_5) {
//                        int start = articleContent.indexOf(word);
//                        int end = start + word.length();
//                        if (start < 0) {
//                            continue;
//                        }
//                        System.out.println("-=-=-=" + word);
//                        styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
//                                start,
//                                end,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }
//                case 4:
//                    System.out.println("444");
//                    for (String word : dataConfig.wordsOfLevel_4) {
//                        int start = articleContent.indexOf(word);
//                        int end = start + word.length();
//                        if (start < 0) {
//                            continue;
//                        }
//                        System.out.println("-=-=-=" + word);
//                        styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
//                                start,
//                                end,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }
//                case 3:
//                    System.out.println("333");
//                    for (String word : dataConfig.wordsOfLevel_3) {
//                        int start = articleContent.indexOf(word);
//                        int end = start + word.length();
//                        if (start < 0) {
//                            continue;
//                        }
//                        System.out.println("-=-=-=" + word);
//                        styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
//                                start,
//                                end,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    }
//                case 2:
//                    System.out.println("222");
//                    for (String word : dataConfig.wordsOfLevel_2) {
//                        int start = articleContent.indexOf(word);
//                        int end = start + word.length();
//                        if (start < 0) {
//                            continue;
//                        }
//                        System.out.println("-=-=-=" + word);
//                        styled.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
//                                start,
//                                end,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                        );
//                    }
//                case 1:
//                    System.out.println("1111");
//                    for (String word : dataConfig.wordsOfLevel_1) {
//                        int start = articleContent.indexOf(word);
//                        int end = start + word.length();
//                        if (start < 0) {
//                            continue;
//                        }
//                        System.out.println("-=-=-=" + word);
//                        styled.setSpan(
//                                new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
//                                start,
//                                end,
//                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                        );
//                        System.out.println("start is " + start);
//                        System.out.println("end is " + end);
//                    }
//            }
//
//            mHandler.sendEmptyMessage(COMPLETED);
//        }
//
//    }
}

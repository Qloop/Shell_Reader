package com.ltz.ShellReader.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据配置
 * Created by Qloop on 2016/9/8.
 */
public class DataConfig {


    public List<Map<String, Object>> values = new ArrayList<>();
    public Map<String, Integer> listOfWords = new HashMap<>();
    public List<String> wordsOfLevel_0 = new ArrayList<>();
    public List<String> wordsOfLevel_1 = new ArrayList<>();
    public List<String> wordsOfLevel_2 = new ArrayList<>();
    public List<String> wordsOfLevel_3 = new ArrayList<>();
    public List<String> wordsOfLevel_4 = new ArrayList<>();
    public List<String> wordsOfLevel_5 = new ArrayList<>();

    private static DataConfig mDataConfig;

    private DataConfig(){}

    public static DataConfig getInstance() {
        if (mDataConfig == null) {
            synchronized (DataConfig.class) {
                if (mDataConfig == null) {
                    mDataConfig = new DataConfig();
                }
            }
        }
        return mDataConfig;
    }

    /**
     * 获取单词等级数据
     *
     * @param is
     * @return 是否获取成功
     */
    public boolean getWordLevel(InputStream is) {
        try {
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String lineData = br.readLine();
            while (lineData != null) {
                String[] splitStr = lineData.split("\t");
                int level = Integer.valueOf(splitStr[1]);
                switch (level) {
                    case 0:
                        wordsOfLevel_0.add(splitStr[0]);
                        break;
                    case 1:
                        wordsOfLevel_1.add(splitStr[0]);
                        break;
                    case 2:
                        wordsOfLevel_2.add(splitStr[0]);
                        break;
                    case 3:
                        wordsOfLevel_3.add(splitStr[0]);
                        break;
                    case 4:
                        wordsOfLevel_4.add(splitStr[0]);
                        break;
                    case 5:
                        wordsOfLevel_5.add(splitStr[0]);
                        break;
                    default:
                        break;
                }
                lineData = br.readLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 获取文章数据
     *
     * @param is
     * @return
     */
    public List<Map<String, Object>> getArticle(InputStream is) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            StringBuffer strUnit = new StringBuffer();
            StringBuffer strArticle = new StringBuffer();
            boolean unitLine = false;
            boolean makeNewLine = true;
            while ((line = br.readLine()) != null) {
                if (line.equals("F_UNIT_START")) {
                    unitLine = true;
                } else if (line.equals("F_ARTICLE_START")) {
                    unitLine = false;
                } else if (line.equals("F_ARTICLE_END")) {
                    //read end
                    Map<String, Object> map = new HashMap<>();
                    map.put("unit", strUnit.toString());
                    map.put("article", strArticle.toString());
                    values.add(map);

                    //clear temp data
                    strUnit = new StringBuffer();
                    strArticle = new StringBuffer();
                    makeNewLine = true;
                } else {
                    if (unitLine) {
                        strUnit.append(line);
                        while (makeNewLine) {
                            strUnit.append("\n");
                            makeNewLine = false;
                        }
                    } else {
                        strArticle.append(line);
                        strArticle.append("\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}

package com.ltz.ShellReader.utils;

import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.Locale;

/**
 * Created by Qloop on 2016/9/10.
 */
public class TextOptions {

    public static void getWordClickedHighLight(TextView contentTextView, String content) {
        contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
        contentTextView.setText(content, TextView.BufferType.SPANNABLE);
        Spannable spans = (Spannable) contentTextView.getText();
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
        iterator.setText(content);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String possibleWord = content.substring(start, end);
            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {
                ClickableSpan clickSpan = getClickableSpan(possibleWord);
                spans.setSpan(clickSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                spans.setSpan(new BackgroundColorSpan(Color.argb(255,31,157,133)),
//                        start,
//                        end,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    public static ClickableSpan getClickableSpan(final String word) {
        return new ClickableSpan() {

            final String mWord;

            {
                mWord = word;
            }

            @Override
            public void onClick(View widget) {
                TextView textView = (TextView) widget;
                String wordClicked = textView.getText().subSequence(textView.getSelectionStart(), textView.getSelectionEnd()).toString();
//                String text = textView.getText().toString();
//                int start = text.indexOf(wordClicked);
//                int end = start + wordClicked.length();
//                SpannableStringBuilder ssb = new SpannableStringBuilder(text);
//                ssb.setSpan(new BackgroundColorSpan(Color.argb(255, 31, 157, 133)),
//                        start,
//                        end,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                textView.setText(ssb);
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
    }
}

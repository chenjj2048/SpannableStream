package com.cjj.spannablestream;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

/**
 * Created on 2016/7/24
 *
 * @author chenjj2048
 */
public interface ISpannable {
    /**
     * 创建新文本
     */
    SpannableStream text(CharSequence str);

    SpannableStream newLine();

    SpannableStringBuilder build();

    void into(TextView textView);

    /**
     * 前景色
     */
    SpannableStream color(@ColorInt int color);

    SpannableStream colorRes(@ColorRes int colorRes);

    /**
     * 背景色
     */
    SpannableStream bgColor(@ColorInt int color);

    SpannableStream bgColorRes(@ColorRes int colorRes);


}

package com.cjj.spannablestream;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.StyleRes;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

/**
 * Created on 2016/7/24
 *
 * @author chenjj2048
 */
public interface ISpannable {
    ISpannable alwaysNewLineAfterInsert(boolean bAlwayInsertNewLine);

    /**
     * 创建新文本
     */
    ISpannable text(CharSequence str);

    ISpannable newLine();

    SpannableStringBuilder build();

    void into(TextView textView);

    /**
     * 前景色
     */
    ISpannable color(@ColorInt int color);

    ISpannable colorRes(@ColorRes int colorRes);

    /**
     * 背景色
     */
    ISpannable bgColor(@ColorInt int color);

    ISpannable bgColorRes(@ColorRes int colorRes);

    ISpannable bold();

    ISpannable italic();

    ISpannable underline();

    ISpannable strikeThrough();

    ISpannable superScript();

    ISpannable subScript();

    ISpannable textSizePx(int px);

    ISpannable textSizeDp(int dp);

    ISpannable relativeTextSize(@FloatRange(from = 0, to = 10) float ratio);

    ISpannable scaleX(@FloatRange(from = 0, to = 10) float ratio);

    ISpannable image(Drawable drawable);

    ISpannable image(@DrawableRes int drawableRes);

    ISpannable image(Bitmap bitmap);

    ISpannable textUrl(String str);

    ISpannable textApperance(@StyleRes int res);

    ISpannable onClick(SpannableClickListener listener);
}

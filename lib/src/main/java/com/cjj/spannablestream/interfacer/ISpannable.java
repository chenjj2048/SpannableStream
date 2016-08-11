package com.cjj.spannablestream.interfacer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.StyleRes;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.cjj.spannablestream.OnSpannableClickListener;
import com.cjj.spannablestream.SpannableFunc;

/**
 * Created on 2016/7/24
 *
 * @author chenjj2048
 */
@Deprecated
public interface ISpannable {
    ISpannable configueAddNewLineAfterInsert(boolean bAlwayInsertNewLine);

    /**
     * 创建新文本
     */
    ISpannable appendText(CharSequence str);

    ISpannable appendText(CharSequence str, boolean withNewLine);

    ISpannable appendNewLine();

    SpannableStringBuilder build();

    void into(TextView textView);

    void into(Activity activity, @IdRes int resId);

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

    ISpannable superScript(float textSizeRatio);

    ISpannable subScript(float textSizeRatio);

    ISpannable textSizePx(int px);

    ISpannable textSizeDp(int dp);

    ISpannable textSizeSp(int sp);

    ISpannable relativeTextSize(@FloatRange(from = 0, to = 10) float ratio);

    ISpannable scaleX(@FloatRange(from = 0, to = 10) float ratio);

    ISpannable appendImage(Drawable drawable);

    ISpannable appendImage(@DrawableRes int drawableRes);

    ISpannable appendImage(Bitmap bitmap);

    ISpannable appendUrlText(String str);

    ISpannable textApperance(@StyleRes int res);

    ISpannable onClick(OnSpannableClickListener listener);

    ISpannable aligmentCenter();

    ISpannable aligmentLeft();

    ISpannable aligmentRight();

    //Todo:spans以后改掉
//    ISpannable replaceRecentString(CharSequence str,SpansPool spans);

    ISpannable replaceRecentString(CharSequence str,SpannableFunc spans);
    //Todo:增加ClickSpan点击颜色变化
}

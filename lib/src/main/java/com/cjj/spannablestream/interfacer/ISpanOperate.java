package com.cjj.spannablestream.interfacer;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.StyleRes;

import com.cjj.spannablestream.OnSpannableClickListener;

/**
 * Created on 2016/8/10
 *
 * @author chenjj2048
 */
public interface ISpanOperate<T> {
    /**
     * 前景色
     */
    T color(@ColorInt int color);

    T colorRes(@ColorRes int colorRes);

    /**
     * 背景色
     */
    T bgColor(@ColorInt int color);

    T bgColorRes(@ColorRes int colorRes);

    T bold();

    T italic();

    T underline();

    T strikeThrough();

    T superScript();

    T subScript();

    T superScript(float textSizeRatio);

    T subScript(float textSizeRatio);

    T textSizePx(int px);

    T textSizeDp(int dp);

    T textSizeSp(int sp);

    T relativeTextSize(@FloatRange(from = 0, to = 10) float ratio);

    T scaleX(@FloatRange(from = 0, to = 10) float ratio);

    T appendImage(Drawable drawable);

    T appendImage(@DrawableRes int drawableRes);

    T appendImage(Bitmap bitmap);

    T appendUrlText(String str);

    T textApperance(@StyleRes int res);

    T onClick(OnSpannableClickListener listener);

    T aligmentCenter();

    T aligmentLeft();

    T aligmentRight();
}

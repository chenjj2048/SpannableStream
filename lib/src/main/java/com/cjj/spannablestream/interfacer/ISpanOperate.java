package com.cjj.spannablestream.interfacer;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.text.SpannableString;

import com.cjj.spannablestream.click.OnSpannableClickListener;
import com.cjj.spannablestream.color.ColorConfig;

/**
 * Created on 2016/8/10
 *
 * @author chenjj2048
 */
public interface ISpanOperate {
    interface Collection<T> extends Color<T>, TextSize<T>, TypeFace<T>,
            UpperSubScript<T>, Aligment<T>, Click<T>, Other<T> {
    }

    interface Color<T> {
        T color(@ColorInt int color);

        T colorRes(@ColorRes int colorRes);

        T bgColor(@ColorInt int color);

        T bgColorRes(@ColorRes int colorRes);
    }

    interface TextSize<T> {
        T textSizePx(int px);

        T textSizeDp(int dp);

        T textSizeSp(int sp);

        T relativeTextSize(float ratio);

        T scaleX(float ratio);
    }

    interface TypeFace<T> {
        T bold();

        T italic();

        T underline();

        T strikeThrough();
    }

    interface UpperSubScript<T> {
        T superScript();

        T subScript();

        T superScript(float textSizeRatio);

        T subScript(float textSizeRatio);
    }

    interface Aligment<T> {
        T aligmentCenter();

        T aligmentLeft();

        T aligmentRight();
    }

    interface Click<T> {
        T onClick(OnSpannableClickListener listener);

        T onClick(ColorConfig colorConfig, OnSpannableClickListener listener);
    }

    interface Other<T> {
        T textApperance(@StyleRes int resId);
    }

    interface Build {
        Object getCurrentSpan();

        void apply(SpannableString spannableString);

        void apply(SpannableString spannableString, int start, int end, int flag);

        int getOperateCount();

        Build build();
    }
}

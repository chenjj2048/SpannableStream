package com.cjj.spannablestream.color;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

/**
 * Created on 2016/8/13
 *
 * @author chenjj2048
 */
public class ColorConfig {
    private int mTextColorNormal;
    private int mTextColorPressed;
    private int mBackgroundColorNormal;
    private int mBackgroundColorPressed;

    private ColorConfig() {
    }

    public static ColorConfig getDefault() {
        return new ColorConfig();
    }

    public ColorConfig colorNormal(@ColorInt int color) {
        this.mTextColorNormal = color;
        if (mTextColorPressed == 0)
            this.mTextColorPressed = color;
        return this;
    }

    public ColorConfig colorNormal(Context context, @ColorRes int resId) {
        int color = ContextCompat.getColor(context, resId);
        return colorNormal(color);
    }

    public ColorConfig colorPressed(@ColorInt int color) {
        this.mTextColorPressed = color;
        return this;
    }

    public ColorConfig colorPressed(Context context, @ColorRes int resId) {
        int color = ContextCompat.getColor(context, resId);
        return colorPressed(color);
    }

    public ColorConfig bgColorNormal(@ColorInt int color) {
        this.mBackgroundColorNormal = color;
        return this;
    }

    public ColorConfig bgColorNormal(Context context, @ColorRes int resId) {
        int color = ContextCompat.getColor(context, resId);
        return bgColorNormal(color);
    }

    public ColorConfig bgColorPressed(@ColorInt int color) {
        this.mBackgroundColorPressed = color;
        return this;
    }

    public ColorConfig bgColorPressed(Context context, @ColorRes int resId) {
        int color = ContextCompat.getColor(context, resId);
        return bgColorPressed(color);
    }

    public int getTextColorNormal() {
        return mTextColorNormal;
    }

    public int getTextColorPressed() {
        return mTextColorPressed;
    }

    public int getBackgroundColorNormal() {
        return mBackgroundColorNormal;
    }

    public int getBackgroundColorPressed() {
        return mBackgroundColorPressed;
    }
}

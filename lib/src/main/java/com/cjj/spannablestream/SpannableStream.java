package com.cjj.spannablestream;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created on 2016/7/24
 *
 * @author chenjj2048
 */
public class SpannableStream implements ISpannable {
    private final LinkedList<SpannableString> mList;
    private Context context;

    public SpannableStream(Context context) {
        this.context = context;
        mList = new LinkedList<>();
    }

    public static SpannableStream with(Context context) {
        return new SpannableStream(context);
    }

    private void applyStyle(CharacterStyle characterSytle) {
        final SpannableString last = mList.getLast();
        final int size = last.length();
        last.setSpan(characterSytle, 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    @SuppressWarnings("all")
    @Override
    public SpannableStringBuilder build() {
        final SpannableStringBuilder sb = new SpannableStringBuilder();
        final Iterator<SpannableString> iterator = mList.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        return sb;
    }

    @Override
    public void into(TextView textView) {
        final SpannableStringBuilder strBuilder = build();
        textView.setText(strBuilder);
    }

    @Override
    public SpannableStream text(CharSequence str) {
        mList.add(new SpannableString(str));
        return this;
    }

    @Override
    public SpannableStream newLine() {
        final String NEW_LINE = "\r\n";
        mList.add(new SpannableString(NEW_LINE));
        return this;
    }

    /**
     * =======================
     * 各类风格
     * =======================
     */


    @Override
    public SpannableStream color(@ColorInt int color) {
        applyStyle(new ForegroundColorSpan(color));
        return this;
    }

    @Override
    public SpannableStream colorRes(@ColorRes int colorRes) {
        int value = context.getResources().getColor(colorRes);
        return color(value);
    }

    @Override
    public SpannableStream bgColor(@ColorInt int color) {
        applyStyle(new BackgroundColorSpan(color));
        return this;
    }

    @Override
    public SpannableStream bgColorRes(@ColorRes int colorRes) {
        int value = context.getResources().getColor(colorRes);
        return bgColor(value);
    }
}

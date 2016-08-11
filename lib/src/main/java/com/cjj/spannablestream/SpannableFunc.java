package com.cjj.spannablestream;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;

/**
 * Created on 2016/8/11
 *
 * @author chenjj2048
 */
public class SpannableFunc {
    private final SpannableDecorator mParent;

    private SpannableFunc(SpannableDecorator parent) {
        this.mParent = parent;
    }

    public static SpannableFunc getDefault() {
        return new SpannableFunc(null);
    }

    public SpannableFunc underline() {
        return new SpannableFunc(new SpannableDecorator(mParent) {
            @Override
            protected Object getSpan() {
                return new UnderlineSpan();
            }
        });
    }

    public SpannableFunc color(final int color) {
        return new SpannableFunc(new SpannableDecorator(mParent) {
            @Override
            protected Object getSpan() {
                return new ForegroundColorSpan(color);
            }
        });
    }

    void apply(SpannableString spanString, int start, int end, int flag) {
        mParent.apply(spanString, start, end, flag);
    }
}

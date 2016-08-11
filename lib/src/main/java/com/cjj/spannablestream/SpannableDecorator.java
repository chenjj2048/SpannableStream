package com.cjj.spannablestream;

import android.text.SpannableString;

/**
 * Created on 2016/8/11
 *
 * @author chenjj2048
 */

/**
 * 装饰者模式
 */
public abstract class SpannableDecorator {
    private final SpannableDecorator mParent;

    public SpannableDecorator(SpannableDecorator parent) {
        this.mParent = parent;
    }

    /**
     * @return new span attribute
     */
    protected abstract Object getSpan();

    /**
     * @param spanString spannableString
     * @param start      start
     * @param end        end
     * @param flag       flag
     */
    protected void apply(SpannableString spanString, int start, int end, int flag) {
        if (spanString == null)
            return;

        if (mParent != null)
            mParent.apply(spanString, start, end, flag);

        Object span = getSpan();
        if (span != null)
            spanString.setSpan(span, start, end, flag);
    }
}

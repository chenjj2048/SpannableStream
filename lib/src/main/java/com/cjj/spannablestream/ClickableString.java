package com.cjj.spannablestream;

import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created on 2016/8/9
 *
 * @author chenjj2048
 */
class ClickableString extends ClickableSpan {
    private final SpannableString spannableString;
    private final OnSpannableClickListener mListener;

    public ClickableString(SpannableString str, OnSpannableClickListener listener) {
        this.spannableString = str;
        this.mListener = listener;
    }

    @Override
    public void onClick(View widget) {
        if (mListener != null)
            mListener.onSpannableItemClick(widget, spannableString);
    }
}

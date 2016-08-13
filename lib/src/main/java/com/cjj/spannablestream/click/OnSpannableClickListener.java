package com.cjj.spannablestream.click;

import android.view.View;

/**
 * Created on 2016/7/24
 *
 * @author chenjj2048
 */

/**
 * There is a child class SimpleSpannableClickListener.class.
 * You can use it to simplify the code.
 */
public interface OnSpannableClickListener {
    void onSpannableItemClick(View widget, CharSequence str);

    void onPressedStateChanged(boolean pressed);
}

package com.cjj.spannablestream.click;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

import com.cjj.spannablestream.interfacer.IClickable;

/**
 * Changed with follow code.
 * https://github.com/iwgang/SimplifySpan/blob/973a985725b1579adb5ea967974b068b3171adf1/library/src/main/java/cn/iwgang/simplifyspan/other/CustomLinkMovementMethod.java
 */
public class CustomLinkMovementMethod<T extends IClickable.OnSpannableClickListener> extends LinkMovementMethod {
    private final Class<T> CLAZZ;
    private T mCustomClickableSpan;

    public CustomLinkMovementMethod(Class<T> clazz) {
        this.CLAZZ = clazz;
    }

    public static CustomLinkMovementMethod getInstance() {
        return Singleton.INSTANCE;
    }

    @Override
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        super.onTouchEvent(textView, spannable, event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCustomClickableSpan = getPressedSpan(textView, spannable, event);
            if (mCustomClickableSpan != null) {
                mCustomClickableSpan.onPressedStateChanged(true);
                Selection.setSelection(spannable, spannable.getSpanStart(mCustomClickableSpan), spannable.getSpanEnd(mCustomClickableSpan));
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            T touchedSpan = getPressedSpan(textView, spannable, event);
            if (mCustomClickableSpan != null && touchedSpan != mCustomClickableSpan) {
                mCustomClickableSpan.onPressedStateChanged(false);
                mCustomClickableSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            if (mCustomClickableSpan != null) {
                mCustomClickableSpan.onPressedStateChanged(false);
                super.onTouchEvent(textView, spannable, event);
            }
            mCustomClickableSpan = null;
            Selection.removeSelection(spannable);
        }
        return true;
    }

    private T getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        T[] link = spannable.getSpans(off, off, CLAZZ);
        T touchedSpan = null;
        if (link.length > 0) {
            touchedSpan = link[0];
        }
        return touchedSpan;
    }

    private static class Singleton {
        static final CustomLinkMovementMethod INSTANCE =
                new CustomLinkMovementMethod<>(ClickableSpanWrapper.class);
    }
}

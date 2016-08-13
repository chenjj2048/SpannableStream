package com.cjj.spannablestream.click;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 参考自
 * https://github.com/iwgang/SimplifySpan/blob/973a985725b1579adb5ea967974b068b3171adf1/library/src/main/java/cn/iwgang/simplifyspan/other/CustomLinkMovementMethod.java
 */
public class CustomLinkMovementMethod extends LinkMovementMethod {
    private ClickableSpanWrapper mCustomClickableSpan;

    public static CustomLinkMovementMethod getInstance() {
        return Singleton.INSTANCE;
    }

    @Override
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        super.onTouchEvent(textView, spannable, event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCustomClickableSpan = getPressedSpan(textView, spannable, event);
            if (mCustomClickableSpan != null) {
                mCustomClickableSpan.onPressStateChanged(true);
                Selection.setSelection(spannable, spannable.getSpanStart(mCustomClickableSpan), spannable.getSpanEnd(mCustomClickableSpan));
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            ClickableSpanWrapper touchedSpan = getPressedSpan(textView, spannable, event);
            if (mCustomClickableSpan != null && touchedSpan != mCustomClickableSpan) {
                mCustomClickableSpan.onPressStateChanged(false);
                mCustomClickableSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            if (mCustomClickableSpan != null) {
                mCustomClickableSpan.onPressStateChanged(false);
                super.onTouchEvent(textView, spannable, event);
            }
            mCustomClickableSpan = null;
            Selection.removeSelection(spannable);
        }
        return true;
    }

    private ClickableSpanWrapper getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        ClickableSpanWrapper[] link = spannable.getSpans(off, off, ClickableSpanWrapper.class);
        ClickableSpanWrapper touchedSpan = null;
        if (link.length > 0) {
            touchedSpan = link[0];
        }
        return touchedSpan;
    }

    private static class Singleton {
        static final CustomLinkMovementMethod INSTANCE = new CustomLinkMovementMethod();
    }
}

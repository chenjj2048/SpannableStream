package com.cjj.spannablestream.interfacer;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

/**
 * Created on 2016/8/11
 *
 * @author chenjj2048
 */
public interface ISpan {
    interface Config<T> {
        T configueAddNewLineAfterInsert(boolean bAlwayInsertNewLine);
    }

    interface Append<T> {
        T appendText(CharSequence str);

        T appendText(CharSequence str, boolean withNewLine);

        T appendNewLine();
    }

    interface Build {
        SpannableStringBuilder build();
    }

    interface Setting {
        void into(TextView textView);

        void into(Activity activity, @IdRes int resId);
    }
}

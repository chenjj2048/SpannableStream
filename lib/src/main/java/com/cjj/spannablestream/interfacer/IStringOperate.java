package com.cjj.spannablestream.interfacer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

/**
 * Created on 2016/8/11
 *
 * @author chenjj2048
 */
public interface IStringOperate {
    interface Collection<T> extends Config<T>, Append<T>, Build, TextSetting, Replacement<T> {
    }

    interface Config<T> {
        T configueAlwaysLineBreak(boolean alwayLineBreak);
    }

    interface Append<T> {
        T appendText(CharSequence str);

        T appendText(@StringRes int resId);

        T appendNewLine();

        T appendNewLine(int count);

        T appendImage(Drawable drawable);

        T appendImage(@DrawableRes int drawableRes);

        T appendImage(Bitmap bitmap);

        T appendUrlText(String str);
    }

    interface Build {

        /**
         * Recommend use into(TextView) or into(context,resId) instead
         * because when use onClick()
         * the TextView will be automatically set that:
         * textView.setMovementMethod(CustomLinkMovementMethod.getInstance());
         * <p>
         * It will set the
         *
         * @return SpannableStringBuilder
         */
        SpannableStringBuilder build();
    }

    interface TextSetting {
        void into(TextView textView);

        void into(Activity activity, @IdRes int textViewResId);
    }

    interface Replacement<T> {
        T replaceString(CharSequence str, ISpanOperate.Build spans);
    }
}

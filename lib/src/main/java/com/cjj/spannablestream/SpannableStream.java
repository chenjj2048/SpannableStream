package com.cjj.spannablestream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created on 2016/7/24
 *
 * @author chenjj2048
 */
public class SpannableStream implements ISpannable {
    private static final SpannableString PIC_PLACEHOLDER = new SpannableString("图片占位");
    private final LinkedList<SpannableString> mList;
    private Context context;
    private boolean bAlwaysNewLine = false;

    private SpannableStream(Context context) {
        this.context = context;
        mList = new LinkedList<>();
    }

    public static ISpannable with(Context context) {
        return new SpannableStream(context);
    }

    private void applyStyle(Object span) {
        final SpannableString last = mList.getLast();
        final int size = last.length();
        last.setSpan(span, 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    @Override
    public ISpannable alwaysNewLineAfterInsert(boolean bAlwayInsertNewLine) {
        this.bAlwaysNewLine = bAlwayInsertNewLine;
        return this;
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
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public ISpannable text(CharSequence str) {
        if (mList.size() != 0 && bAlwaysNewLine)
            newLine();

        mList.add(new SpannableString(str));
        return this;
    }

    @Override
    public ISpannable newLine() {
        final String NEW_LINE = "\r\n";
        mList.add(new SpannableString(NEW_LINE));
        return this;
    }

    /**
     * =======================
     * 各类字体风格
     * =======================
     */


    @Override
    public ISpannable color(@ColorInt int color) {
        applyStyle(new ForegroundColorSpan(color));
        return this;
    }

    @Override
    public ISpannable colorRes(@ColorRes int colorRes) {
        int value = ContextCompat.getColor(context, colorRes);
        return color(value);
    }

    @Override
    public ISpannable bgColor(@ColorInt int color) {
        applyStyle(new BackgroundColorSpan(color));
        return this;
    }

    @Override
    public ISpannable bgColorRes(@ColorRes int colorRes) {
        int value = ContextCompat.getColor(context, colorRes);
        return bgColor(value);
    }

    @Override
    public ISpannable bold() {
        applyStyle(new StyleSpan(Typeface.BOLD));
        return this;
    }

    @Override
    public ISpannable italic() {
        applyStyle(new StyleSpan(Typeface.ITALIC));
        return this;
    }

    @Override
    public ISpannable underline() {
        applyStyle(new UnderlineSpan());
        return this;
    }

    @Override
    public ISpannable strikeThrough() {
        applyStyle(new StrikethroughSpan());
        return this;
    }

    @Override
    public ISpannable superScript() {
        applyStyle(new SuperscriptSpan());
        return this;
    }

    @Override
    public ISpannable subScript() {
        applyStyle(new SubscriptSpan());
        return this;
    }

    @Override
    public ISpannable textSizePx(int px) {
        applyStyle(new AbsoluteSizeSpan(px));
        return this;
    }

    @Override
    public ISpannable textSizeDp(int dp) {
        applyStyle(new AbsoluteSizeSpan(dp, true));
        return this;
    }

    @Override
    public ISpannable relativeTextSize(@FloatRange(from = 0, to = 10) float ratio) {
        applyStyle(new RelativeSizeSpan(ratio));
        return this;
    }

    @Override
    public ISpannable scaleX(@FloatRange(from = 0, to = 10) float ratio) {
        applyStyle(new ScaleXSpan(ratio));
        return this;
    }

    @Override
    public ISpannable image(Drawable drawable) {
        mList.add(PIC_PLACEHOLDER);
        applyStyle(new ImageSpan(drawable));
        return this;
    }

    @Override
    public ISpannable image(@DrawableRes int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return image(drawable);
    }

    @Override
    public ISpannable image(Bitmap bitmap) {
        mList.add(PIC_PLACEHOLDER);
        applyStyle(new ImageSpan(context, bitmap));
        return this;
    }

    @Override
    public ISpannable textUrl(String str) {
        mList.add(new SpannableString(str));
        applyStyle(new URLSpan(str));
        return this;
    }

    @Override
    public ISpannable textApperance(@StyleRes int res) {
        applyStyle(new TextAppearanceSpan(context, res));
        return this;
    }

    @Override
    public ISpannable onClick(final SpannableClickListener listener) {
        if (listener == null) return this;
        final SpannableString spannableString = mList.getLast();

        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (spannableString == PIC_PLACEHOLDER) {
                    listener.onImageClick();
                } else {
                    listener.onStringClick(spannableString.toString());
                }
            }
        };
        applyStyle(span);
        return this;
    }
}

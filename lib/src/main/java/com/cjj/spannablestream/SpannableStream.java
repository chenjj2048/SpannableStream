package com.cjj.spannablestream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
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

import com.cjj.spannablestream.interfacer.ISpannable;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created on 2016/7/24
 *
 * @author chenjj2048
 */
public class SpannableStream implements ISpannable {
    /**
     * Picture placeholder
     */
    private static final SpannableString PICTURE_PLACEHOLDER = new SpannableString("[IMG]");

    private static final String NEW_LINE = "\r\n";

    /**
     * Collection of spannableString
     */
    private final LinkedList<SpannableString> mList;

    private Context context;
    /**
     * When use this.appendText() to add a new string,it will not add a new line default.
     */
    private boolean bNewLineAdd = false;

    /**
     * Private Constructor, use SpannableStream.with(context) to create new instance instead.
     *
     * @param context context
     */
    private SpannableStream(Context context) {
        this.context = context;
        mList = new LinkedList<>();
    }

    /**
     * @param context context
     * @return new instance of this class
     */
    public static ISpannable with(Context context) {
        return new SpannableStream(context);
    }

    /**
     * Setting span attribute to the last string
     *
     * @param span span
     */
    private void applyStyle(Object span) {
        final SpannableString lastString = mList.getLast();
        if (lastString == null) return;

        final int size = lastString.length();
        lastString.setSpan(span, 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * Change configure,whether add a new line when append a string
     *
     * @param needNewLine whether add a new line when use appendText() function.
     * @return this
     */
    @Override
    public ISpannable configueAddNewLineAfterInsert(boolean needNewLine) {
        this.bNewLineAdd = needNewLine;
        return this;
    }

    /**
     * Build a spannableStringBuilder from data of mList
     *
     * @return spannableStringBuilder
     */
    @SuppressWarnings("all")
    @Override
    public SpannableStringBuilder build() {
        final SpannableStringBuilder sb = new SpannableStringBuilder();
        final Iterator<SpannableString> iterator = mList.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        mList.clear();
        return sb;
    }

    /**
     * Set this spannableString to TextView
     *
     * @param textView textView
     */
    @Override
    public void into(TextView textView) {
        final SpannableStringBuilder strBuilder = build();
        textView.setText(strBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * Set this spannableString to TextView
     *
     * @param activity activity
     * @param resId    resId
     */
    @Override
    public void into(Activity activity, @IdRes int resId) {
        if (activity != null) {
            View view = activity.findViewById(resId);
            if (view != null && view instanceof TextView)
                this.into((TextView) view);
        }
    }

    /**
     * Append text
     *
     * @param str string
     * @return this
     */
    @Override
    public ISpannable appendText(CharSequence str) {
        if (bNewLineAdd && mList.size() != 0)
            appendNewLine();

        mList.add(new SpannableString(str));
        return this;
    }

    /**
     * Append text
     *
     * @param str         str
     * @param withNewLine whether need new line after append a string.
     *                    If this is first line, new line will not be added.
     * @return this
     */
    @Override
    public ISpannable appendText(CharSequence str, boolean withNewLine) {
        if (withNewLine && mList.size() != 0)
            appendNewLine();
        mList.add(new SpannableString(str));
        return this;
    }

    /**
     * Append new line
     *
     * @return this
     */
    @Override
    public ISpannable appendNewLine() {
        return appendText(NEW_LINE, false);
    }

    /**
     * Set last string textColor
     *
     * @param color color
     * @return this
     */
    @Override
    public ISpannable color(@ColorInt int color) {
        applyStyle(new ForegroundColorSpan(color));
        return this;
    }

    /**
     * Set last string textColor
     *
     * @param colorRes colorRes
     * @return this
     */
    @Override
    public ISpannable colorRes(@ColorRes int colorRes) {
        int value = ContextCompat.getColor(context, colorRes);
        return color(value);
    }

    /**
     * Set last string backgroundColor
     *
     * @param color color
     * @return this
     */
    @Override
    public ISpannable bgColor(@ColorInt int color) {
        applyStyle(new BackgroundColorSpan(color));
        return this;
    }

    /**
     * Set last string backgroundColor
     *
     * @param colorRes colorRes
     * @return this
     */
    @Override
    public ISpannable bgColorRes(@ColorRes int colorRes) {
        int value = ContextCompat.getColor(context, colorRes);
        return bgColor(value);
    }

    /**
     * Bold the last string
     *
     * @return this
     */
    @Override
    public ISpannable bold() {
        applyStyle(new StyleSpan(Typeface.BOLD));
        return this;
    }

    /**
     * Italic the last string
     *
     * @return this
     */
    @Override
    public ISpannable italic() {
        applyStyle(new StyleSpan(Typeface.ITALIC));
        return this;
    }

    /**
     * Underline the last string
     *
     * @return this
     */
    @Override
    public ISpannable underline() {
        applyStyle(new UnderlineSpan());
        return this;
    }

    /**
     * Make a strikeThrough to the last string
     *
     * @return this
     */
    @Override
    public ISpannable strikeThrough() {
        applyStyle(new StrikethroughSpan());
        return this;
    }

    /**
     * Make the last string superScript
     *
     * @return this
     */
    @Override
    public ISpannable superScript() {
        applyStyle(new SuperscriptSpan());
        return this;
    }

    /**
     * Make the last string subScript
     *
     * @return this
     */
    @Override
    public ISpannable subScript() {
        applyStyle(new SubscriptSpan());
        return this;
    }

    /**
     * Make the last string superScript
     *
     * @param textSizeRatio textSize ratio
     * @return this
     */
    @Override
    public ISpannable superScript(float textSizeRatio) {
        superScript();
        relativeTextSize(textSizeRatio);
        return this;
    }

    /**
     * Make the last string subScript
     *
     * @param textSizeRatio textSize ratio
     * @return this
     */
    @Override
    public ISpannable subScript(float textSizeRatio) {
        subScript();
        relativeTextSize(textSizeRatio);
        return this;
    }

    /**
     * Set the text size
     *
     * @param px px
     * @return this
     */
    @Override
    public ISpannable textSizePx(int px) {
        applyStyle(new AbsoluteSizeSpan(px));
        return this;
    }

    /**
     * Set the text size
     *
     * @param dp dp
     * @return this
     */
    @Override
    public ISpannable textSizeDp(int dp) {
        applyStyle(new AbsoluteSizeSpan(dp, true));
        return this;
    }

    /**
     * Set the text size
     *
     * @param sp sp
     * @return this
     */
    @Override
    public ISpannable textSizeSp(int sp) {
        float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        int px = (int) (sp * scaleDensity + 0.5);
        return textSizePx(px);
    }

    /**
     * Change the text size
     *
     * @param ratio relative text size
     * @return this
     */
    @Override
    public ISpannable relativeTextSize(@FloatRange(from = 0, to = 10) float ratio) {
        applyStyle(new RelativeSizeSpan(ratio));
        return this;
    }

    /**
     * Change the text gap
     *
     * @param ratio text gap ratio
     * @return this
     */
    @Override
    public ISpannable scaleX(@FloatRange(from = 0, to = 10) float ratio) {
        applyStyle(new ScaleXSpan(ratio));
        return this;
    }

    /**
     * Append drawable
     *
     * @param drawable drawable
     * @return this
     */
    @Override
    public ISpannable appendImage(Drawable drawable) {
        mList.add(PICTURE_PLACEHOLDER);
        applyStyle(new ImageSpan(drawable));
        return this;
    }

    /**
     * Append drawable
     *
     * @param drawableRes resource of drawable
     * @return this
     */
    @Override
    public ISpannable appendImage(@DrawableRes int drawableRes) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return appendImage(drawable);
    }

    /**
     * Append a bitmap
     *
     * @param bitmap bitmap
     * @return this
     */
    @Override
    public ISpannable appendImage(Bitmap bitmap) {
        mList.add(PICTURE_PLACEHOLDER);
        applyStyle(new ImageSpan(context, bitmap));
        return this;
    }

    /**
     * Append url text
     *
     * @param str str
     * @return this
     */
    @Override
    public ISpannable appendUrlText(String str) {
        mList.add(new SpannableString(str));
        applyStyle(new URLSpan(str));
        return this;
    }

    /**
     * Set the last string by style
     *
     * @param res resource of sytle
     * @return this
     */
    @Override
    public ISpannable textApperance(@StyleRes int res) {
        applyStyle(new TextAppearanceSpan(context, res));
        return this;
    }

    /**
     * Set a click listener
     *
     * @param listener listener
     * @return this
     */
    @Override
    public ISpannable onClick(final OnSpannableClickListener listener) {
        if (listener == null)
            return this;

        final SpannableString spannableString = mList.getLast();
        applyStyle(new ClickableString(spannableString, listener));
        return this;
    }

    /**
     * Aligment left
     *
     * @return this
     */
    @Override
    public ISpannable aligmentLeft() {
        applyStyle(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL));
        return this;
    }

    /**
     * Aligment center
     *
     * @return this
     */
    @Override
    public ISpannable aligmentCenter() {
        applyStyle(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER));
        return this;
    }

    /**
     * Aligment right
     *
     * @return this
     */
    @Override
    public ISpannable aligmentRight() {
        applyStyle(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE));
        return this;
    }

    /**
     * =========================
     * I meet a trouble such as follows. Who can help me?
     *
     * If I use that,it works well.
     *     spannableString.setSpan(new UnderlineSpan(),0,5,SPAN_EXCLUSIVE_EXCLUSIVE);
     *     spannableString.setSpan(new UnderlineSpan(),10,15,SPAN_EXCLUSIVE_EXCLUSIVE);
     *
     * But when I use that,it didn't works well. Only the last line works well. Why?
     *     Object span = new UnderlineSpan();
     *     spannableString.setSpan(span,0,5,SPAN_EXCLUSIVE_EXCLUSIVE);
     *     spannableString.setSpan(span,10,15,SPAN_EXCLUSIVE_EXCLUSIVE);
     * ========================
     */
    /**
     * Replace last spannableString by new span
     *
     * @param str   str
     * @param spans new spans attribute
     * @return this
     */
    @Override
    public ISpannable replaceRecentString(CharSequence str, SpannableFunc spans) {
        int lastIndex = 0;
        final int FLAG = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        final SpannableString spanString = mList.getLast();

        if (spans == null || spanString == null)
            return this;

        while (lastIndex != -1) {
            lastIndex = spanString.toString().indexOf(str.toString(), lastIndex);
            if (lastIndex != -1) {
                spans.apply(spanString, lastIndex, lastIndex + str.length(), FLAG);
                lastIndex += str.length();
            }
        }
        return this;
    }
}

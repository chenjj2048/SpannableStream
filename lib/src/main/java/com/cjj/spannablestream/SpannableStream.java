package com.cjj.spannablestream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.cjj.spannablestream.click.CustomLinkMovementMethod;
import com.cjj.spannablestream.color.ColorConfig;
import com.cjj.spannablestream.interfacer.IClickable;
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
    private boolean lineBreak = false;

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
     * Change configure,whether add a new line when append a string
     *
     * @param alwayLineBreak whether add a new line when use appendText() function.
     * @return this
     */
    @Override
    public ISpannable configueAlwaysLineBreak(boolean alwayLineBreak) {
        this.lineBreak = alwayLineBreak;
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
        textView.setMovementMethod(CustomLinkMovementMethod.getInstance());
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
        if (lineBreak && mList.size() != 0 && !NEW_LINE.equals(str))
            appendNewLine();

        mList.add(new SpannableString(str));
        return this;
    }

    /**
     * Append text
     *
     * @param resId resId
     * @return this
     */
    @Override
    public ISpannable appendText(@StringRes int resId) {
        String str = context.getString(resId);
        return appendText(str);
    }

    /**
     * Append new line
     *
     * @return this
     */
    @Override
    public ISpannable appendNewLine() {
        return appendText(NEW_LINE);
    }

    /**
     * Append lines
     *
     * @param count count of line break
     * @return this
     */
    public ISpannable appendNewLine(int count) {
        for (int i = 0; i < count; i++)
            appendNewLine();
        return this;
    }

    /**
     * Set last string textColor
     *
     * @param color color
     * @return this
     */
    @Override
    public ISpannable color(@ColorInt int color) {
        SpannableOperate.getDefault().color(color).build()
                .apply(mList.getLast());
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
        SpannableOperate.with(context).colorRes(colorRes).build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Set last string backgroundColor
     *
     * @param color color
     * @return this
     */
    @Override
    public ISpannable bgColor(@ColorInt int color) {
        SpannableOperate.getDefault().bgColor(color).build()
                .apply(mList.getLast());
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
        SpannableOperate.with(context).bgColorRes(colorRes).build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Bold the last string
     *
     * @return this
     */
    @Override
    public ISpannable bold() {
        SpannableOperate.getDefault().bold().build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Italic the last string
     *
     * @return this
     */
    @Override
    public ISpannable italic() {
        SpannableOperate.getDefault().italic().build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Underline the last string
     *
     * @return this
     */
    @Override
    public ISpannable underline() {
        SpannableOperate.getDefault().underline().build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Make a strikeThrough to the last string
     *
     * @return this
     */
    @Override
    public ISpannable strikeThrough() {
        SpannableOperate.getDefault().strikeThrough().build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Make the last string superScript
     *
     * @return this
     */
    @Override
    public ISpannable superScript() {
        SpannableOperate.getDefault().superScript().build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Make the last string subScript
     *
     * @return this
     */
    @Override
    public ISpannable subScript() {
        SpannableOperate.getDefault().subScript().build()
                .apply(mList.getLast());
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
        SpannableOperate.getDefault().superScript(textSizeRatio).build()
                .apply(mList.getLast());
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
        SpannableOperate.getDefault().subScript(textSizeRatio).build()
                .apply(mList.getLast());
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
        SpannableOperate.getDefault().textSizePx(px).build()
                .apply(mList.getLast());
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
        SpannableOperate.getDefault().textSizeDp(dp).build()
                .apply(mList.getLast());
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
        SpannableOperate.with(context).textSizeSp(sp).build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Change the text size
     *
     * @param ratio relative text size
     * @return this
     */
    @Override
    public ISpannable relativeTextSize(float ratio) {
        SpannableOperate.getDefault().relativeTextSize(ratio).build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Change the text gap
     *
     * @param ratio text gap ratio
     * @return this
     */
    @Override
    public ISpannable scaleX(float ratio) {
        SpannableOperate.getDefault().scaleX(ratio).build()
                .apply(mList.getLast());
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
        final SpannableString str = mList.getLast();
        str.setSpan(new ImageSpan(drawable), 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        final SpannableString str = mList.getLast();
        str.setSpan(new ImageSpan(context, bitmap), 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    /**
     * Append url text
     *
     * @param urlAddress url
     * @return this
     */
    @Override
    public ISpannable appendUrlText(String urlAddress) {
        mList.add(new SpannableString(urlAddress));
        final SpannableString str = mList.getLast();
        str.setSpan(new URLSpan(urlAddress), 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return this;
    }

    /**
     * Set the last string by style
     *
     * @param resId resource of sytle
     * @return this
     */
    @Override
    public ISpannable textApperance(@StyleRes int resId) {
        SpannableOperate.with(context).textApperance(resId).build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Set a click listener
     *
     * @param listener listener
     * @return this
     */
    @Override
    public ISpannable onClick(IClickable.OnSpannableClickListener listener) {
        SpannableOperate.getDefault().onClick(listener).build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * @param colorConfig colorConfig
     * @param listener    listener
     * @return this
     */
    @Override
    public ISpannable onClick(ColorConfig colorConfig, IClickable.OnSpannableClickListener listener) {
        SpannableOperate.getDefault().onClick(colorConfig, listener).build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Aligment left
     *
     * @return this
     */
    @Override
    public ISpannable aligmentLeft() {
        SpannableOperate.getDefault().aligmentLeft().build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Aligment center
     *
     * @return this
     */
    @Override
    public ISpannable aligmentCenter() {
        SpannableOperate.getDefault().aligmentCenter().build()
                .apply(mList.getLast());
        return this;
    }

    /**
     * Aligment right
     *
     * @return this
     */
    @Override
    public ISpannable aligmentRight() {
        SpannableOperate.getDefault().aligmentRight().build()
                .apply(mList.getLast());
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
     * @param str              str
     * @param spannableOperate new spans attribute
     * @return this
     */
    @Override
    public ISpannable replaceString(CharSequence str, SpannableOperate spannableOperate) {
        internalReplace(mList.getLast(), str, spannableOperate);
        return this;
    }

    /**
     * Replace all the string in List.
     *
     * @param str              str
     * @param spannableOperate spannableOperate
     * @return this
     */
    @Override
    public ISpannable replaceAllString(CharSequence str, SpannableOperate spannableOperate) {
        for (SpannableString s : mList)
            internalReplace(s, str, spannableOperate);
        return this;
    }

    private void internalReplace(SpannableString mSpannableString, CharSequence targetString, SpannableOperate mSpannableOperate) {
        int lastIndex = 0;
        final int FLAG = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

        if (mSpannableOperate == null || mSpannableString == null || TextUtils.isEmpty(targetString))
            return;

        while (lastIndex != -1) {
            lastIndex = mSpannableString.toString().indexOf(targetString.toString(), lastIndex);
            if (lastIndex != -1) {
                mSpannableOperate.apply(mSpannableString, lastIndex, lastIndex + targetString.length(), FLAG);
                lastIndex += targetString.length();
            }
        }
    }
}

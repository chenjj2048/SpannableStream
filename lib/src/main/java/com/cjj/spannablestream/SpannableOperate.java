package com.cjj.spannablestream;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.UnderlineSpan;

import com.cjj.spannablestream.click.ClickableSpanWrapper;
import com.cjj.spannablestream.click.OnSpannableClickListener;
import com.cjj.spannablestream.color.ColorConfig;
import com.cjj.spannablestream.interfacer.ISpanOperate;

/**
 * Created on 2016/8/11
 *
 * @author chenjj2048
 */
public abstract class SpannableOperate implements ISpanOperate.Collection<SpannableOperate>, ISpanOperate.Build {
    private static final SpannableOperate DEFAULT_INSTANCE = SpannableOperate.with(null);
    private final SpannableOperate mParent;
    private final Context context;

    /**
     * Initialize new instance by SpannableOperate.with(context)
     *
     * @param context context
     */
    private SpannableOperate(Context context) {
        this.context = context;
        this.mParent = null;
    }

    /**
     * 装饰者模式
     *
     * @param parent parent
     */
    private SpannableOperate(SpannableOperate parent) {
        this.context = parent.context;
        this.mParent = parent;
    }

    /**
     * Use SpannableOperate.with(context) instead of new SpannableOperate()
     *
     * @param context context
     * @return new instance
     */
    public static SpannableOperate with(Context context) {
        return new SpannableOperate(context) {
            @Override
            public Object getCurrentSpan() {
                return null;
            }
        };
    }

    /**
     * @return default instance, if you dont't need use context.
     * Otherwise, use SpannableOperate.with(context) instead
     */
    public static SpannableOperate getDefault() {
        return DEFAULT_INSTANCE;
    }

    @Override
    public ISpanOperate.Build build() {
        return this;
    }

    /**
     * Set string span
     *
     * @param spannableString spannableString
     * @param start           start
     * @param end             end
     * @param flag            flag
     */
    @Override
    public void apply(SpannableString spannableString, int start, int end, int flag) {
        if (spannableString == null)
            return;

        /**
         * Setting parent attributes first
         */
        if (mParent != null)
            mParent.apply(spannableString, start, end, flag);

        /**
         * Setting current attributes
         */
        Object mSpan = getCurrentSpan();
        if (mSpan == null) return;

        if (mSpan instanceof ClickableSpanWrapper) {
            ClickableSpanWrapper mClickableSpan = (ClickableSpanWrapper) mSpan;
            mClickableSpan.setClickText(spannableString, start, end);
        }
        spannableString.setSpan(mSpan, start, end, flag);
    }

    /**
     * Set string span
     *
     * @param spannableString spannableString
     */
    @Override
    public void apply(SpannableString spannableString) {
        if (spannableString != null)
            apply(spannableString, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * @return count of operate before this class
     */
    @Override
    public int getOperateCount() {
        return (mParent == null) ? 0 : mParent.getOperateCount() + 1;
    }

    /**
     * ========================
     * Color Operate
     * ========================
     */

    @Override
    public SpannableOperate color(@ColorInt final int color) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new ForegroundColorSpan(color);
            }
        };
    }

    @Override
    public SpannableOperate colorRes(@ColorRes final int colorRes) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                int color = ContextCompat.getColor(context, colorRes);
                return new ForegroundColorSpan(color);
            }
        };
    }

    @Override
    public SpannableOperate bgColor(@ColorInt final int color) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new BackgroundColorSpan(color);
            }
        };
    }

    @Override
    public SpannableOperate bgColorRes(@ColorRes final int colorRes) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                int color = ContextCompat.getColor(context, colorRes);
                return new BackgroundColorSpan(color);
            }
        };
    }

    /**
     * ========================
     * TextSize Operate
     * ========================
     */

    @Override
    public SpannableOperate textSizePx(final int px) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new AbsoluteSizeSpan(px);
            }
        };
    }

    @Override
    public SpannableOperate textSizeDp(final int dp) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new AbsoluteSizeSpan(dp, true);
            }
        };
    }

    @Override
    public SpannableOperate textSizeSp(final int sp) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
                int px = (int) (sp * scaleDensity + 0.5);
                return new AbsoluteSizeSpan(px);
            }
        };
    }

    @Override
    public SpannableOperate relativeTextSize(final float ratio) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new RelativeSizeSpan(ratio);
            }
        };
    }

    @Override
    public SpannableOperate scaleX(final float ratio) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new ScaleXSpan(ratio);
            }
        };
    }

    /**
     * ========================
     * TypeFace Operate
     * ========================
     */

    @Override
    public SpannableOperate bold() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new StyleSpan(Typeface.BOLD);
            }
        };
    }

    @Override
    public SpannableOperate italic() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new StyleSpan(Typeface.ITALIC);
            }
        };
    }

    @Override
    public SpannableOperate underline() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new UnderlineSpan();
            }
        };
    }

    @Override
    public SpannableOperate strikeThrough() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new StrikethroughSpan();
            }
        };
    }

    /**
     * ============================
     * Super & Sub Script Operate
     * ============================
     */

    @Override
    public SpannableOperate superScript() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new SuperscriptSpan();
            }
        };
    }

    @Override
    public SpannableOperate subScript() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new SubscriptSpan();
            }
        };
    }

    @Override
    public SpannableOperate superScript(float textSizeRatio) {
        return this.superScript().relativeTextSize(textSizeRatio);
    }

    @Override
    public SpannableOperate subScript(float textSizeRatio) {
        return this.subScript().relativeTextSize(textSizeRatio);
    }

    /**
     * ========================
     * Aligment Operate
     * ========================
     */

    @Override
    public SpannableOperate aligmentCenter() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER);
            }
        };
    }

    @Override
    public SpannableOperate aligmentLeft() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL);
            }
        };
    }

    @Override
    public SpannableOperate aligmentRight() {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE);
            }
        };
    }

    /**
     * ========================
     * Other Operate
     * ========================
     */

    @Override
    public SpannableOperate textApperance(@StyleRes final int resId) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new TextAppearanceSpan(context, resId);
            }
        };
    }

    /**
     * The sequence of onClick() using will affect the display effects.
     * Such as this.color().onClick() and this.onClick().color() they are diffenet.
     * Just try it to gain what you want.
     * <p>
     *
     * @param listener listener
     * @return this
     */
    @Override
    public SpannableOperate onClick(OnSpannableClickListener listener) {
        return onClick(ColorConfig.getDefault(), listener);
    }

    /**
     * @param colorConfig colorConfig
     * @param listener    listener
     * @return this
     */
    @Override
    public SpannableOperate onClick(final ColorConfig colorConfig, final OnSpannableClickListener listener) {
        return new SpannableOperate(this) {
            @Override
            public Object getCurrentSpan() {
                return new ClickableSpanWrapper(colorConfig, listener);
            }
        };
    }
}

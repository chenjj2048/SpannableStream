package com.cjj.demo.spannablestream;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.spannablestream.SpannableOperate;
import com.cjj.spannablestream.SpannableStream;
import com.cjj.spannablestream.click.SimpleSpannableClickListener;
import com.cjj.spannablestream.color.ColorConfig;
import com.cjj.spannablestream.interfacer.IClickable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextView1();
        initTextView2();
    }

    private void initTextView1() {
        TextView mTextView1 = (TextView) findViewById(R.id.textView1);
        SpannableStream.with(this)
                .appendText("Example 1").aligmentCenter().bold().underline().relativeTextSize(1.5f).appendNewLine()
                .appendText("ForegroundColor").colorRes(android.R.color.holo_red_light).appendNewLine()
                .appendText("BackgroundColor").bgColorRes(android.R.color.holo_orange_light).color(Color.WHITE).appendNewLine(2)
                .appendText("Bold,").bold()
                .appendText("Italic,").italic()
                .appendText("UnderLine,").underline()
                .appendText("StrikeThrough").strikeThrough().appendNewLine().appendNewLine()
                .appendText("Plain").appendText("SuperScript").superScript().appendText("SubScript").subScript().appendNewLine(2)
                .appendText("40dp ").textSizeDp(40)
                .appendText("40px ").textSizePx(40)
                .appendText("40sp").textSizeSp(40).appendNewLine(2)
                .appendText("1.0x TextSize")
                .appendText("1.5x TextSize").relativeTextSize(1.5f).appendNewLine()
                .appendText("ScaleX").scaleX(4).appendNewLine()
                .appendImage(R.mipmap.ic_launcher).appendText("ImageSpan").colorRes(android.R.color.holo_green_light).appendNewLine()
                .appendUrlText("http://github.com").appendNewLine()
                .appendText("Aligment Left").aligmentLeft().appendNewLine()
                .appendText("Aligment Middle").aligmentCenter().appendNewLine()
                .appendText("Aligment Right").aligmentRight().appendNewLine(2)
                .appendNewLine(5)
                .into(mTextView1);
    }

    private void initTextView2() {
        SpannableOperate mReplaceAttributes = SpannableOperate.with(this)
                .italic()
                .relativeTextSize(2f)
                .onClick(ColorConfig.getDefault()
                                .colorNormal(this, android.R.color.holo_blue_light)
                                .bgColorNormal(Color.TRANSPARENT)
                                .colorPressed(Color.WHITE)
                                .bgColorPressed(this, android.R.color.holo_red_light)
                        , new SimpleSpannableClickListener() {
                            @Override
                            public void onSpannableItemClick(View widget, CharSequence str) {
                                Toast.makeText(widget.getContext(), str, Toast.LENGTH_SHORT).show();
                            }
                        });
        SpannableStream.with(this)
                .color(Color.RED)
                .colorRes(android.R.color.holo_red_light)
                .bgColor(Color.WHITE)
                .bgColorRes(android.R.color.white)
                .into(mTextView);


        SpannableStream.with(this)
                .configueAlwaysLineBreak(true)
                .appendText("Example 2").aligmentCenter().bold().relativeTextSize(1.5f).underline()
                .appendText("You can click here")
                .onClick(ColorConfig.getDefault()
                                .colorPressed(this, android.R.color.holo_green_light)
                                .colorNormal(this, android.R.color.holo_red_light)
                        , new IClickable.OnSpannableClickListener() {
                            @Override
                            public void onSpannableItemClick(View widget, CharSequence str) {
                            }

                            @Override
                            public void onPressedStateChanged(boolean isPressed) {
                                Toast.makeText(MainActivity.this, "Finger " + (isPressed ? "Down" : "Up"), Toast.LENGTH_SHORT).show();
                            }
                        })
                .aligmentCenter().relativeTextSize(1.5f)
                .appendText(R.string.text)
                .replaceString("text", mReplaceAttributes)
                .appendNewLine(20)
                .into(this, R.id.textView2);
    }
}

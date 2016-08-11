package com.cjj.demo.spannablestream;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.cjj.spannablestream.SpannableStream;
import com.cjj.spannablestream.SpannableFunc;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView1;
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindTextView();

        initTextView1();
        initTextView2();
    }

    private void bindTextView() {
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView2);
    }

    private void initTextView1() {
        SpannableStream.with(this)
                .appendText("Example 1").aligmentCenter().bold().underline().appendNewLine()
                .appendText("ForegroundColor").color(Color.RED).appendNewLine()
                .appendText("BackgroundColor").bgColor(Color.YELLOW).appendNewLine()
                .appendText("Bold,").bold()
                .appendText("Italic,").italic()
                .appendText("UnderLine,").underline()
                .appendText("StrikeThrough").strikeThrough()
                .appendText("Plain", true).appendText("SuperScript").superScript().appendText("SubScript").subScript().appendNewLine()
                .appendText("40dp").textSizeDp(40)
                .appendText("40px").textSizePx(40)
                .appendText("40sp").textSizeSp(40)
                .appendText("1.0x TextSize", true)
                .appendText("1.5x TextSize").relativeTextSize(1.5f)
                .appendText("ScaleX", true).scaleX(4).appendNewLine()
                .appendImage(R.mipmap.ic_launcher).appendText("ImageSpan").appendNewLine()
                .appendUrlText("http://www.google.com")
                .appendText("Aligment Left", true).aligmentLeft()
                .appendText("Aligment Middle", true).aligmentCenter()
                .appendText("Aligment Right", true).aligmentRight()
                .appendNewLine()
                .into(mTextView1);
    }

    private void initTextView2() {
        SpannableStringBuilder sb = SpannableStream.with(this)
                .configueAddNewLineAfterInsert(true)
                .appendText("Example 2").aligmentCenter().bold().underline()
                .appendText("You can replace the style of all the specified text in the text, such as replacing the \"a\" in this paragraph.")
                .replaceRecentString("ac", SpannableFunc.getDefault().underline().color(Color.BLUE))
                .build();

        mTextView2.setText(sb);
    }

}

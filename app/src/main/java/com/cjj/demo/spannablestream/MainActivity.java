package com.cjj.demo.spannablestream;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cjj.spannablestream.SpannableStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        if (textView == null) return;

        Context context = MainActivity.this;

        SpannableStream.with(context)
                .text("abcdefg").color(Color.RED)
                .newLine()
                .text("1234567").bgColor(Color.GREEN).color(Color.WHITE)
                .into(textView);
    }
}

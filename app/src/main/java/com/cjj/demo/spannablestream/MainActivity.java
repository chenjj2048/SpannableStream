package com.cjj.demo.spannablestream;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.spannablestream.SimpleSpannableClickListener;
import com.cjj.spannablestream.SpannableStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        if (textView == null) return;

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                final Context context = MainActivity.this;
                SpannableStream.with(context)
                        .text("abcdef")
                        .text("开始中国啊啊")
                        .onClick(new SimpleSpannableClickListener() {
                            @Override
                            public void onStringClick(String string) {
                                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .newLine()
                        .text("bbc")
                        .onClick(new SimpleSpannableClickListener() {
                            @Override
                            public void onStringClick(String string) {
                                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .image(R.mipmap.ic_launcher)
                        .onClick(new SimpleSpannableClickListener() {
                            @Override
                            public void onImageClick() {
                                Toast.makeText(context, "图片呗点击", Toast.LENGTH_SHORT).show();
                            }

                        })

                        .into(textView);
            }
        });

    }
}

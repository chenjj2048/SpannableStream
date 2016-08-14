#SpannableStream
A library for you to use SpannableString easyly.

###Screenshot
1
2


#Example
--------
You can gain the app demo here.

To achieve the effect in first screenshot, you can use code as follows:
```java
SpannableStream.with(context)
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

```

To achieve the effect in second screenshot, you can use code as follows:
```java
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
```



#License
--------

    Copyright 2015 chenjj2048

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
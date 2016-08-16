#SpannableStream
A library for you to use SpannableString easily.

##Example
You can gain the [app demo](http://fir.im/tzl7) here.

###Demo1
![snapshot 1](https://raw.githubusercontent.com/chenjj2048/SpannableStream/master/snap/1.gif)

To achieve the effect, you can use code as follows:
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
###Demo2
![snapshot 1](https://raw.githubusercontent.com/chenjj2048/SpannableStream/master/snap/2.gif)

To achieve the effect, you can use code as follows:
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

##Supported Operation

###1.Append Operation

* appendText(CharSequence str);
* appendText(@StringRes int resId);

* appendNewLine();
* appendNewLine(int count);

* appendImage(Drawable drawable);
* appendImage(@DrawableRes int drawableRes);
* appendImage(Bitmap bitmap);

* appendUrlText(String str);

###2. Color Operation

* color(@ColorInt int color);
* colorRes(@ColorRes int colorRes);
* bgColor(@ColorInt int color);
* bgColorRes(@ColorRes int colorRes);
  

###3. TextSize Operation

  Just choose one function to use in `textSizePx()`, `textSizeDp()`, `textSizeSp()` and `relativeTextSize()`.

* textSizePx(int px);
* textSizeDp(int dp);
* textSizeSp(int sp);
* relativeTextSize(float ratio);
* scaleX(float ratio);

###4. TypeFace Operation

* bold();
* italic();
* underline();
* strikeThrough();

###5. SubScript and UpperScript

* superScript();
* subScript();
* superScript(float textSizeRatio);
* subScript(float textSizeRatio);
    

###6. Aligment Operation

* aligmentCenter();
* aligmentLeft();
* aligmentRight();
    

###7. Using R.style.xxx to set the text style

* textApperance(@StyleRes int resId);

You can set the textView attribute by using `textApperance(R.style.xxx)`. Just as follows:

```java
 SpannableStream.with(context)
                .appendText("abcdefg")
                .textApperance(R.style.text_style)
                .into(mTextView);
```

###8. Click Operation

When you want to achieve click operation and make the text color changing with pressing operation,you can use the `onClick()` function with two parameters. Otherwise, just use the `onClick()` function with one parameter.

* onClick(IClickable.OnSpannableClickListener listener);
* onClick(ColorConfig colorConfig, IClickable.OnSpannableClickListener listener);

Here is the listener interface. You can use `SimpleSpannableClickListener` to simplify the code for writing the listener.

```java
 interface OnSpannableClickListener {

        void onSpannableItemClick(View textView, CharSequence str);

        void onPressedStateChanged(boolean isPressed);
    }
```

When you want to make the clicked text changing color, You should use `ColorConfig` class. Just use like that:
```java
  ColorConfig.getDefault()
                .colorNormal(Color.WHITE)
                .colorPressed(Color.BLUE)
                .bgColorNormal(Color.RED)
                .bgColorPressed(Color.GREEN)
                .colorNormal(context, android.R.color.white)
                .colorPressed(context, android.R.color.holo_blue_light)
                .bgColorNormal(context, android.R.color.holo_red_light)
                .bgColorPressed(context, android.R.color.holo_green_light);
```

###9. Replace Operation

* replaceString(CharSequence str, SpannableOperate spanOperate);
* replaceAllString(CharSequence str, SpannableOperate spannableOperate);

You can use `SpannableOperate.with(context)` or `SpannableOperate.getDefault()` to create the instance. The difference between them was whether you need context at the next operation. Some operations will throw `NullPointException` if you didn't use `SpannableOperate.with(context)`, such as `.colorRes()`, `.textSizeSp()` and so on.

 `SpannableOperate` is used just like that as the new attribute after replacement.
```java
 SpannableOperate.getDefault()
                .underline()
                .color(Color.RED)
                .italic();
```

##Todo
- [ ]Add text background drawable operation;
- [ ]support custom span operation;


#License

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
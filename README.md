# UnderLineLinearLayout
// 这是一个简单的时间轴实现(LinearLayout)</br>



## 效果图:</br>
![image](https://github.com/razerdp/UnderLineLinearLayout/blob/master/img/underlinelayout.gif)
![image](https://github.com/razerdp/UnderLineLinearLayout/blob/master/img/underlinelayout_horizontal.gif)
</br>

----------


# 更新日志：
### 2016-02-26:添加 `setWillNotDraw(false);`
demo由于设置了颜色，所以ViewGroup会调用onDraw方法，在没有颜色的情况下，如果linearlayout的divider为空，是不会执行onDraw方法的，这里把它设定为执行。

----------

### 2016-01-26:在忙完一天的工作后，忽然想起了这个东东，于是这次一并做了

这次更新了一个前面说过的目标：lineGravity，这一次我们可以通过line_gravity属性来定义我们时间轴的位置了-V-，喜欢哪里点哪里</br>

**不过因为针对LinearLayout，所以有一定的限定：**

 - 当方向为vertical的时候，我们只能定义时间轴的位置在Left/Middle/Right，至于具体在什么位置，请设置**line_margin_side**属性
 - 当方向为horizontal的时候，我们只能定义时间轴的位置在Top/Middle/Bottom，至于具体在什么位置，请设置**line_margin_side**属性
 - 其余情况，比如vertical的方向，但我们设置了时间轴为top，这豪无意义对吧，那么其余非法情况我将其默认为0，也就是垂直方向默认为left，水平方向默认为top。

### 效果图：</br>
![image](https://github.com/razerdp/UnderLineLinearLayout/blob/master/img/underlinearlayout_with_gravity.gif)

----------


### 2016-01-25
增加了水平方向的timeLine，期望后面有时间可以做上gravity，也就是可以定义timeLine所处方向，最终目标是继承viewGroup，期望可以针对任何布局。


----------


## 使用方法：</br>
 因为继承的LinearLayout，所以请直接addView吧-V-</br>
### 各属性值：
 attrs.xml:</br>
```html
    <declare-styleable name="UnderLineLinearLayout">
        <!--时间轴偏移值-->
        <attr name="line_margin_side" format="dimension"/>
        <!--时间轴动态调整值-->
        <attr name="line_dynamic_dimen" format="dimension"/>
        <!--线宽-->
        <attr name="line_stroke_width" format="dimension"/>
        <!--线的颜色-->
        <attr name="line_color" format="color"/>
        <!--点的大小-->
        <attr name="point_size" format="dimension"/>
        <!--点的颜色-->
        <attr name="point_color" format="color"/>
        <!--图标-->
        <attr name="icon_src" format="reference"/>
           <!--时间轴的gravity-->
                <!--the gravity of the timeline-->
                <attr name="line_gravity">
                    <enum name="Left" value="2"/>
                    <enum name="Right" value="4"/>
                    <enum name="Middle" value="0"/>
                    <enum name="Top" value="1"/>
                    <enum name="Bottom" value="3"/>
                </attr>
    </declare-styleable>
```
## 解析博文：</br>
http://blog.csdn.net/mkfrank/article/details/50557221 </br>
or</br>
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0121/3902.html
### 最后打个小广告：这是我另一个正在维护的项目，一个方便使用popupWindow的顶级抽象类，期望可以方便的实现popupWindow的各种效果，欢迎fork和star以及提出意见或建议
https://github.com/razerdp/BasePopup
## LICENSE
许可？？？。。。。。。。纯属交流，请随便用吧- -
 

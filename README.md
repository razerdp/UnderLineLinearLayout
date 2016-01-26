# UnderLineLinearLayout
// 这是一个简单的时间轴实现(LinearLayout)</br>
##效果图:</br>
![image](https://github.com/razerdp/UnderLineLinearLayout/blob/master/img/underlinelayout.gif)
![image](https://github.com/razerdp/UnderLineLinearLayout/blob/master/img/underlinelayout_horizontal.gif)
</br>
##更新日志：
###2016-01-25:增加了水平方向的timeLine，期望后面有时间可以做上gravity，也就是可以定义timeLine所处方向，最终目标是继承viewGroup，期望可以针对任何布局。
##使用方法：</br>
 因为继承的LinearLayout，所以请直接addView吧-V-</br>
###各属性值：
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
    </declare-styleable>
```
##解析博文：</br>
http://blog.csdn.net/mkfrank/article/details/50557221 </br>
or</br>
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0121/3902.html
###最后打个小广告：这是我另一个正在维护的项目，一个方便使用popupWindow的顶级抽象类，期望可以方便的实现popupWindow的各种效果，欢迎fork和star以及提出意见或建议
https://github.com/razerdp/BasePopup
##LICENSE
许可？？？。。。。。。。纯属交流，请随便用吧- -
 

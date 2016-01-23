# UnderLineLinearLayout
// 这是一个简单的时间轴实现(LinearLayout)</br>
##效果图:</br>
![image](https://github.com/razerdp/UnderLineLinearLayout/blob/master/img/underlinelayout.gif)
</br>
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
http://blog.csdn.net/mkfrank/article/details/50557221

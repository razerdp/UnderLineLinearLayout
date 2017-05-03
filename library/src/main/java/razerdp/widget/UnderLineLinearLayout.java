package razerdp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by 大灯泡 on 2016/1/21.
 * 简易带有时间轴的linearlayout
 */
public class UnderLineLinearLayout extends LinearLayout {
    //=============================================================line gravity常量定义
    public static final int GRAVITY_LEFT = 2;
    public static final int GRAVITY_RIGHT = 4;
    public static final int GRAVITY_MIDDLE =0;
    public static final int GRAVITY_TOP = 1;
    public static final int GRAVITY_BOTTOM = 3;
    //=============================================================元素定义
    private Bitmap mIcon;
    //line location
    private int lineMarginSide;
    private int lineDynamicDimen;
    //line property
    private int lineStrokeWidth;
    private int lineColor;
    //point property
    private int pointSize;
    private int pointColor;

    //=============================================================paint
    private Paint linePaint;
    private Paint pointPaint;
    //=============================================================其他辅助参数
    //第一个点的位置
    private int firstX;
    private int firstY;
    //最后一个图的位置
    private int lastX;
    private int lastY;
    //默认垂直
    private int curOrientation = VERTICAL;

    //line gravity(默认垂直的左边)
    private int lineGravity = GRAVITY_LEFT;

    private Context mContext;

    //开关
    private boolean drawLine = true;

    private int rootLeft;
    private int rootMiddle;
    private int rootRight;
    private int rootTop;
    private int rootBottom;
    //参照点
    private int sideRelative;

    public UnderLineLinearLayout(Context context) {
        this(context, null);
    }

    public UnderLineLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderLineLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.UnderLineLinearLayout);
        lineMarginSide = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_margin_side, 10);
        lineDynamicDimen = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_dynamic_dimen, 0);
        lineStrokeWidth = attr.getDimensionPixelOffset(R.styleable.UnderLineLinearLayout_line_stroke_width, 2);
        lineColor = attr.getColor(R.styleable.UnderLineLinearLayout_line_color, 0xff3dd1a5);
        pointSize = attr.getDimensionPixelSize(R.styleable.UnderLineLinearLayout_point_size, 8);
        pointColor = attr.getColor(R.styleable.UnderLineLinearLayout_point_color, 0xff3dd1a5);
        lineGravity = attr.getInt(R.styleable.UnderLineLinearLayout_line_gravity, GRAVITY_LEFT);

        int iconRes = attr.getResourceId(R.styleable.UnderLineLinearLayout_icon_src, R.drawable.ic_ok);
        BitmapDrawable temp = (BitmapDrawable) context.getResources().getDrawable(iconRes);
        if (temp != null) mIcon = temp.getBitmap();

        curOrientation = getOrientation();
        attr.recycle();
        setWillNotDraw(false);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineStrokeWidth);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setDither(true);
        pointPaint.setColor(pointColor);
        pointPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateSideRelative();
        if (drawLine) {
            drawTimeLine(canvas);
        }
    }

    private void calculateSideRelative() {
        rootLeft = getLeft();
        rootTop = getTop();
        rootRight = getRight();
        rootBottom = getBottom();
        if (curOrientation == VERTICAL) rootMiddle = (rootLeft + rootRight) >> 1;
        if (curOrientation == HORIZONTAL) rootMiddle = (rootTop + rootBottom) >> 1;

        boolean isCorrect=(lineGravity==GRAVITY_MIDDLE||(lineGravity+curOrientation)%2!=0);
        if (isCorrect){
            switch (lineGravity){
                case GRAVITY_TOP:
                    sideRelative=rootTop;
                    break;
                case GRAVITY_BOTTOM:
                    sideRelative=rootBottom;
                    break;
                case GRAVITY_LEFT:
                    sideRelative=rootLeft;
                    break;
                case GRAVITY_RIGHT:
                    sideRelative=rootRight;
                    break;
                case GRAVITY_MIDDLE:
                    sideRelative=rootMiddle;
                    break;
            }
        }else {
            sideRelative=0;
        }
    }

    private void drawTimeLine(Canvas canvas) {
        int childCount = getChildCount();

        if (childCount > 0) {
            //大于1，证明至少有2个，也就是第一个和第二个之间连成线，第一个和最后一个分别有点/icon
            if (childCount > 1) {
                switch (curOrientation) {
                    case VERTICAL:
                        drawFirstChildViewVertical(canvas);
                        drawLastChildViewVertical(canvas);
                        drawBetweenLineVertical(canvas);
                        break;
                    case HORIZONTAL:
                        drawFirstChildViewHorizontal(canvas);
                        drawLastChildViewHorizontal(canvas);
                        drawBetweenLineHorizontal(canvas);
                        break;
                    default:
                        break;
                }
            }
            else if (childCount == 1) {
                switch (curOrientation) {
                    case VERTICAL:
                        drawFirstChildViewVertical(canvas);
                        break;
                    case HORIZONTAL:
                        drawFirstChildViewHorizontal(canvas);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //=============================================================Vertical Draw
    private void drawFirstChildViewVertical(Canvas canvas) {
        if (getChildAt(0) != null) {
            int top = getChildAt(0).getTop();
            //记录值
            firstX = sideRelative>=rootMiddle?(sideRelative-lineMarginSide):(sideRelative+lineMarginSide);
            firstY = top + getChildAt(0).getPaddingTop() + lineDynamicDimen;
            //画一个圆
            canvas.drawCircle(firstX, firstY, pointSize, pointPaint);
        }
    }

    private void drawLastChildViewVertical(Canvas canvas) {
        if (getChildAt(getChildCount() - 1) != null) {
            int top = getChildAt(getChildCount() - 1).getTop();
            //记录值
            lastX = (sideRelative>=rootMiddle?(sideRelative-lineMarginSide):(sideRelative+lineMarginSide)) - (mIcon
                    .getWidth() >> 1);
            lastY = top + getChildAt(getChildCount() - 1).getPaddingTop() + lineDynamicDimen;
            //画一个图
            canvas.drawBitmap(mIcon, lastX, lastY, null);
        }
    }

    private void drawBetweenLineVertical(Canvas canvas) {
        //画剩下的
        canvas.drawLine(firstX, firstY, firstX, lastY, linePaint);
        for (int i = 0; i < getChildCount() - 1; i++) {
            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                int top = getChildAt(i).getTop();
                //记录值
                int Y = top + getChildAt(i).getPaddingTop() + lineDynamicDimen;
                canvas.drawCircle(firstX, Y, pointSize, pointPaint);
            }
        }
    }

    //=============================================================Horizontal Draw
    private void drawFirstChildViewHorizontal(Canvas canvas) {
        if (getChildAt(0) != null) {
            int left = getChildAt(0).getLeft();
            //记录值
            firstX = left + getChildAt(0).getPaddingLeft() + lineDynamicDimen;
            firstY = sideRelative>=rootMiddle?(sideRelative-lineMarginSide):(sideRelative+lineMarginSide);
            //画一个圆
            canvas.drawCircle(firstX, firstY, pointSize, pointPaint);
        }
    }

    private void drawLastChildViewHorizontal(Canvas canvas) {
        if (getChildAt(getChildCount() - 1) != null) {
            int left = getChildAt(getChildCount() - 1).getLeft();
            //记录值
            lastX = left + getChildAt(getChildCount() - 1).getPaddingLeft() + lineDynamicDimen;
            lastY = (sideRelative>=rootMiddle?(sideRelative-lineMarginSide):(sideRelative+lineMarginSide)) - (mIcon
                    .getWidth() >> 1);
            //画一个图
            canvas.drawBitmap(mIcon, lastX, lastY, null);
        }
    }

    private void drawBetweenLineHorizontal(Canvas canvas) {
        //画剩下的线
        canvas.drawLine(firstX, firstY, lastX, firstY, linePaint);
        for (int i = 0; i < getChildCount() - 1; i++) {
            //画了线，就画圆
            if (getChildAt(i) != null && i != 0) {
                int left = getChildAt(i).getLeft();
                //记录值
                int x = left + getChildAt(i).getPaddingLeft() + lineDynamicDimen;
                canvas.drawCircle(x, firstY, pointSize, pointPaint);
            }
        }
    }

    //=============================================================Getter/Setter

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
        this.curOrientation = orientation;
        invalidate();
    }

    public int getLineStrokeWidth() {
        return lineStrokeWidth;
    }

    public void setLineStrokeWidth(int lineStrokeWidth) {
        this.lineStrokeWidth = lineStrokeWidth;
        invalidate();
    }

    public boolean isDrawLine() {
        return drawLine;
    }

    public void setDrawLine(boolean drawLine) {
        this.drawLine = drawLine;
        invalidate();
    }

    public Paint getLinePaint() {
        return linePaint;
    }

    public void setLinePaint(Paint linePaint) {
        this.linePaint = linePaint;
        invalidate();
    }

    public int getPointSize() {
        return pointSize;
    }

    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
        invalidate();
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
        invalidate();
    }

    public Paint getPointPaint() {
        return pointPaint;
    }

    public void setPointPaint(Paint pointPaint) {
        this.pointPaint = pointPaint;
        invalidate();
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        invalidate();
    }

    public int getLineMarginSide() {
        return lineMarginSide;
    }

    public void setLineMarginSide(int lineMarginSide) {
        this.lineMarginSide = lineMarginSide;
        invalidate();
    }

    public int getLineDynamicDimen() {
        return lineDynamicDimen;
    }

    public void setLineDynamicDimen(int lineDynamicDimen) {
        this.lineDynamicDimen = lineDynamicDimen;
        invalidate();
    }

    public Bitmap getIcon() {
        return mIcon;
    }

    public void setIcon(Bitmap icon) {
        mIcon = icon;
    }

    public void setIcon(int resId) {
        if (resId == 0) return;
        BitmapDrawable temp = (BitmapDrawable) mContext.getResources().getDrawable(resId);
        if (temp != null) mIcon = temp.getBitmap();
        invalidate();
    }

    public int getLineGravity() {
        return lineGravity;
    }

    public void setLineGravity(int lineGravity) {
        this.lineGravity = lineGravity;
        invalidate();
    }
}

package razerdp.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import razerdp.widget.UnderLineLinearLayout;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private Button addButton;
    private Button subButton;
    private Button mHorizontalButton;
    private Button lineGravityBtn;
    private Button addMarginButton;
    private Button subMarginButton;
    private TextView mCurMargin;
    private UnderLineLinearLayout mUnderLineLinearLayout;
    private static final int[] LINE_GRAVITY={UnderLineLinearLayout.GRAVITY_LEFT,UnderLineLinearLayout.GRAVITY_MIDDLE,
            UnderLineLinearLayout.GRAVITY_RIGHT};
    private static final String[] LINE_GRAVITY_STR={"LEFT","MIDDLE","RIGHT"};
    int t=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (Button) findViewById(R.id.add);
        subButton = (Button) findViewById(R.id.sub);
        lineGravityBtn= (Button) findViewById(R.id.line_gravity);
        mHorizontalButton= (Button) findViewById(R.id.horizontal);
        addMarginButton= (Button) findViewById(R.id.add_marginside);
        subMarginButton= (Button) findViewById(R.id.sub_marginside);
        mCurMargin= (TextView) findViewById(R.id.cur_marginside);
        mUnderLineLinearLayout = (UnderLineLinearLayout)
                findViewById(R.id.underline_layout);

        addButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        mHorizontalButton.setOnClickListener(this);
        addMarginButton.setOnClickListener(this);
        subMarginButton.setOnClickListener(this);
        lineGravityBtn.setOnClickListener(this);
    }

    int i = 0;

    private void addItem() {
        View v = LayoutInflater.from(this).inflate(R.layout.item_vertical, mUnderLineLinearLayout, false);
        ((TextView) v.findViewById(R.id.tx_action)).setText("this is test " + i);
        ((TextView) v.findViewById(R.id.tx_action_time)).setText("2016-01-21");
        ((TextView) v.findViewById(R.id.tx_action_status)).setText("finish");
        mUnderLineLinearLayout.addView(v);
        i++;
    }

    private void subItem() {
        if (mUnderLineLinearLayout.getChildCount() > 0) {
            mUnderLineLinearLayout.removeViews(mUnderLineLinearLayout.getChildCount() - 1, 1);
            i--;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                addItem();
                break;
            case R.id.sub:
                subItem();
                break;
            case R.id.horizontal:
                Intent intent=new Intent(this,HorizontalActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.line_gravity:
                mUnderLineLinearLayout.setLineGravity(LINE_GRAVITY[t%3]);
                lineGravityBtn.setText(LINE_GRAVITY_STR[t%3]);
                t++;
                break;
            case R.id.add_marginside:
                int curMargin=UIHelper.pxToDip(this,mUnderLineLinearLayout.getLineMarginSide());
                mUnderLineLinearLayout.setLineMarginSide(UIHelper.dipToPx(this,++curMargin));
                mCurMargin.setText("current line marginside = "+curMargin+"dp");
                break;
            case R.id.sub_marginside:
                int curMargin2=UIHelper.pxToDip(this,mUnderLineLinearLayout.getLineMarginSide());
                mUnderLineLinearLayout.setLineMarginSide(UIHelper.dipToPx(this,--curMargin2));
                mCurMargin.setText("current line marginside = "+curMargin2+"dp");
                break;
            default:
                break;
        }

    }
}

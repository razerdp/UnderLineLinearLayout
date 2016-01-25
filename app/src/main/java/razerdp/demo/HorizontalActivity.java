package razerdp.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import razerdp.widget.UnderLineLinearLayout;

public class HorizontalActivity extends AppCompatActivity {
    private Button addButton;
    private Button subButton;
    private Button mVerticalButton;
    private UnderLineLinearLayout mUnderLineLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underline_horizontal);
        addButton = (Button) findViewById(R.id.add);
        subButton = (Button) findViewById(R.id.sub);
        mVerticalButton= (Button) findViewById(R.id.vertical);
        mUnderLineLinearLayout = (UnderLineLinearLayout)
                findViewById(R.id.underline_layout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subItem();
            }
        });
        mVerticalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HorizontalActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    int i = 0;

    private void addItem() {
        View v = LayoutInflater.from(this).inflate(R.layout.item_horizontal, mUnderLineLinearLayout, false);
        ((TextView) v.findViewById(R.id.tx_action)).setText("this is test " + i);
        ((TextView) v.findViewById(R.id.tx_action_time)).setText("2016-01-21");
        mUnderLineLinearLayout.addView(v);
        i++;
    }

    private void subItem() {
        if (mUnderLineLinearLayout.getChildCount() > 0) {
            mUnderLineLinearLayout.removeViews(mUnderLineLinearLayout.getChildCount() - 1, 1);
            i--;
        }
    }
}

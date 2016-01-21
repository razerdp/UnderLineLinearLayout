package razerdp.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import razerdp.widget.UnderLineLinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private Button subButton;
    private UnderLineLinearLayout mUnderLineLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (Button) findViewById(R.id.add);
        subButton = (Button) findViewById(R.id.sub);
        mUnderLineLinearLayout = (UnderLineLinearLayout) findViewById(R.id.underline_layout);

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
    }

    int i = 0;

    private void addItem() {
        View v = LayoutInflater.from(this).inflate(R.layout.item_test, mUnderLineLinearLayout, false);
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
}

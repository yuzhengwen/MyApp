package yuzhengwen.com.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);

        CheckBox cb = new CheckBox(this);
        cb.setText("Checkbox 1");
        Button b = new Button(this);
        b.setText("Checkbox 1");

        Bundle data = getIntent().getExtras();
        TextView t = new TextView(this);
        if(data!=null)
            t.setText(data.get("Message").toString());
        cb.setId(1);
        b.setId(2);
        t.setId(3);

        RelativeLayout.LayoutParams checkboxParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        checkboxParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        checkboxParams.setMarginEnd(30);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.BELOW, cb.getId());

        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textParams.addRule(RelativeLayout.BELOW, b.getId());

        layout.addView(cb, checkboxParams);
        layout.addView(b, buttonParams);
        layout.addView(t, textParams);

        setContentView(layout);
    }

}

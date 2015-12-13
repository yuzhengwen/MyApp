package yuzhengwen.com.myapp.sql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import yuzhengwen.com.myapp.R;

public class DatActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    TextView textView;
    MyDBManager myDBManager;
    Button add, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dat);

        add = (Button) findViewById(R.id.datAddButton);
        delete = (Button) findViewById(R.id.datDeleteButton);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.datEditText);
        textView = (TextView) findViewById(R.id.datTextView);

        myDBManager = new MyDBManager(DatActivity.this, null);

        printDatabase();
    }

    public void printDatabase(){
        String s = myDBManager.getDatabaseString();
        textView.setText(s);
        editText.setText("");
    }

    @Override
    public void onClick(View v) {
        String s = editText.getText().toString();
        switch (v.getId()){
            case R.id.datAddButton:
                //Items items = new Items(editText.getText().toString());
                myDBManager.add(s);
                break;
            case R.id.datDeleteButton:
                myDBManager.delete(s);
                break;
        }
        printDatabase();
    }
}

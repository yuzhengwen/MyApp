package yuzhengwen.com.myapp.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import yuzhengwen.com.myapp.R;

public class ListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        String[] strings = {"Test1","Test2","Test3","Test4","Test5","Test6","Test7","Test8",
                "Test9"};
        ListAdapter listAdapter = new CustomAdapter(this, strings);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = String.valueOf(parent.getItemAtPosition(position));
                Toast t = Toast.makeText(ListActivity.this, s, Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }
}

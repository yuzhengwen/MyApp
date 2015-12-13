package yuzhengwen.com.myapp.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import yuzhengwen.com.myapp.R;

public class CustomAdapter extends ArrayAdapter{

    public CustomAdapter(Context context, String[] strings) {
        super(context, R.layout.custom_list_item, strings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_list_item, parent, false);
        String s = (String) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.list_item_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image);

        textView.setText(s);
        imageView.setImageResource(R.drawable.row);

        return view;
    }
}

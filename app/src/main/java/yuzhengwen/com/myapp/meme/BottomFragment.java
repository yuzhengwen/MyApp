package yuzhengwen.com.myapp.meme;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yuzhengwen.com.myapp.R;

public class BottomFragment extends Fragment{

    private TextView topText, bottomText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_fragment, container, false);

        topText = (TextView)view.findViewById(R.id.topText);
        bottomText = (TextView)view.findViewById(R.id.bottomText);

        return view;
    }

    public void setMemeText(String top, String bottom){
        topText.setText(top);
        bottomText.setText(bottom);
    }
}

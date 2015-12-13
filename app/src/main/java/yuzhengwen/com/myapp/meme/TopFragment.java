package yuzhengwen.com.myapp.meme;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import yuzhengwen.com.myapp.R;

public class TopFragment extends Fragment{

    private EditText topEditText, bottomEditText;
    private TopFragmentInterface topFragmentInterface;

    public interface TopFragmentInterface{
        void createMeme(String top, String bottom);
    }

    @Override
    public void onAttach(Activity a) {
        //on attach to activity (FragmentPage), FragmentPage will be passed as context
        super.onAttach(a);
        topFragmentInterface = (TopFragmentInterface) a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_fragment, container, false);

        topEditText = (EditText) view.findViewById(R.id.topEditText);
        bottomEditText = (EditText) view.findViewById(R.id.bottomEditText);
        final Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return view;
    }

    private void buttonClicked(View v){
        topFragmentInterface.createMeme(topEditText.getText().toString(), bottomEditText.getText().toString());
    }
}

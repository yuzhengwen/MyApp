package yuzhengwen.com.myapp.meme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import yuzhengwen.com.myapp.R;

public class FragmentPage extends AppCompatActivity implements TopFragment.TopFragmentInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_page);
    }

    @Override
    public void createMeme(String top, String bottom) {
        BottomFragment bottomFragment = (BottomFragment) getFragmentManager()
                .findFragmentById(R.id.bottomFragment);
        bottomFragment.setMemeText(top, bottom);
    }
}

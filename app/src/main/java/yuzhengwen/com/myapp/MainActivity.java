package yuzhengwen.com.myapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import yuzhengwen.com.myapp.list.ListActivity;
import yuzhengwen.com.myapp.meme.FragmentPage;
import yuzhengwen.com.myapp.sql.DatActivity;

public class MainActivity extends AppCompatActivity{

    MediaPlayer player;
    static boolean fullscreen = true;
    static boolean navBarTint = true;
    MyService myService;
    boolean isBound = false;

    //notification
    final int notificationId = 9750432;
    NotificationCompat.Builder n = new NotificationCompat.Builder(this);

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyLocalBinder binder = (MyService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound= false;
        }
    };

    public void showTime(View view){
        String time = myService.getTime();
        TextView text = (TextView)findViewById(R.id.tiime);
        text.setText(time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hello");
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //notification
        n.setAutoCancel(true);

        setItems();
        player = MediaPlayer.create(this, R.raw.sound);

        Intent i = new Intent(this, MyService.class);
        bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setItems(){

        View.OnClickListener buttonOnClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.playSound:
                        player.start();
                        break;
                    case R.id.newActivity:
                        Intent intent = new Intent(MainActivity.this, PageActivity.class);
                        String s = ((EditText)findViewById(R.id.newActivityEditText)).getText().toString();
                        intent.putExtra("Message", s);
                        startActivity(intent);
                        break;
                    case R.id.fragmentPage:
                        startActivity(new Intent(MainActivity.this, FragmentPage.class));
                        break;
                    case R.id.list:
                        startActivity(new Intent(MainActivity.this, ListActivity.class));
                        break;
                    case R.id.datActivity:
                        startActivity(new Intent(MainActivity.this, DatActivity.class));
                        break;
                    case R.id.videoActivity:
                        startActivity(new Intent(MainActivity.this, VideoActivity.class));
                        break;
                    case R.id.cameraActivity:
                        startActivity(new Intent(MainActivity.this, CameraActivity.class));
                        break;
                    case R.id.notificationButton:
                        createNotification();
                        break;
                }
            }
        };

        Button playSound = (Button)findViewById(R.id.playSound);
        playSound.setOnClickListener(buttonOnClickListener);

        Button newActivity = (Button)findViewById(R.id.newActivity);
        newActivity.setOnClickListener(buttonOnClickListener);

        Button fragmentPage = (Button)findViewById(R.id.fragmentPage);
        fragmentPage.setOnClickListener(buttonOnClickListener);

        Button list = (Button)findViewById(R.id.list);
        list.setOnClickListener(buttonOnClickListener);

        Button sql = (Button)findViewById(R.id.datActivity);
        sql.setOnClickListener(buttonOnClickListener);

        Button video = (Button)findViewById(R.id.videoActivity);
        video.setOnClickListener(buttonOnClickListener);

        Button camera = (Button)findViewById(R.id.cameraActivity);
        camera.setOnClickListener(buttonOnClickListener);

        Button not = (Button)findViewById(R.id.notificationButton);
        not.setOnClickListener(buttonOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, Settings.class));
                return true;
            case R.id.checkOption:
                Toast t = Toast.makeText(this, "This is a toast", Toast.LENGTH_LONG);
                t.show();
                return true;
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.release();
    }

    private void createNotification(){
        n.setSmallIcon(R.drawable.notification_icon);
        n.setTicker("New notification from My App");
        n.setWhen(System.currentTimeMillis());
        n.setContentTitle("Here is TITLE");
        n.setContentText("Tihs is content. Blah blah blah");

        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{new Intent(this, MainActivity.class)},
                PendingIntent.FLAG_UPDATE_CURRENT);
        n.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(notificationId, n.build());
    }
}

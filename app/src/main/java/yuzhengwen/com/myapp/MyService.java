package yuzhengwen.com.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {

    private final IBinder b = new MyLocalBinder() ;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return b;
    }

    public String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        return format.format(new Date());
    }

    public class MyLocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
}

package yuzhengwen.com.myapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        Button b = (Button) findViewById(R.id.cameraButton);
        b.setOnClickListener(this);
        i = (ImageView) findViewById(R.id.cameraImageView);

        if(!hasCamera())
            b.setEnabled(false);
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //get photo
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            /*
            bitmap = invertPhoto(bitmap);
            i.setImageBitmap(bitmap);*/

            Drawable[] layers = new Drawable[2];
            layers[0] = new BitmapDrawable(getResources(), bitmap);
            layers[1] = getResources().getDrawable(R.drawable.filter);
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            i.setImageDrawable(layerDrawable);

            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", "desc");
        }
    }

    private Bitmap invertPhoto(Bitmap b) {
        int A, R, G, B;
        int pixelColor;
        int width = b.getWidth();
        int height = b.getHeight();

        Bitmap newBitmap = Bitmap.createBitmap(width,height, b.getConfig());
        for(int x =0;x<width;x++){
            for(int y =0;y<height;y++){
                pixelColor = b.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);

                newBitmap.setPixel(x, y, Color.argb(A, 255 - R, 255 - G, 255 - B));
            }
        }
        return newBitmap;
    }
}

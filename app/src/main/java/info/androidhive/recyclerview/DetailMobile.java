package info.androidhive.recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DetailMobile extends AppCompatActivity {
    private ImageView mImageView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobile);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("model");
        TextView textView = findViewById(R.id.model);
        textView.setText(message);
        message = intent.getStringExtra("marca");
        textView = findViewById(R.id.marca);
        textView.setText(message);
        message = intent.getStringExtra("pantalla");
        textView = findViewById(R.id.midaPantalla);
        textView.setText(message);
        message = intent.getStringExtra("hdd");
        textView = findViewById(R.id.quantHdd);
        textView.setText(message);
        message = intent.getStringExtra("ram");
        textView = findViewById(R.id.quantRam);
        textView.setText(message);
        message = intent.getStringExtra("camara");
        textView = findViewById(R.id.camera);
        textView.setText(message);
        message = intent.getStringExtra("so");
        textView = findViewById(R.id.versio);
        textView.setText(message);
        message = intent.getStringExtra("any");
        textView = findViewById(R.id.dataLlancament);
        textView.setText(message);
        message = intent.getStringExtra("preu");
        textView = findViewById(R.id.preu);
        textView.setText(message);
        String url = intent.getStringExtra("foto");
        mImageView = findViewById(R.id.imageView2);
        new TascaDescarrega().execute(url);
    }
    private Bitmap carregarImatgeDeLaXarxa(String url){
        try {
            return BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class TascaDescarrega extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            return carregarImatgeDeLaXarxa(urls[0]);
        }

        protected void onPostExecute(Bitmap result) {
            mImageView.setImageBitmap(result);
        }
    }
}

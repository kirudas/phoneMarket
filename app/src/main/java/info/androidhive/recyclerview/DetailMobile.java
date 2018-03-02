package info.androidhive.recyclerview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private Cursor Mobiles;
    private MobilesConversor MobilesConv;
    private MobilesSqlLiteHelper mobHelper;
    //private String[] colums= {"model","marca","pantalla","hdd","ram","camara","so","any","preu","foto"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobile);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String codi = intent.getStringExtra("codi");
        mobHelper = new  MobilesSqlLiteHelper(this, "Mobiles.db", null, 2);
        // obtenir l'objecte BD
        SQLiteDatabase db = mobHelper.getWritableDatabase();
        MobilesConv = new MobilesConversor(mobHelper);
        //Busca el mobil segons el codi
        Cursor cursor = MobilesConv.getAll();
        cursor.moveToFirst();
        boolean acaba = cursor.isLast();
        while(!acaba && !cursor.isAfterLast()){
            if (cursor.getString(0).equals(codi)){
                acaba = true;
            }else {
                cursor.moveToNext();
            }
        }
        if (!acaba){
            return;
        }
        TextView textView = findViewById(R.id.inpCrtMod);
        textView.setText(cursor.getString(1));
        textView = findViewById(R.id.marca);
        textView.setText(cursor.getString(2));
        textView = findViewById(R.id.midaPantalla);
        textView.setText(cursor.getString(3));
        textView = findViewById(R.id.quantHdd);
        textView.setText(cursor.getString(4));
        textView = findViewById(R.id.quantRam);
        textView.setText(cursor.getString(5));
        textView = findViewById(R.id.camera);
        textView.setText(cursor.getString(6));
        textView = findViewById(R.id.versio);
        textView.setText(cursor.getString(7));
        textView = findViewById(R.id.inpCrtDataLlancament);
        textView.setText(cursor.getString(8));
        textView = findViewById(R.id.preu);
        textView.setText(cursor.getString(9));
        mImageView = findViewById(R.id.imageView2);

        new TascaDescarrega().execute(cursor.getString(10));
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

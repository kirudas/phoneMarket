package info.androidhive.recyclerview;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * Activitat que permet introduir un nou titular a la BD
 */
public class NouMobileActivity extends Activity {
    private EditText nom;
    private EditText marca;
    private EditText model;
    private EditText dataLlancament;
    private EditText pantalla;
    private EditText hdd;
    private EditText ram;
    private EditText camera;
    private EditText so;
    private EditText preu;


    private Button btnOk;
    private Button btnCancel;


    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mobile);
        nom = (EditText) findViewById(R.id.inpCrtMarca);
        marca = (EditText) findViewById(R.id.inpCrtMarca);
        model = (EditText) findViewById(R.id.inpCrtMod);
        dataLlancament = (EditText) findViewById(R.id.inpCrtDataLlancament);
        pantalla = (EditText) findViewById(R.id.inpCrtPantalla);
        hdd = (EditText) findViewById(R.id.inpCrtHdd);
        ram = (EditText) findViewById(R.id.inpCrtRam);
        camera = (EditText) findViewById(R.id.inpCrtCamara);
        so = (EditText) findViewById(R.id.inpCrtSo);
        preu = (EditText) findViewById(R.id.inpCrtPreu);
        btnOk = (Button) findViewById(R.id.btnCrtOk);
        btnCancel = (Button) findViewById(R.id.btnCrtCanc);

        btnOk.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                desar();
            }
        });

        btnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    /**
     * Mètode que desa les dades introduïdes per l'usuari
     */
    private void desar() {
        try {
            MobilesSqlLiteHelper titHelper = new MobilesSqlLiteHelper(this, "Mobiles.db", null, 2);
            MobilesConversor mobilesConv = new MobilesConversor(titHelper);
            // obtenir l'objecte BD
            SQLiteDatabase db = titHelper.getWritableDatabase();
            // Si s'ha obert correctament la BD
            if(db != null) {
                // esborrar tots els registres de la taula
                mobilesConv.save(new Mobile(0, nom.getText().toString(), marca.getText().toString(), model.getText().toString(), dataLlancament.getText().toString(), pantalla.getText().toString(), hdd.getText().toString(), ram.getText().toString(), camera.getText().toString(), so.getText().toString(), preu.getText().toString(), preu.getText().toString()));
                setResult(RESULT_OK);
            }
        }
        catch(Exception e) {
            setResult(RESULT_CANCELED);
        }
        finally {
            finish();
        }
    }
}
package info.androidhive.recyclerview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*private List<Mobile> mobileList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MobilesAdapter mAdapter;*/
    final static int ADD_Mobile = 1;

    private Cursor Mobiles;
    private MobilesAdapter adapter;
    private MobilesSqlLiteHelper mobHelper;
    private MobilesConversor MobilesConv;
    private RecyclerView llista;
    private TextView lblNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // recuperar els controls
        //lblNoData = (TextView) findViewById(R.id.lblNoData);
        llista = (RecyclerView) findViewById(R.id.recycler_view);

        // vincular el menú contextual a la llista
        registerForContextMenu(llista);

        // crear l'objecte que crea la connexió amb la BD
        mobHelper = new  MobilesSqlLiteHelper(this, "Mobiles.db", null, 2);
        // obtenir l'objecte BD
        SQLiteDatabase db = mobHelper.getWritableDatabase();
        MobilesConv = new MobilesConversor(mobHelper);

        // Si s'ha obert correctament la BD
        if(db != null) {
            // actualitzar la llista
            refreshData();
            // Tancar la base de dades
            db.close();
        }      
        /*recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MobilesAdapter(mobileList);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);*/

        // row click listener
        llista.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), llista, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Mobile mobile = adapter.getItem(position); //.get(position);
                //Toast.makeText(getApplicationContext(), mobile.getNom() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DetailMobile.class);
                String aux = String.valueOf(mobile.getCodi());
                intent.putExtra("codi", aux);
                //intent.putExtra("ID",mobile.getNom());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //prepareMobileData();
    }
    /*
 * Torna a executar la consulta i a enllaçar les dades
 */
    void refreshData() {
        Mobiles = MobilesConv.getAll();
        adapter = new MobilesAdapter(this, Mobiles);
        llista.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        llista.setLayoutManager(mLayoutManager);

        llista.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        llista.setItemAnimator(new DefaultItemAnimator());

        llista.setAdapter(adapter);
        if(Mobiles.getCount() == 0) {
            //lblNoData.setVisibility(lblNoData.VISIBLE);
            llista.setVisibility(llista.INVISIBLE);
        }
        else {
            //lblNoData.setVisibility(lblNoData.INVISIBLE);
            llista.setVisibility(llista.VISIBLE);
        }
    }

    /**
     * Crea el menú d'opcions de l'aplicació
     */
     public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }



    /**
     * Respon a l'event d'haver escollit una opció del menú de l'app
     */

        public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(MainActivity.this,AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.mnuAfegir:
                Intent i = new Intent(this, NouMobileActivity.class);
                startActivityForResult(i, ADD_Mobile);
                return true;
            case R.id.mnuBorrar:
                MobilesConv.remove(adapter.getItem(info.position));
                // actualitzar la llista
                refreshData();
                // mostrar missatge
                Toast.makeText(this, "S'ha esborrat el mobile!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnuSortir :
                this.finish();
                return true;
        }
        return false;
    }


    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // si s'ha tancat l'activitat ADD_TITULAR i ha anat bé
        if( requestCode == ADD_Mobile && resultCode == RESULT_OK) {
            refreshData();
        }
    }

    /**
     * Crea el menú contextual
     */
    /*
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }
*/
    /**
     * Respon a l'event d'haver escollit una opció del menú contextual
     */
    /*
    public boolean onContextItemSelected(MenuItem item) {
        //AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(MainActivity.this,AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.mnuVeureDades:
                // mostrar les dades de l'element escollit
                Toast.makeText(this, "VEURÀ DADES", Toast.LENGTH_LONG).show();
                return true;
            case R.id.mnuEsborrar:
                // esborrar l'element escollit
                //MobilesConv.remove(adapter.getItem(info.position));

                // actualitzar la llista
                refreshData();
                // mostrar missatge
                Toast.makeText(this, "S'ha esborrat el titular!", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "VEURÀ DADES", Toast.LENGTH_LONG).show();


                return true;
            default: break;
        }
        return false;
    }
    */
    /**
     * Prepares sample data to provide data set to adapter
     */
    /*private void prepareMobileData(){
        Mobile mobile = new Mobile("iPhone X", "Apple","iPhone X","12-11-2017", "5,8", "64", "Unknow", "12", "iOS 11", "1159€", "https://images.apple.com/es/iphone/compare/images/overview/compare_iphonex_silver_large.jpg");
        mobileList.add(mobile);
        mobile = new Mobile("iPhone 8", "Apple","iPhone 8","12-11-2017", "4,7", "64", "Unknow", "12", "iOS 11", "809€", "https://images.apple.com/es/iphone/compare/images/overview/compare_iphone8_silver_large.jpg");
        mobileList.add(mobile);
        mobile = new Mobile("iPhone 8 Plus", "Apple","iPhone 8 Plus","12-11-2017", "5,5", "64", "Unknow", "12", "iOS 11", "919€", "https://images.apple.com/es/iphone/compare/images/overview/compare_iphone8plus_silver_large.jpg");
        mobileList.add(mobile);
        mobile = new Mobile("Samsung", "Samsung", "Samsung Note 8", "05-10-2017", "6,3", "64", "6", "12", "Android 7.1", "1010€", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6015/6015800_sd.jpg;maxHeight=640;maxWidth=550");
        mobileList.add(mobile);
        mobile = new Mobile("Samsung", "Samsung", "Samsung S8", "29-03-2017", "5,8", "64", "4", "12", "Android 7", "809€", "http://d2giyh01gjb6fi.cloudfront.net/phone_front/0001/61/thumb_60270_phone_front_big.jpeg");
        mobileList.add(mobile);
        mobile = new Mobile("Samsung", "Samsung", "Samsung S8 Plus", "29-03-2017", "6,2", "64", "4", "12", "Android 7", "919€", "http://techbeasts.com/wp-content/uploads/2017/03/Samsung-Galaxy-S8-Press-Render-1.jpg");
        mobileList.add(mobile);

        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }*/

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(MainActivity.this,AboutUs.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

*/
}

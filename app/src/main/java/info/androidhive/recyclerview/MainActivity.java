package info.androidhive.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Mobile> mobileList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MobilesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MobilesAdapter(mobileList);

        recyclerView.setHasFixedSize(true);

        // vertical RecyclerView
        // keep mobile_list_row.xmll width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep mobile_list_row.xmll width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Mobile mobile = mobileList.get(position);
                //Toast.makeText(getApplicationContext(), mobile.getNom() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,DetailMobile.class);
                intent.putExtra("nom", mobile.getNom());
                intent.putExtra("marca", mobile.getMarca());
                intent.putExtra("model", mobile.getModel());
                intent.putExtra("pantalla", mobile.getPantalla());
                intent.putExtra("hdd", mobile.getHdd());
                intent.putExtra("ram", mobile.getRam());
                intent.putExtra("camara", mobile.getCamara());
                intent.putExtra("so", mobile.getSo());
                intent.putExtra("preu", mobile.getPreu());
                intent.putExtra("any", mobile.getAny());
                intent.putExtra("foto",mobile.getImatge());
                //intent.putExtra("ID",mobile.getNom());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMobileData();
    }

    /**
     * Prepares sample data to provide data set to adapter
     */
    private void prepareMobileData(){
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

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

}

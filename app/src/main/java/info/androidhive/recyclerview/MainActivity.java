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
    public static final String EXTRA_MESSAGE = "info.androidhive.recyclerview.MESSAGE";
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
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
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
                intent.putExtra(EXTRA_MESSAGE, mobile.getNom());
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
        Mobile mobile = new Mobile("IPhone X", "Apple","X","2017");
        mobileList.add(mobile);
        mobile = new Mobile("Oneplus 5T", "OnePlus","5T","2018");
        mobileList.add(mobile);
        mobile = new Mobile("Samsung Galaxy S8", "Samsung","S8","2017");
        mobileList.add(mobile);
        mobile = new Mobile("Xperoa XZ", "Sony","XZ","2017");
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
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                return true;

            /*case R.id.action_switch:
                Toast.makeText(this, "Switch", Toast.LENGTH_SHORT).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}

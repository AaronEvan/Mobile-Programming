package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.adapter.AdapterFinish;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.DetailDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.TransaksiDatabaseHandler;

import java.util.ArrayList;

public class FinishActivity extends AppCompatActivity {
    public ArrayList<Product> myOrder = new ArrayList<>();
    RecyclerView recycleview;
    AdapterFinish adapter;
    LinearLayoutManager layoutManager;
    TextView totalTxt;
    Integer counttotals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Product>) bundle.get("myOrder");

        totalTxt = findViewById(R.id.idtotal);
        countTotal();

        recycleview = findViewById(R.id.gridmodel);
        setRecycle();
    }

    private void countTotal() {
        counttotals = 0;
        for(int i = 0; i<myOrder.size();i++){
            counttotals = counttotals + ( myOrder.get(i).getHargaProduct() * myOrder.get(i).getQuantityProduct());
        }
        totalTxt.setText("Total : Rp. "+Integer.toString(counttotals));
    }

    private void setRecycle() {
        recycleview.setHasFixedSize(true);

        layoutManager =  new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);

        adapter = new AdapterFinish(myOrder);
        recycleview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.home_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {

    }
}
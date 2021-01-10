package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.adapter.AdapterListFood;
import com.aaronevan.binusezyfoody.adapter.AdapterListItem;
import com.aaronevan.binusezyfoody.binusclass.MyMoney;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    ArrayList<Product> listDrinkItems = new ArrayList<>();
    public ArrayList<Product> myOrder = new ArrayList<>();

    RecyclerView recycleview;
    GridLayoutManager layoutManager;
    AdapterListFood adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Product>) bundle.getSerializable("myOrder");

        init();
        recycleview = findViewById(R.id.gridmodel);
        setRecycle();
    }

    public void init(){
        ProductDatabaseHandler phd = new ProductDatabaseHandler(this);
        listDrinkItems = phd.getData(MyMoney.getStore(), "Food");
    }

    private void setRecycle() {
        recycleview.setHasFixedSize(true);

        layoutManager =  new GridLayoutManager(this, 2);
        recycleview.setLayoutManager(layoutManager);

        adapter = new AdapterListFood(listDrinkItems, myOrder);
        recycleview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.cart_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.myorder){
            //validasi arraylist tidak kosong / nol
            if(myOrder.size() != 0){
                Bundle bundle = new Bundle();
                Intent intent = new Intent(this, MyOrderActivity.class);
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Cart Still Empty",Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
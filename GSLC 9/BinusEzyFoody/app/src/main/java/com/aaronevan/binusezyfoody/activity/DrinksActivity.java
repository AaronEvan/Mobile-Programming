package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.binusclass.Drink;
import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.adapter.AdapterListItem;

import java.util.ArrayList;

public class DrinksActivity extends AppCompatActivity {
    ArrayList<Drink> listDrinkItems = new ArrayList<>();
    public ArrayList<Drink> myOrder = new ArrayList<>();

    RecyclerView recycleview;
    GridLayoutManager layoutManager;
    AdapterListItem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        init();
        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Drink>) bundle.getSerializable("myOrder");

        recycleview = findViewById(R.id.gridmodel);
        setRecycle();
    }

    public void init(){
        listDrinkItems.add(new Drink(R.drawable.water,"Mineral Water", 4000));
        listDrinkItems.add(new Drink(R.drawable.apel,"Jus Apel", 12000));
        listDrinkItems.add(new Drink(R.drawable.mangga,"Jus Mangga", 14000));
        listDrinkItems.add(new Drink(R.drawable.alpukat,"Jus Alpukat", 15000));
    }

    private void setRecycle() {
        recycleview.setHasFixedSize(true);

        layoutManager =  new GridLayoutManager(this, 2);
        recycleview.setLayoutManager(layoutManager);

        adapter = new AdapterListItem(listDrinkItems, myOrder);
        recycleview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                Toast.makeText(getApplicationContext(),"Your Didn't Order Yet",Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }
}
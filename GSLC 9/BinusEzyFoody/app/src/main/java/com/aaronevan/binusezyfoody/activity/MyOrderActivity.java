package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.binusclass.Drink;
import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.adapter.AdapterMyOrder;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {
    ArrayList<Drink> myOrder = new ArrayList<>();
    RecyclerView recycleview;
    LinearLayoutManager layoutManager;
    AdapterMyOrder adapter;
    TextView totalTxt;
    TextView emptyTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Drink>) bundle.get("myOrder");

        totalTxt = findViewById(R.id.idtotal);
        emptyTxt = findViewById(R.id.empty);
        emptyTxt.setVisibility(View.GONE);
        countTotal();

        recycleview = findViewById(R.id.gridmodel);
        setRecycle();

        if(myOrder.size() == 0){
            recycleview.setVisibility(View.GONE);
            emptyTxt.setVisibility(View.VISIBLE);
        }
    }

    private void countTotal() {
        int counttotals = 0;
        for(int i = 0; i<myOrder.size();i++){
            counttotals = counttotals + ( myOrder.get(i).getPrice() * myOrder.get(i).getQuantity());
        }
        totalTxt.setText("Total : Rp. "+Integer.toString(counttotals));
    }

    private void setRecycle() {
        recycleview.setHasFixedSize(true);

        layoutManager =  new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);

        adapter = new AdapterMyOrder(myOrder);
        recycleview.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DrinksActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pay_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.pay){
            if(myOrder.size() == 0){
                Toast.makeText(getApplicationContext(),"Your Cart is Empty",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(this, FinishActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);
                Toast.makeText(getApplicationContext(),"Your Order Complete! Thank You!",Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }

        }
        return true;
    }

}
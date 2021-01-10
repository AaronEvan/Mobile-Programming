package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.adapter.AdapterMyOrder;
import com.aaronevan.binusezyfoody.binusclass.MyMoney;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.DetailDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.TransaksiDatabaseHandler;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {
    ArrayList<Product> myOrder = new ArrayList<>();
    RecyclerView recycleview;
    LinearLayoutManager layoutManager;
    AdapterMyOrder adapter;
    TextView totalTxt;
    TextView emptyTxt;
    Integer counttotals;
    EditText alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);
        alamat = findViewById(R.id.alamat);
        TextView balance = findViewById(R.id.balance);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Product>) bundle.get("myOrder");

        totalTxt = findViewById(R.id.idtotal);
        emptyTxt = findViewById(R.id.empty);
        emptyTxt.setVisibility(View.GONE);
        countTotal();

        recycleview = findViewById(R.id.gridmodel);
        setRecycle();

        if (myOrder.size() == 0) {
            recycleview.setVisibility(View.GONE);
            emptyTxt.setVisibility(View.VISIBLE);
        }

        MyMoney mm = new MyMoney(this);
        balance.setText("Balance : Rp. " + mm.getMoney().toString());
    }

    private void countTotal() {
        counttotals = 0;
        for (int i = 0; i < myOrder.size(); i++) {
            counttotals = counttotals + (myOrder.get(i).getHargaProduct() * myOrder.get(i).getQuantityProduct());
        }
        totalTxt.setText("Total : Rp. " + Integer.toString(counttotals));
    }

    private void setRecycle() {
        recycleview.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);

        adapter = new AdapterMyOrder(myOrder);
        recycleview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.pay_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pay) {
            if (myOrder.size() == 0) {
                Toast.makeText(getApplicationContext(), "Cart Still Empty", Toast.LENGTH_LONG).show();
            } else if (alamat.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Alamat Harus diisi", Toast.LENGTH_LONG).show();

            }  else if (alamat.getText().toString().length() <= 10) {
                Toast.makeText(getApplicationContext(), "Panjang Minimal Alamat 10 huruf", Toast.LENGTH_LONG).show();
            } else {
                MyMoney mm = new MyMoney(this);
                if (counttotals > mm.getMoney()) {
                    Toast.makeText(getApplicationContext(), "Money Not Enough!", Toast.LENGTH_LONG).show();
                    return false;
                }
                Intent intent = new Intent(this, FinishActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);
                String dest = alamat.getText().toString();
                TransaksiDatabaseHandler tdh = new TransaksiDatabaseHandler(this);
                Integer lastID = tdh.addTransaction(dest, counttotals);
                DetailDatabaseHandler ddh = new DetailDatabaseHandler(this);
                ProductDatabaseHandler pdh = new ProductDatabaseHandler(this);
                mm.ReduceMoney(counttotals);

                for (int z = 0; z < myOrder.size(); z++) {
                    ddh.addDetailTransaction(lastID, myOrder.get(z).getIdProduct(), myOrder.get(z).getQuantityProduct());
                    pdh.reduceStock(myOrder.get(z).getIdProduct(), myOrder.get(z).getStockProduct() - myOrder.get(z).getQuantityProduct());
                }

                Toast.makeText(getApplicationContext(), "Your Order Complete! Thank You!", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
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
package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.DetailDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.TransaksiDatabaseHandler;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    public ArrayList<Product> myOrder = new ArrayList<>();
    EditText edtTxt;
    Product data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data = (Product) bundle.getSerializable("objDrink");
        myOrder = (ArrayList<Product>) bundle.getSerializable("myOrder");

        initializeData();
    }

    private void initializeData() {
        edtTxt = findViewById(R.id.editTextNumberSigned);
        ImageView image = findViewById(R.id.idimage);
        TextView nama = findViewById(R.id.idnama);
        TextView price = findViewById(R.id.idprice);
        TextView stock = findViewById(R.id.idstock);

        image.setImageResource(data.getImageProduct());
        nama.setText(data.getNamaProduct());
        price.setText("Rp. " + Integer.toString(data.getHargaProduct()));
        stock.setText("Stock : " + Integer.toString(data.getStockProduct()));
    }

    public void moveToItem(View view) {
        if (edtTxt.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Quantity Must > 0", Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = Integer.parseInt(edtTxt.getText().toString());

        if (qty <= 0) {
            Toast.makeText(getApplicationContext(), "Quantity Must > 0", Toast.LENGTH_SHORT).show();
            return;
        }

        if (data.getStockProduct() == 0) {
            Toast.makeText(getApplicationContext(), "No Stock!" + data.getStockProduct(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (qty > data.getStockProduct()) {
            Toast.makeText(getApplicationContext(), "Quantity Must <= " + data.getStockProduct(), Toast.LENGTH_SHORT).show();
            return;
        }

        data.setQuantityProduct(qty);
        int flag = 0;
        for (int i = 0; i < myOrder.size(); i++) {
            if (myOrder.get(i).getIdProduct().toString().equals(data.getIdProduct().toString())) {
                myOrder.set(i, data);
                flag = 1;
                Toast.makeText(getApplicationContext(), "Updated " + data.getQuantityProduct() + " x " + data.getNamaProduct() + " to Your Cart", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (flag == 0) {
            myOrder.add(data);
            Toast.makeText(getApplicationContext(), "Added " + data.getQuantityProduct() + " x " + data.getNamaProduct() + " to Your Cart", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.cart_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.myorder) {
            if (edtTxt.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Quantity Must > 0", Toast.LENGTH_SHORT).show();
                return false;
            }
            int qty = Integer.parseInt(edtTxt.getText().toString());
            if (qty <= 0) {
                Toast.makeText(getApplicationContext(), "Quantity Must > 0", Toast.LENGTH_SHORT).show();
                return false;
            }

            data.setQuantityProduct(qty);
            myOrder.add(data);
            Intent intent = new Intent(this, MyOrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("myOrder", myOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
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
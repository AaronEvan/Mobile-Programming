package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.binusclass.Drink;
import com.aaronevan.binusezyfoody.R;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    public ArrayList<Drink> myOrder;
    EditText edtTxt;
    Drink data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        data = (Drink) bundle.getSerializable("objDrink");
        myOrder = (ArrayList<Drink>) bundle.getSerializable("myOrder");

        initializeData();
    }

    private void initializeData() {
        edtTxt = findViewById(R.id.editTextNumberSigned);
        ImageView image = findViewById(R.id.idimage);
        TextView nama = findViewById(R.id.idnama);
        TextView price = findViewById(R.id.idprice);

        image.setImageResource(data.getImage());
        nama.setText(data.getName());
        price.setText("Rp. " + Integer.toString(data.getPrice()));
    }

    public void moveToItem(View view){
        if(edtTxt.getText().toString().equals("") ){
            Toast.makeText(getApplicationContext(),"Quantity Must > 0",Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = Integer.parseInt(edtTxt.getText().toString());

        if(qty <= 0){
            Toast.makeText(getApplicationContext(),"Quantity Must > 0",Toast.LENGTH_SHORT).show();
            return;
        }

        data.setQuantity(qty);
        myOrder.add(data);
        Intent intent = new Intent(this, DrinksActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        Toast.makeText(getApplicationContext(),"Added " + data.getQuantity()+ " x " + data.getName() + " to Your Cart",Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
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
            if(edtTxt.getText().toString().equals("") ){
                Toast.makeText(getApplicationContext(),"Quantity Must > 0",Toast.LENGTH_SHORT).show();
                return false;
            }
            int qty = Integer.parseInt(edtTxt.getText().toString());
            if(qty <= 0){
                Toast.makeText(getApplicationContext(),"Quantity Must > 0",Toast.LENGTH_SHORT).show();
                return false;
            }

            data.setQuantity(qty);
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
}
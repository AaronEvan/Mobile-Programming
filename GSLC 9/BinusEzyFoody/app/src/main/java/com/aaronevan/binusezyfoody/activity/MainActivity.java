package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.StoreActivity;
import com.aaronevan.binusezyfoody.binusclass.Drink;
import com.aaronevan.binusezyfoody.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public ArrayList<Drink> myOrder = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

    }

    public void moveToDrinks(View view) {
        Intent intent = new Intent(this, DrinksActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void moveToStore(View view) {
        Intent intent = new Intent(this, StoreActivity.class);
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
            Toast.makeText(getApplicationContext(),"Cart Still Empty",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
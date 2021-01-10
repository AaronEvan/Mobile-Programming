package com.aaronevan.binusezyfoody.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.StoreActivity;
import com.aaronevan.binusezyfoody.binusclass.MyMoney;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.RestoDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.UserDatabaseHandler;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public ArrayList<Product> myOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TextView store = findViewById(R.id.store);
        TextView balance = findViewById(R.id.balance);

        if(MyMoney.getStore() == 1){
            store.setText("Store : Kemanggisan Store");
        }else{
            store.setText("Store : Alam Sutera Store");
        }
        if(bundle != null){
            if(bundle.containsKey("myOrder") ){
                myOrder = (ArrayList<Product>) bundle.getSerializable("myOrder");
            }
        }
        initDatabase();

        MyMoney mm = new MyMoney(this);
        balance.setText("Balance : Rp. " + mm.getMoney().toString());
    }

    private void initDatabase() {
        UserDatabaseHandler udh = new UserDatabaseHandler(this);
        if(udh.getMoney() == -1){
            udh.initMoney();
        }
        RestoDatabaseHandler rdh = new RestoDatabaseHandler(this);
        if(rdh.getData().size() == 0){
            rdh.init();
        }
        ProductDatabaseHandler pdh = new ProductDatabaseHandler(this);
        if(pdh.getData().size() == 0){
            pdh.initProduct();
        }

    }

    public void moveToDrinks(View view) {
        Intent intent = new Intent(this, DrinksActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void moveToFood(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void moveToSnack(View view) {
        Intent intent = new Intent(this, SnackActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void moveToTopUp(View view) {
        Intent intent = new Intent(this, TopUpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.myorder){
            if(myOrder.size() == 0){
                Toast.makeText(getApplicationContext(),"Cart Still Empty",Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(this, MyOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        }else if(id == R.id.myLoc){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Are You Sure?");
            alert.setMessage("Your Cart Will Be Deleted!");
            final Intent intent = new Intent(this, StoreActivity.class);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),"Loading Store Location...",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(),"Canceled Change Store",Toast.LENGTH_SHORT).show();
                }
            });
            alert.create().show();
        }else if(id == R.id.history){
            Intent intent = new Intent(this, HistoryActivity.class); // history
            Bundle bundle = new Bundle();
            bundle.putSerializable("myOrder", myOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        return true;
    }

}
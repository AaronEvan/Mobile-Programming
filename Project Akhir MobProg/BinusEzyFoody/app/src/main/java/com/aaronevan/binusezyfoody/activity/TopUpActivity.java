package com.aaronevan.binusezyfoody.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.binusclass.MyMoney;
import com.aaronevan.binusezyfoody.binusclass.Product;

import java.util.ArrayList;

public class TopUpActivity extends AppCompatActivity {
    EditText edtTxt;
    TextView saldo;
    public ArrayList<Product> myOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        edtTxt = findViewById(R.id.editTxt);
        refreshMoney();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Product>) bundle.getSerializable("myOrder");
    }

    public void refreshMoney(){
        MyMoney my = new MyMoney(this);
        saldo = (TextView) findViewById(R.id.saldo);
        saldo.setText("Balance : Rp." + my.getMoney().toString());
    }

    public void TopUpMoney(View view){
        if(edtTxt.getText().toString().equals("") ){
            Toast.makeText(getApplicationContext(),"Money Must > 0",Toast.LENGTH_SHORT).show();
            return;
        }

        Integer qty = Integer.parseInt(edtTxt.getText().toString());

        if(qty <= 0){
            Toast.makeText(getApplicationContext(),"Money Must > 0",Toast.LENGTH_SHORT).show();
            return;
        }
        if(qty > 2000000){
            Toast.makeText(getApplicationContext(),"Money Must < 2.000.000",Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplicationContext(),"Top-Up Berhasil!",Toast.LENGTH_LONG).show();
        edtTxt.setText("");
        MyMoney mm = new MyMoney(this);
        mm.TopUpMoney(qty);
        refreshMoney();
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
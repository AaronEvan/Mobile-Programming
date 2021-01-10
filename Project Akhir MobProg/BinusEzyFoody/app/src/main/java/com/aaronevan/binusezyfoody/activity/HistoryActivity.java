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
import com.aaronevan.binusezyfoody.adapter.AdapterHistoryHeader;
import com.aaronevan.binusezyfoody.adapter.AdapterListItem;
import com.aaronevan.binusezyfoody.binusclass.HistoryHeader;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.TransaksiDatabaseHandler;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ArrayList<HistoryHeader> historyHeaders = new ArrayList<>();
    public ArrayList<Product> myOrder = new ArrayList<>();

    RecyclerView recycleview;
    GridLayoutManager layoutManager;
    AdapterHistoryHeader adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        init();
        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);
        bartool.setTitle("History Header");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Product>) bundle.getSerializable("myOrder");

        recycleview = findViewById(R.id.gridmodel);
        setRecycle();
    }

    public void init(){
        TransaksiDatabaseHandler tdh = new TransaksiDatabaseHandler(this);
        historyHeaders= tdh.getAllData();
    }

    private void setRecycle() {
        recycleview.setHasFixedSize(true);

        layoutManager =  new GridLayoutManager(this, 1);
        recycleview.setLayoutManager(layoutManager);

        adapter = new AdapterHistoryHeader(historyHeaders, myOrder);
        recycleview.setAdapter(adapter);
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
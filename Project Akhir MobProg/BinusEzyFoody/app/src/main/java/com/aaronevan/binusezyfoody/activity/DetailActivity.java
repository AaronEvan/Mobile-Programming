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
import com.aaronevan.binusezyfoody.adapter.AdapterDetail;
import com.aaronevan.binusezyfoody.adapter.AdapterHistoryHeader;
import com.aaronevan.binusezyfoody.adapter.AdapterListItem;
import com.aaronevan.binusezyfoody.binusclass.HistoryDetail;
import com.aaronevan.binusezyfoody.binusclass.HistoryHeader;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.DetailDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;
import com.aaronevan.binusezyfoody.helper.TransaksiDatabaseHandler;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ArrayList<HistoryDetail> details = new ArrayList<>();
    public ArrayList<Product> myOrder = new ArrayList<>();
    public ArrayList<Product> allProduct = new ArrayList<>();
    HistoryHeader historyData;

    RecyclerView recycleview;
    GridLayoutManager layoutManager;
    AdapterDetail adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        Toolbar bartool = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(bartool);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myOrder = (ArrayList<Product>) bundle.getSerializable("myOrder");
        historyData = (HistoryHeader) bundle.getSerializable("objHeader");
        recycleview = findViewById(R.id.gridmodel);

        bartool.setTitle("History Detail : " + historyData.getTanggal());
        init();
        setRecycle();
    }

    public void init(){
        DetailDatabaseHandler ddh = new DetailDatabaseHandler(this);
        details = ddh.getAllData(historyData.getIdTransaksi());
        ProductDatabaseHandler pdh = new ProductDatabaseHandler(this);
        allProduct = pdh.getData();
    }

    private void setRecycle() {
        recycleview.setHasFixedSize(true);

        layoutManager =  new GridLayoutManager(this, 2);
        recycleview.setLayoutManager(layoutManager);

        adapter = new AdapterDetail(details, allProduct, this);
        recycleview.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("myOrder", myOrder);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
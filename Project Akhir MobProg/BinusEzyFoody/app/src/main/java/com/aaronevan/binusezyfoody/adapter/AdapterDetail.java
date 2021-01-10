package com.aaronevan.binusezyfoody.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.activity.DetailActivity;
import com.aaronevan.binusezyfoody.activity.HistoryActivity;
import com.aaronevan.binusezyfoody.binusclass.HistoryDetail;
import com.aaronevan.binusezyfoody.binusclass.HistoryHeader;
import com.aaronevan.binusezyfoody.binusclass.Product;
import com.aaronevan.binusezyfoody.helper.ProductDatabaseHandler;

import java.util.ArrayList;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.MyViewHolder> {
    private ArrayList<HistoryDetail> details;
    private ArrayList<Product> allProduct;
    Context con;
//    public ArrayList<Product> myOrder;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        Context context;
        public MyViewHolder(CardView v) {
            super(v);
            cardview = v;
            context = v.getContext();
        }
    }

    public AdapterDetail(ArrayList<HistoryDetail> details, ArrayList<Product> allProduct, Context con) {
        this.details = details;
        this.allProduct = allProduct;
        this.con = con;
    }

    @NonNull
    @Override
    public AdapterDetail.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_layout, parent, false);
        return new MyViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final CardView cardView = holder.cardview;

        ImageView image = cardView.findViewById(R.id.imageid);
        TextView nama = cardView.findViewById(R.id.namaid);
        TextView harga = cardView.findViewById(R.id.hargaid);
        TextView qty = cardView.findViewById(R.id.qtyid);

        HistoryDetail temp = details.get(position);
        ProductDatabaseHandler pdh = new ProductDatabaseHandler(con);
        Product temp2 = pdh.getOneData(temp.getIdProduct());

        image.setImageResource(temp2.getImageProduct());
        nama.setText(temp2.getNamaProduct());
        harga.setText("Rp. " + temp2.getHargaProduct().toString());
        qty.setText("Qty :" + temp.getQty().toString());

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                HistoryHeader obj = historyHeaders.get(position);
//                Bundle bundle = new Bundle();
//                Intent intent = new Intent(cardView.getContext(), .class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                bundle.putSerializable("objHeader", obj);
//                bundle.putSerializable("myOrder", myOrder);
//                intent.putExtras(bundle);
//                cardView.getContext().startActivity(intent);
//                ((HistoryActivity)cardView.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

}

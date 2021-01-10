package com.aaronevan.binusezyfoody.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.activity.DetailActivity;
import com.aaronevan.binusezyfoody.activity.HistoryActivity;
import com.aaronevan.binusezyfoody.activity.OrderActivity;
import com.aaronevan.binusezyfoody.binusclass.HistoryHeader;
import com.aaronevan.binusezyfoody.binusclass.Product;

import java.util.ArrayList;

public class AdapterHistoryHeader extends RecyclerView.Adapter<AdapterHistoryHeader.MyViewHolder> {
    private ArrayList<HistoryHeader> historyHeaders;
    public ArrayList<Product> myOrder;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        Context context;
        public MyViewHolder(CardView v) {
            super(v);
            cardview = v;
            context = v.getContext();
        }
    }

    public AdapterHistoryHeader(ArrayList<HistoryHeader> historyHeader, ArrayList<Product> myOrder) {
        this.historyHeaders = historyHeader;
        this.myOrder = myOrder;
    }

    @NonNull
    @Override
    public AdapterHistoryHeader.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.historyheader_layout, parent, false);
        return new MyViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final CardView cardView = holder.cardview;

        TextView alamat = cardView.findViewById(R.id.alamat);
        TextView tanggal = cardView.findViewById(R.id.tanggal);
        TextView total = cardView.findViewById(R.id.total);

        HistoryHeader temp = historyHeaders.get(position);

//        image.setImageResource(temp.getImageProduct());
        alamat.setText("Alamat : " + temp.getAlamat());
        tanggal.setText(temp.getTanggal());
        total.setText("Total Rp. " + temp.getTotal().toString());

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                HistoryHeader obj = historyHeaders.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(cardView.getContext(), DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putSerializable("objHeader", obj);
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);
                cardView.getContext().startActivity(intent);
                ((HistoryActivity)cardView.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyHeaders.size();
    }

}

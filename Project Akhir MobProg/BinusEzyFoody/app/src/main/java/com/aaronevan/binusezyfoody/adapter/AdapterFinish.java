package com.aaronevan.binusezyfoody.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.binusclass.Product;

import java.util.ArrayList;

public class AdapterFinish extends RecyclerView.Adapter<AdapterFinish.MyViewHolder> {
    public ArrayList<Product> myOrder;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        public MyViewHolder(CardView v) {
            super(v);
            cardview = v;
        }
    }

    public AdapterFinish(ArrayList<Product> myOrder) {
        this.myOrder = myOrder;
    }

    @NonNull
    @Override
    public AdapterFinish.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.finish_layout, parent, false);
        return new MyViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final CardView cardView = holder.cardview;

        TextView nama = cardView.findViewById(R.id.idnama);
        TextView qty  = cardView.findViewById(R.id.idqty);
        TextView price = cardView.findViewById(R.id.idprice);

        Product temp = myOrder.get(position);

        nama.setText(temp.getNamaProduct());
        qty.setText(Integer.toString(temp.getQuantityProduct())+" x ");
        price.setText("Rp. " + Integer.toString(temp.getHargaProduct()));
    }

    @Override
    public int getItemCount() {
        return myOrder.size();
    }

}

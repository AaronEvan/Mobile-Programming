package com.aaronevan.binusezyfoody.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aaronevan.binusezyfoody.activity.MyOrderActivity;
import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.binusclass.Product;

import java.util.ArrayList;

public class AdapterMyOrder extends RecyclerView.Adapter<AdapterMyOrder.MyViewHolder> {
    public ArrayList<Product> myOrder;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        public MyViewHolder(CardView v) {
            super(v);
            cardview = v;
        }
    }

    public AdapterMyOrder(ArrayList<Product> myOrder) {
        this.myOrder = myOrder;
    }

    @NonNull
    @Override
    public AdapterMyOrder.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.myorder_layout, parent, false);
        return new MyViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final CardView cardView = holder.cardview;

        TextView nama = cardView.findViewById(R.id.idnama);
        TextView qty  = cardView.findViewById(R.id.idqty);
        TextView price = cardView.findViewById(R.id.idprice);
        Button btn = cardView.findViewById(R.id.deleteBtn);

        Product temp = myOrder.get(position);

        nama.setText(temp.getNamaProduct());
        qty.setText(Integer.toString(temp.getQuantityProduct())+" x ");
        price.setText("Rp. " + Integer.toString(temp.getHargaProduct()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cardView.getContext(),"Success Delete " + myOrder.get(position).getNamaProduct() + "!",Toast.LENGTH_SHORT).show();
                myOrder.remove(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(cardView.getContext(), MyOrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);
                cardView.getContext().startActivity(intent);
                ((MyOrderActivity)cardView.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrder.size();
    }

}

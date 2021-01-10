package com.aaronevan.binusezyfoody.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aaronevan.binusezyfoody.R;
import com.aaronevan.binusezyfoody.activity.DrinksActivity;
import com.aaronevan.binusezyfoody.activity.FoodActivity;
import com.aaronevan.binusezyfoody.activity.OrderActivity;
import com.aaronevan.binusezyfoody.binusclass.Product;

import java.util.ArrayList;

public class AdapterListFood extends RecyclerView.Adapter<AdapterListFood.MyViewHolder> {
    private ArrayList<Product> arrdrink;
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

    public AdapterListFood(ArrayList<Product> arrdrink, ArrayList<Product> myOrder) {
        this.arrdrink = arrdrink;
        this.myOrder = myOrder;
    }

    @NonNull
    @Override
    public AdapterListFood.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final CardView cardView = holder.cardview;
//        ImageView imageView =  cardView.findViewById(R.id.idimage);
        ImageView image = cardView.findViewById(R.id.idimage);
        TextView nama = cardView.findViewById(R.id.idnama);
        TextView price = cardView.findViewById(R.id.idprice);
        TextView stock = cardView.findViewById(R.id.idstock);

        Product temp = arrdrink.get(position);

        image.setImageResource(temp.getImageProduct());
        nama.setText(temp.getNamaProduct());
        price.setText("Rp. " + Integer.toString(temp.getHargaProduct()));
        stock.setText("Stock : " + Integer.toString(temp.getStockProduct()));

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Product obj = arrdrink.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(cardView.getContext(), OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putSerializable("objDrink", obj);
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);
                cardView.getContext().startActivity(intent);
                ((FoodActivity)cardView.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrdrink.size();
    }

}

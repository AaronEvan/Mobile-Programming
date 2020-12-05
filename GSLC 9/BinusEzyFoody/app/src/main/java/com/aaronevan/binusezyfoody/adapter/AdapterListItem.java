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

import com.aaronevan.binusezyfoody.binusclass.Drink;
import com.aaronevan.binusezyfoody.activity.DrinksActivity;
import com.aaronevan.binusezyfoody.activity.OrderActivity;
import com.aaronevan.binusezyfoody.R;

import java.util.ArrayList;

public class AdapterListItem extends RecyclerView.Adapter<AdapterListItem.MyViewHolder> {
    private ArrayList<Drink> arrdrink;
    public ArrayList<Drink> myOrder;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        Context context;
        public MyViewHolder(CardView v) {
            super(v);
            cardview = v;
            context = v.getContext();
        }
    }

    public AdapterListItem(ArrayList<Drink> arrdrink, ArrayList<Drink> myOrder) {
        this.arrdrink = arrdrink;
        this.myOrder = myOrder;
    }

    @NonNull
    @Override
    public AdapterListItem.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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

        Drink temp = arrdrink.get(position);

        image.setImageResource(temp.getImage());
        nama.setText(temp.getName());
        price.setText("Rp. " + Integer.toString(temp.getPrice()));

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Drink obj = arrdrink.get(position);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(cardView.getContext(), OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                bundle.putSerializable("objDrink", obj);
                bundle.putSerializable("myOrder", myOrder);
                intent.putExtras(bundle);
                cardView.getContext().startActivity(intent);
                ((DrinksActivity)cardView.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrdrink.size();
    }

}

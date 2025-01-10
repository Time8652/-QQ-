package com.example.myqq.MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myqq.News.ContactNew;
import com.example.myqq.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<ContactNew> newsArrayList;

    public ContactAdapter(Context context, ArrayList<ContactNew> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ContactNew contactNew = newsArrayList.get(position);
        holder.tvHeading.setText(contactNew.heading);
        holder.titleImage.setImageResource(contactNew.titleImage);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout list_item;
        ShapeableImageView titleImage;
        TextView tvHeading;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_item = itemView.findViewById(R.id.list_item);
            titleImage = itemView.findViewById(R.id.image);
            tvHeading = itemView.findViewById(R.id.title);
        }
    }
}

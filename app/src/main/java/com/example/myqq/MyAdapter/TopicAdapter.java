package com.example.myqq.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myqq.News.TopicNew;
import com.example.myqq.R;

import java.util.ArrayList;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<TopicNew> topicNewArrayList;
    private boolean check_star = false;

    public TopicAdapter(Context context, ArrayList<TopicNew> topicNewArrayList) {
        this.context = context;
        this.topicNewArrayList = topicNewArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_topic, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TopicNew topicNew = topicNewArrayList.get(position);
        Glide.with(context).load(topicNew.head_url).into(holder.imageView_head);
        holder.tv_name.setText(topicNew.name);
        holder.tv_time.setText(topicNew.time);
        holder.tv_text.setText(topicNew.text);
        holder.topic_star.setText(String.valueOf(topicNew.star));
        Glide.with(context).load(topicNew.work_url).into(holder.imageView_work);
        holder.linearLayout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.linearLayout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.constraintLayout.setVisibility(View.VISIBLE);
            }
        });
        holder.linearLayout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check_star) {
                    holder.topic_thumb.setImageResource(R.drawable.topic_thumb_purple);
                    holder.topic_star.setText(String.valueOf(topicNew.star + 1));
                }
            }
        });
        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.editText.setText("");
                holder.constraintLayout.setVisibility(View.GONE);
                Toast.makeText(v.getContext(), "发表成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicNewArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_head, imageView_work, topic_thumb;
        TextView tv_name, tv_time, tv_text, topic_star, send;
        LinearLayout linearLayout_1, linearLayout_2, linearLayout_3;
        EditText editText;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_head = itemView.findViewById(R.id.imageView);
            tv_name = itemView.findViewById(R.id.name);
            tv_time = itemView.findViewById(R.id.time);
            tv_text = itemView.findViewById(R.id.textView);
            imageView_work = itemView.findViewById(R.id.imageView_1);
            linearLayout_1 = itemView.findViewById(R.id.linearLayout_1);
            linearLayout_2 = itemView.findViewById(R.id.linearLayout_2);
            linearLayout_3 = itemView.findViewById(R.id.linearLayout_3);
            topic_thumb = itemView.findViewById(R.id.topic_thumb);
            topic_star = itemView.findViewById(R.id.topic_star);
            editText = itemView.findViewById(R.id.editText);
            send = itemView.findViewById(R.id.send);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}

package com.example.myqq.MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myqq.News.News;
import com.example.myqq.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<News> newsArrayList;
    private OnItemClickListener onItemClickListener;

    // 接口定义
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public MyAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.qq_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        News news = newsArrayList.get(position);
        holder.tvHeading.setText(news.heading);
        holder.tvMessage.setText(news.message);
        holder.titleImage.setImageResource(news.titleImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout list_item;
        ShapeableImageView titleImage;
        TextView tvHeading;
        TextView tvMessage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_item = itemView.findViewById(R.id.list_item);
            titleImage = itemView.findViewById(R.id.image);
            tvHeading = itemView.findViewById(R.id.title);
            tvMessage = itemView.findViewById(R.id.content);
        }
    }

    public static class SwipeController extends ItemTouchHelper.Callback {
        private final MyAdapter adapter;

        public SwipeController(MyAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return 0;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true; // 长按拖拽
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true; // 启用滑动删除
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipeDir) {
            // 根据滑动方向删除项目
            if (swipeDir == ItemTouchHelper.START) { // 左滑
                adapter.remove(viewHolder.getAdapterPosition());
            }
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            // 可以在这里添加自定义的滑动动画效果
        }
    }

    // 在适配器中添加一个方法来处理删除
    public void remove(int position) {
        newsArrayList.remove(position);
        notifyItemRemoved(position);
    }
}

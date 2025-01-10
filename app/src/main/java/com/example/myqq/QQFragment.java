package com.example.myqq;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myqq.MyAdapter.MyAdapter;
import com.example.myqq.News.News;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class QQFragment extends Fragment {
    private ArrayList<News> newsArrayList;
    private String[] newsHeading, newsMessage;
    private int[] imageResourceID;
    private ItemTouchHelper helper;
    private static String textView, text;
    private static int img;

    @SuppressLint("ValidFragment")
    QQFragment(String number) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_q, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();
        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(staggeredGridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(getContext(), newsArrayList);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (getContext() != null) {
                    Intent intent = new Intent(getContext(), IntelligentActivity.class);
                    startActivity(intent);
                    textView = newsHeading[position];
                    text = newsMessage[position];
                    img = imageResourceID[position];
                }
            }
        });
        recyclerview.setAdapter(myAdapter);
        ItemTouchHelper.Callback callback = new MyAdapter.SwipeController(myAdapter);
        helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerview);
        myAdapter.notifyDataSetChanged();
    }

    public static String getTextView() {
        return textView;
    }

    public static String getText() {
        return text;
    }

    public static int getImg() {
        return img;
    }

    private void dataInitialize() {
        newsArrayList = new ArrayList<>();
        newsHeading = new String[]{
                "我的电脑",
                "QQ邮箱提醒",
                "订阅号消息",
                "花舞花落泪",
                "如花的旋律",
                "代价是折磨",
                "倚靠窗畔",
                "紫色的彩虹",
                "伊人泪满面",
                "微醉阳光"
        };

        newsMessage = new String[]{
                "[图片]Screenshot_20240401",
                "【QQ开放平台】 感谢您的使用",
                "第十一期青年大学习",
                "[动画表情]",
                "对方已成功接收文件“ ”",
                "你已完成了4月1日作业",
                "[金山文档] 2023-2024学年第二学期",
                "@全体成员 第6周登记表已发布",
                "你邀请某某加入了群聊",
                "谋某某加入了群聊"
        };

        imageResourceID = new int[]{
                R.drawable.head_0,
                R.drawable.head_1,
                R.drawable.head_2,
                R.drawable.head_3,
                R.drawable.head_4,
                R.drawable.head_5,
                R.drawable.head_6,
                R.drawable.head_7,
                R.drawable.head_8,
                R.drawable.head_9
        };

        for (int i = 0; i < 10; i++){
            News news = new News(newsHeading[i], newsMessage[i], imageResourceID[i]);
            newsArrayList.add(news);
        }
    }
}

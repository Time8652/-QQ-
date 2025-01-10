package com.example.myqq;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myqq.MyAdapter.ContactAdapter;
import com.example.myqq.News.ContactNew;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ContactFragment extends Fragment {

    private ImageView class_more1, class_more2;
    private RecyclerView listView1, listView2;
    private ArrayList<ContactNew> contactNewArrayList1, contactNewArrayList2;
    private String[] newsHeading1, newsHeading2;
    private int[] imageResourceID1, imageResourceID2;
    private boolean classIfOpen1 = false, classIfOpen2 = false;

    @SuppressLint("ValidFragment")
    ContactFragment(String number) {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        class_more1 = view.findViewById(R.id.class_more1);
        class_more2 = view.findViewById(R.id.class_more2);
        listView1 = view.findViewById(R.id.listView1);
        listView2 = view.findViewById(R.id.listView2);
        //处理点击事件
        initOnClickListener();
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    private void initData() {
        class_more1.setImageResource(R.drawable.expand_more_white);
        class_more2.setImageResource(R.drawable.expand_more_white);
        listView1.setVisibility(View.GONE);
        listView2.setVisibility(View.GONE);
        contactNewArrayList1 = new ArrayList<>();
        newsHeading1 = new String[]{
                "我的电脑",
                "QQ邮箱提醒",
                "订阅号消息"
        };

        imageResourceID1 = new int[]{
                R.drawable.head_0,
                R.drawable.head_1,
                R.drawable.head_2
        };

        for (int i = 0; i < newsHeading1.length; i++){
            ContactNew contactNew1 = new ContactNew(newsHeading1[i], imageResourceID1[i]);
            contactNewArrayList1.add(contactNew1);
        }

        contactNewArrayList2 = new ArrayList<>();
        newsHeading2 = new String[]{
                "花舞花落泪",
                "如花的旋律",
                "代价是折磨",
                "倚靠窗畔",
                "紫色的彩虹",
                "伊人泪满面",
                "微醉阳光"
        };

        imageResourceID2 = new int[]{
                R.drawable.head_3,
                R.drawable.head_4,
                R.drawable.head_5,
                R.drawable.head_6,
                R.drawable.head_7,
                R.drawable.head_8,
                R.drawable.head_9
        };

        for (int i = 0; i < newsHeading2.length; i++){
            ContactNew contactNew2 = new ContactNew(newsHeading2[i], imageResourceID2[i]);
            contactNewArrayList2.add(contactNew2);
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager1 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        listView1.setLayoutManager(staggeredGridLayoutManager1);
        ContactAdapter contactAdapter1 = new ContactAdapter(getContext(), contactNewArrayList1);
        listView1.setAdapter(contactAdapter1);
        StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        listView2.setLayoutManager(staggeredGridLayoutManager2);
        ContactAdapter contactAdapter2 = new ContactAdapter(getContext(), contactNewArrayList2);
        listView2.setAdapter(contactAdapter2);
    }

    private void initOnClickListener() {
        class_more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!classIfOpen1){
                    if(classIfOpen2){
                        listView2.clearAnimation();
                        listView2.setVisibility(View.GONE);
                        class_more2.setImageResource(R.drawable.expand_more_white);
                        classIfOpen2 = !classIfOpen2;
                    }
                    //open
                    listView1.setVisibility(View.VISIBLE);
                    class_more1.setImageResource(R.drawable.expand_less_white);
                    TranslateAnimation animation = new TranslateAnimation(
                            Animation.ABSOLUTE, 0f,
                            Animation.ABSOLUTE, 0f,
                            Animation.RELATIVE_TO_SELF, -1f,
                            Animation.RELATIVE_TO_SELF, 0f
                    );
                    animation.setDuration(300);
                    animation.setFillAfter(true);
                    animation.setInterpolator(new DecelerateInterpolator());
                    listView1.startAnimation(animation);
                }else {
                    //close
                    listView1.clearAnimation();
                    listView1.setVisibility(View.GONE);
                    class_more1.setImageResource(R.drawable.expand_more_white);
                }
                classIfOpen1 = !classIfOpen1;
            }
        });
        class_more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!classIfOpen2){
                    if(classIfOpen1){
                        listView1.clearAnimation();
                        listView1.setVisibility(View.GONE);
                        class_more1.setImageResource(R.drawable.expand_more_white);
                        classIfOpen1 = !classIfOpen1;
                    }
                    //open
                    listView2.setVisibility(View.VISIBLE);
                    class_more2.setImageResource(R.drawable.expand_less_white);
                    TranslateAnimation animation = new TranslateAnimation(
                            Animation.ABSOLUTE, 0f,
                            Animation.ABSOLUTE, 0f,
                            Animation.RELATIVE_TO_SELF, -1f,
                            Animation.RELATIVE_TO_SELF, 0f
                    );
                    animation.setDuration(300);
                    animation.setFillAfter(true);
                    animation.setInterpolator(new DecelerateInterpolator());
                    listView2.startAnimation(animation);
                }else {
                    //close
                    listView2.clearAnimation();
                    listView2.setVisibility(View.GONE);
                    class_more2.setImageResource(R.drawable.expand_more_white);
                }
                classIfOpen2 = !classIfOpen2;
            }
        });
    }
}

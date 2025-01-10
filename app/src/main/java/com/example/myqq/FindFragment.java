package com.example.myqq;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myqq.MyAdapter.TopicAdapter;
import com.example.myqq.News.TopicNew;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FindFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<TopicNew> topicNewArrayList;
    private int[] head_url, work_url;
    private String[] name, time, text;
    private int[] star;

    @SuppressLint("ValidFragment")
    FindFragment(String number) {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        initData();
        TopicAdapter topicAdapter = new TopicAdapter(getContext(), topicNewArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();
        return view;
    }

    private void initData() {
        topicNewArrayList = new ArrayList<>();
        head_url = new int[]{
                R.drawable.head_6,
                R.drawable.head_7,
                R.drawable.head_8
        };

        work_url = new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };

        name = new String[]{
                "倚靠窗畔",
                "紫色的彩虹",
                "伊人泪满面",
        };

        time = new String[]{
                "12.19 12:00",
                "12.20 12:12",
                "12.22 6:38"
        };

        text = new String[]{
                "#传统文化之美：我心中的中国风# 在我心中，中国风不仅仅是一种视觉上的审美享受，它是流淌在华夏儿女血脉中的文化基因，是历经千年而不衰的精神家园。每当提及中国风，我的思绪便不由自主地飘向那些古老而又鲜活的画面：从青花瓷上淡雅脱俗的蓝白交织，到水墨画中留白处的无限遐想；从京剧脸谱上斑斓多姿的色彩，到书法笔触间行云流水的意境……每一处细节，都蕴含着深厚的文化底蕴和独特的艺术魅力。",
                "在我心中，中国风不仅仅是一种视觉上的审美享受，它是流淌在华夏儿女血脉中的文化基因，是历经千年而不衰的精神家园。每当提及中国风，我的思绪便不由自主地飘向那些古老而又鲜活的画面：从青花瓷上淡雅脱俗的蓝白交织，到水墨画中留白处的无限遐想；从京剧脸谱上斑斓多姿的色彩，到书法笔触间行云流水的意境……每一处细节，都蕴含着深厚的文化底蕴和独特的艺术魅力。",
                "在我心中，中国风不仅仅是一种视觉上的审美享受，它是流淌在华夏儿女血脉中的文化基因，是历经千年而不衰的精神家园。每当提及中国风，我的思绪便不由自主地飘向那些古老而又鲜活的画面：从青花瓷上淡雅脱俗的蓝白交织，到水墨画中留白处的无限遐想；从京剧脸谱上斑斓多姿的色彩，到书法笔触间行云流水的意境……每一处细节，都蕴含着深厚的文化底蕴和独特的艺术魅力。"
        };

        star = new int[]{
                22,
                23,
                33
        };

        for (int i = 0; i < head_url.length; i++){
            TopicNew topicNew = new TopicNew(head_url[i], work_url[i], name[i], time[i], text[i], star[i]);
            topicNewArrayList.add(topicNew);
        }
    }
}

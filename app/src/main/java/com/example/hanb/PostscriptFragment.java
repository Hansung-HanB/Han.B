package com.example.hanb;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class PostscriptFragment extends Fragment {
    private RecyclerView recyclerView; //목록 뷰 선언
    private PostscriptAdapter adapter; //어댑터
    private Button writeButton_postscript; //글쓰기 버튼
    private ArrayList<PostscriptData> list = new ArrayList<>(); //배열 형태

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_postscript, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_main_list); // 개별 행 모양 불러오기
        //list = PostscriptData.createContactsList(10); // 보여줄 행 개수
        list = PostscriptData.createContactsList(); // 보여줄 행 개수
        recyclerView.setHasFixedSize(true); 
        adapter = new PostscriptAdapter(getActivity(), list); //어댑터에 PostscriptData에서 불러온 배열 저장
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // 리사이클러뷰 레이아웃 지정
        recyclerView.setAdapter(adapter); //리사이클러뷰 어댑터 설정

        //후기 쓰기 버튼
        writeButton_postscript = rootView.findViewById(R.id.writeButton_postscript);
        writeButton_postscript.setOnClickListener(v -> {
            writeButton_postscript.setForegroundTintList(ColorStateList.valueOf(Color.parseColor("#5D5D5D")));
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            WritePostscriptFragment writePostscriptFragment = new WritePostscriptFragment();
            transaction.replace(R.id.main_frame, writePostscriptFragment);
            transaction.commit();
        });

        return rootView;
    }
}
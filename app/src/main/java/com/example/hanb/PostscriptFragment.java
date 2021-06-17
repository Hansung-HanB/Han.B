package com.example.hanb;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class PostscriptFragment extends Fragment {
    private RecyclerView recyclerView; //목록 뷰 선언
    private PostscriptAdapter adapter; //어댑터
    private ArrayList<PostscriptData> list = new ArrayList<>(); //배열 형태
    private Button writeButton_postscript; //글쓰기 버튼
    Handler handler;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_postscript, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview_main_list); // 개별 행 모양 불러오기
        list = PostscriptData.createContactsList(); // 보여줄 행 개수
        recyclerView.setHasFixedSize(true);
        adapter = new PostscriptAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();

        handler = new Handler();
        handler.postDelayed(() -> {
//            list = PostscriptData.createContactsList(); // 보여줄 행 개수
//            recyclerView.setHasFixedSize(true);
//            adapter = new PostscriptAdapter(getActivity(),list);
//            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }, 110);

        // 목록 마지막 확인
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount) {
                    Toast.makeText(getContext(), "더 이상 불러올 게시글이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 목록 새로고침 기능
        SwipeRefreshLayout mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.detach(PostscriptFragment.this).attach(PostscriptFragment.this);
            ft.commit();
            final Handler handler = new Handler();
            handler.postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 500);
        });

        //후기 쓰기 버튼
        writeButton_postscript = rootView.findViewById(R.id.writeButton_postscript);
        writeButton_postscript.setOnClickListener(v -> {
            // 버튼 눌릴 때 색변경
            writeButton_postscript.setForegroundTintList(ColorStateList.valueOf(Color.parseColor("#A7D5F2")));
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            WritePostscriptFragment writePostscriptFragment = new WritePostscriptFragment();
            transaction.replace(R.id.main_frame, writePostscriptFragment);
            transaction.commit();
            });

        return rootView;
    }
}
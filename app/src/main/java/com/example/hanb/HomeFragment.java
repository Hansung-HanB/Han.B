package com.example.hanb;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private View view;
//    private TextView userID_home;
    RecyclerView mRecyclerView_recommend = null;
    RecyclerView mRecyclerView_rank = null;
    ProgramRecommendAdaptor mAdapter = null;
    ProgramRankAdaptor adpater = null;
    ArrayList<ProgramRecommendItem> mList = new ArrayList<ProgramRecommendItem>();
    ArrayList<ProgramRankItem> mData = new ArrayList<ProgramRankItem>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addItem(String program, String grade) {
        ProgramRecommendItem item = new ProgramRecommendItem();
        item.setRecommendProgram(program);
        item.setProgramGrade(grade);
        mList.add(item);
    }

    public void addItem2(String program, String grade) {
        ProgramRankItem item2 = new ProgramRankItem();
        item2.setRankProgram(program);
        item2.setRankProgramGrade(grade);
        mData.add(item2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //userID_home = view.findViewById(R.id.point_home);

        // 안드로이드 자체에 xml로 저장된 현재 로그인 한 학번 가져오기
        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        //String inputID = sharedPreferences.getString("inputID", "");
        //userID_home.setText(inputID);

        mRecyclerView_recommend = view.findViewById(R.id.recyclerView_recommend_home);
        mAdapter = new ProgramRecommendAdaptor(mList);
        mRecyclerView_recommend.setAdapter(mAdapter);

        mRecyclerView_rank = view.findViewById(R.id.recyclerView_ranking_home);
        adpater = new ProgramRankAdaptor(mData);
        mRecyclerView_rank.setAdapter(adpater);

        mRecyclerView_recommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView_rank.setLayoutManager(new LinearLayoutManager(getActivity()));

        for(int i=0; i<=10; i++) {
            addItem("test", "5");
        }

        for(int i=0; i<=10; i++) {
            addItem2("test2", "3");
        }

        mAdapter.notifyDataSetChanged();
        adpater.notifyDataSetChanged();

        return view;
    }
}

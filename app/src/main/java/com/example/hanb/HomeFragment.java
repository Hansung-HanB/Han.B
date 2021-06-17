package com.example.hanb;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private View view;
    static ArrayList<ProgramRankItem> mData = new ArrayList<>();
    static String rank_program, rank_average;
    RecyclerView mRecyclerView_recommend = null;
    RecyclerView mRecyclerView_rank = null;
    ProgramRecommendAdaptor mAdapter = null;
    ProgramRankAdaptor adpater = null;
    ArrayList<ProgramRecommendItem> mList = new ArrayList<>();

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

    static void addItem2(String program, String grade) {
        ProgramRankItem item2 = new ProgramRankItem();
        item2.setRankProgram(program);
        item2.setRankProgramGrade(grade);
        mData.add(item2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView_recommend = view.findViewById(R.id.recyclerView_recommend_home);
        mAdapter = new ProgramRecommendAdaptor(mList);
        mRecyclerView_recommend.setAdapter(mAdapter);

        mRecyclerView_rank = view.findViewById(R.id.recyclerView_ranking_home);
        adpater = new ProgramRankAdaptor(mData);
        mRecyclerView_rank.setAdapter(adpater);

        mRecyclerView_recommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView_rank.setLayoutManager(new LinearLayoutManager(getActivity()));

        String serverUrl="http://15.164.102.181//rank.php";

        //결과를 JsonArray 받으므로 StringRequest가 아니라 JsonArrayRequest를 사용
        //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, response -> {
            //파라미터로 응답받은 결과 JsonArray를 분석
            mData.clear();
            try {
                for(int i=0;i<10;i++){
                    JSONObject jsonObject= response.getJSONObject(i);
                    rank_program=jsonObject.getString("program");
                    rank_average=jsonObject.getString("avg(ratingbar)");
                    addItem2(rank_program, rank_average);
                }
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> {
        });

        RequestQueue requestQueue= Volley.newRequestQueue(MyApplication.ApplicationContext());
        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);

        mAdapter.notifyDataSetChanged();
        adpater.notifyDataSetChanged();


        addItem("한성토익강좌", "5");
        addItem("내외국인 재학생 연합봉사단", "4.3");
        addItem("영자신문사", "4.2");
        addItem("한성영어캠프", "4");
        addItem("또래상담", "3.8");
        addItem("학습능력향상 튜터링", "3.5");
        addItem("open activity", "3.2");
        addItem("집단상담프로그램", "3");
        addItem("한성대신문사", "2.7");
        addItem("총장님과 식사", "2.5");



        return view;
    }
}

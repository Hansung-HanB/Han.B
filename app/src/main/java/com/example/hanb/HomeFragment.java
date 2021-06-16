package com.example.hanb;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private View view;
//    private TextView userID_home;
    RecyclerView mRecyclerView_recommend = null;
    RecyclerView mRecyclerView_rank = null;
    ProgramRecommendAdaptor mAdapter = null;
    ProgramRankAdaptor adpater = null;
    ArrayList<ProgramRecommendItem> mList = new ArrayList<ProgramRecommendItem>();
    static ArrayList<ProgramRankItem> mData = new ArrayList<ProgramRankItem>();
    static String rank_program;
    static String rank_average;


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

        String serverUrl="http://15.164.102.181//rank.php";

        //결과를 JsonArray 받으므로 StringRequest가 아니라 JsonArrayRequest를 사용

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(MyApplication.ApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                //파라미터로 응답받은 결과 JsonArray를 분석
                //mData.clear();
                try {
                    for(int i=0;i<10;i++){
                        //for(int i=0;i<numContacts;i++){
                        JSONObject jsonObject= response.getJSONObject(i);
                        rank_program=jsonObject.getString("program");
                        rank_average=jsonObject.getString("avg(ratingbar)");
                        addItem2(rank_program, rank_average);
                        // PostscriptData 리스트형태로 저장
                        //mData.add( new ProgramRankItem()); //위에 것 이 가장 최근 것
                    }
                } catch (JSONException e) {e.printStackTrace();}
            }
        }, error -> {
            //Toast.makeText(MyApplication.ApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
        });


        for(int i=0; i<=10; i++) {
            addItem("test", "5");
        }

        //for(int i=1; i<=10; i++) {
          //addItem2(rank_program, Integer.toString(i + 1));
      // }

        RequestQueue requestQueue= Volley.newRequestQueue(MyApplication.ApplicationContext());
        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);

        mAdapter.notifyDataSetChanged();
        adpater.notifyDataSetChanged();

        return view;

    }
}

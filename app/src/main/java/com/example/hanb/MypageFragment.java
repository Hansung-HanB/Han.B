package com.example.hanb;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MypageFragment extends Fragment {
    private View view;
    private TextView userName_field, userID_field, userMajor_field, userPoint_field, remainPoint_field;
    private TextView signOutBtn_mypage;
    static String userID_comp;
    static String userName;
    static String userMajor;
    static String userPoint;
    static String remainPoint;
    static int remainPoint_int;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mypage, container, false);
        SharedPreferences prefs = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        String userID = prefs.getString("inputID", "");

        userName_field = view.findViewById(R.id.userName_field);
        userID_field = view.findViewById(R.id.userID_field);
        userMajor_field = view.findViewById(R.id.userMajor_field);
        userPoint_field = view.findViewById(R.id.userPoint_field);
        remainPoint_field = view.findViewById(R.id.remainPoint_field);
        signOutBtn_mypage = view.findViewById(R.id.signOutBtn_mypage);

        userID_field.setText(userID);
        //String serverURL = String.format("http://15.164.102.181/mypage.php?ID=%s", userID);
        String serverURL = "http://15.164.102.181//mypage_test.php";

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverURL, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(MyApplication.ApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                //파라미터로 응답받은 결과 JsonArray를 분석
                //mData.clear();
                try {
                    for(int i=0;i<response.length();i++){
                        //for(int i=0;i<numContacts;i++){
                        JSONObject jsonObject= response.getJSONObject(i);
                        userID_comp=jsonObject.getString("userID");
                        userName=jsonObject.getString("userName");
                        userMajor=jsonObject.getString("userMajor");
                        userPoint=jsonObject.getString("userPoint");
                        remainPoint_int=800-Integer.parseInt(jsonObject.getString("userPoint"));
                        remainPoint=Integer.toString(remainPoint_int);
                        //Toast.makeText(getActivity(), userID_comp+" "+userName+" "+userMajor+" "+userPoint, Toast.LENGTH_SHORT).show();
                        if(userID_comp.equals(userID)){
                            userMajor_field.setText(userMajor);
                            userName_field.setText(userName);
                            userPoint_field.setText(userPoint);
                            remainPoint_field.setText(remainPoint);
                            //Toast.makeText(getActivity(), userID_comp+" "+userName+" "+userMajor+" "+userPoint, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {e.printStackTrace();}
            }
        }, error -> {
            //Toast.makeText(MyApplication.ApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
        });

        RequestQueue requestQueue= Volley.newRequestQueue(MyApplication.ApplicationContext());
        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);



        // 마이페이지에서 로그아웃 클릭시 로그인 화면으로 이동
        signOutBtn_mypage.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
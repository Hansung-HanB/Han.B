package com.example.hanb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MypageFragment extends Fragment {
    private View view;
    private TextView userName_field, userID_field, userMajor_field, userPoint_field, remainPoint_field, signOutBtn_mypage;
    static String userID_comp, userName, userMajor, userPoint, remainPoint;
    static int userPoint_int, remainPoint_int;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, container, false);
        SharedPreferences prefs = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        String userID = prefs.getString("inputID", "");

        userName_field = view.findViewById(R.id.userName_field);
        userID_field = view.findViewById(R.id.userID_field);
        userMajor_field = view.findViewById(R.id.userMajor_field);
        userPoint_field = view.findViewById(R.id.userPoint_field);
        remainPoint_field = view.findViewById(R.id.remainPoint_field);
        signOutBtn_mypage = view.findViewById(R.id.signOutBtn_mypage);

        userID_field.setText(userID);

        String serverURL = "http://15.164.102.181//mypage_test.php";

        //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverURL, null, response -> {
            //파라미터로 응답받은 결과 JsonArray를 분석
            try {
                for(int i=0;i<response.length();i++){
                    JSONObject jsonObject= response.getJSONObject(i);
                    userID_comp=jsonObject.getString("userID");
                    userName=jsonObject.getString("userName");
                    userMajor=jsonObject.getString("userMajor");
                    userPoint=jsonObject.getString("userPoint");
                    userPoint_int=Integer.parseInt(jsonObject.getString("userPoint"));
                    remainPoint_int=800-Integer.parseInt(jsonObject.getString("userPoint"));
                    remainPoint=Integer.toString(remainPoint_int);
                    if(userID_comp.equals(userID)){
                        userMajor_field.setText(userMajor);
                        userName_field.setText(userName);
                        userPoint_field.setText(userPoint);
                        if(userPoint_int>=0 && userPoint_int<=800){
                            remainPoint_field.setText(remainPoint);
                        } else remainPoint_field.setText("0");
                     }
                }
            } catch (JSONException e) {e.printStackTrace();}
        }, error -> {
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
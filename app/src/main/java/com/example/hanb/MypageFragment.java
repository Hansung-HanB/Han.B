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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

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
        String serverURL = String.format("http://15.164.102.181/mypage.php?ID=%s", userID);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("student");
                    JSONObject data = jsonArray.getJSONObject(0);

                    String userName = data.getString("userName");
                    String userMajor = data.getString("userMajor");
                    int userPoint = data.getInt("userPoint");

                    userName_field.setText(userName);
                    userMajor_field.setText(userMajor);
                    userPoint_field.setText(userPoint);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Volley Login Error " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });




        // 마이페이지에서 로그아웃 클릭시 로그인 화면으로 이동
        signOutBtn_mypage.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
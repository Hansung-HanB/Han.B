package com.example.hanb;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class PostscriptData extends AppCompatActivity {
    public String program;
    public String postscript;
    public float ratingbar;
    static int num;

    // 화면에 표시될 문자열 초기화
    public PostscriptData(String program, String postscript,float ratingbar) {
        this.program = program;
        this.postscript = postscript;
        this.ratingbar = ratingbar;
    }
    
    // 입력받은 숫자의 리스트생성
    //public static ArrayList<PostscriptData> createContactsList(int numContacts) {
    public static ArrayList<PostscriptData> createContactsList() {
        ArrayList<PostscriptData> contacts = new ArrayList<PostscriptData>();

        //서버의 loadDBtoJson.php파일에 접속하여 (DB데이터들)결과 받기
        //Volley+ 라이브러리 사용
        //서버주소
        String serverUrl="http://15.164.102.181/reviews3.php";

        //결과를 JsonArray 받으므로 StringRequest가 아니라 JsonArrayRequest를 사용
        String program = "";
        String postscript ="";
        float ratingbar = 0;
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(MyApplication.ApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                //파라미터로 응답받은 결과 JsonArray를 분석
                contacts.clear();
                try {
                    num=response.length();
                    for(int i=0;i<response.length();i++){
                    //for(int i=0;i<numContacts;i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        String program=jsonObject.getString("program");
                        String postscript=jsonObject.getString("postscript");
                        float ratingbar= Float.parseFloat(jsonObject.getString("ratingbar")); //no가 문자열이라서 바꿔야함.
                        String UserID=jsonObject.getString("userID");

                        // PostscriptData 리스트형태로 저장
                        contacts.add(new PostscriptData(program,postscript,ratingbar));
                    }
                } catch (JSONException e) {e.printStackTrace();}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MyApplication.ApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        //실제 요청 작업을 수행해주는 요청큐 객체 생성
        RequestQueue requestQueue= Volley.newRequestQueue(MyApplication.ApplicationContext());
        //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);

        for (int i = 1; i <= num; i++) {
        //for (int i = 1; i <= numContacts; i++) {
            contacts.add(new PostscriptData(program, postscript, ratingbar));
        }

        return contacts;
    }
}
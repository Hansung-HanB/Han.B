package com.example.hanb;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RankRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://15.164.102.181//rank.php";
    private Map<String, String> map;

    public RankRequest(String program, float ratingbar, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("program",program);
        map.put("ratingbar", ratingbar + "");
    }

    @Override
    protected Map<String, String> getParams() {
        return map;
    }
}

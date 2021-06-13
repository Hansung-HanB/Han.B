package com.example.hanb;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class ReviewRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://15.164.102.181//Register3.php";
    private Map<String, String> map;

    public ReviewRequest(String program, String postscript, float ratingbar, String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();

        map.put("program", program);
        map.put("postscript", postscript);
        map.put("ratingbar",ratingbar+"");
        map.put("userID", userID);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
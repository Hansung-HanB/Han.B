package com.example.hanb;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WritePostscriptFragment extends Fragment {
    private View view;
    private Button enrollButton_write_postscript;
    private SearchView search_write_postscript;
    private ConstraintLayout write_postscript_layout;
    private RatingBar ratingBar_write_postscript;
    private EditText psText_write_postscript;
    //ViewGroup viewGroup;



    //public String userID;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        //viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_write_postscript,container,false);
        //userID = ((LoginActivity) getActivity()).findViewById(R.id.userNameText_login);
        view = inflater.inflate(R.layout.fragment_write_postscript, container, false);



        //Bundle 받기


        setHasOptionsMenu(true);
        String userID;
        Bundle bundle = getArguments();
        if(bundle != null){
            bundle = getArguments();
            userID = bundle.getString("userID");
        }




        enrollButton_write_postscript = view.findViewById(R.id.enrollButton_write_postscript);
        write_postscript_layout = view.findViewById(R.id.write_postscript_layout);
        search_write_postscript = view.findViewById(R.id.search_write_postscript); //비교과명
        psText_write_postscript = view.findViewById(R.id.psText_write_postscript); //비교과후기
        ratingBar_write_postscript = view.findViewById(R.id.ratingBar_write_postscript); //비교과평점


        enrollButton_write_postscript.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {



                String program = search_write_postscript.toString();
                String postscript = psText_write_postscript.getText().toString();
                float ratingbar = ratingBar_write_postscript.getRating();

                //String userID = null;

                Bundle extra = getArguments();
                String userID = null;
                if(extra != null){
                    userID = extra.getString("userID");
                } else userID="1871002";


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 후기등록에 성공한 경우
                                Toast.makeText(getActivity(), "후기 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getTargetFragment().getContext(), WritePostscriptFragment.class);
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); //프래그먼트 전환
                                PostscriptFragment postscriptFragment = new PostscriptFragment();
                                transaction.replace(R.id.main_frame, postscriptFragment);//후기 등록에 성공하면 후기 게시판으로 이동해 작성한 후기 확인
                                transaction.commit();
                                //startActivity(intent);
                            } else { // 후기등록에 실패한 경우
                                Toast.makeText(getActivity(), "후기 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ReviewRequest reviewRequest = new ReviewRequest(program, postscript, ratingbar, userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(reviewRequest);
            }
        });


        write_postscript_layout.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(psText_write_postscript.getWindowToken(), 0);
        });

        return view;
    }
}
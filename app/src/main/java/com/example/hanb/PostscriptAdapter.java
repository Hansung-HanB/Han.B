package com.example.hanb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostscriptAdapter extends RecyclerView.Adapter<PostscriptAdapter.Holder> {
    private Context context;
    private List<PostscriptData> list = new ArrayList<>();

    public PostscriptAdapter(Context context, List<PostscriptData> list) {
        this.context = context;
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_normal, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


     // Todo 만들어진 ViewHolder에 data 삽입 ListView의 getView와 동일
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 각 위치에 문자열 세팅
        int itemposition = position;
        holder.program.setText(list.get(itemposition).program);
        holder.postscript.setText(list.get(itemposition).postscript);
        holder.ratingBar.setRating(list.get(itemposition).ratingbar);
        Log.e("StudyApp", "onBindViewHolder" + itemposition);
    }

    // 몇개의 데이터를 리스트로 뿌려줘야하는지 반드시 정의해줘야한다
    @Override
    public int getItemCount() {
        return list.size(); // RecyclerView의 size return
    }

    // ViewHolder는 하나의 View를 보존하는 역할을 한다
    public class Holder extends RecyclerView.ViewHolder{
        public TextView program;
        public TextView postscript;
        public RatingBar ratingBar;


        public Holder(View view){
            super(view);
            program = (TextView) view.findViewById(R.id.program_postscipt_item);
            postscript = (TextView) view.findViewById(R.id.postscript_postscipt_item);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_postscript_item);
        }
    }
}
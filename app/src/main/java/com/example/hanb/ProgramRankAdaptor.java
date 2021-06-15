package com.example.hanb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class ProgramRankAdaptor extends RecyclerView.Adapter<ProgramRankAdaptor.ViewHolder> {
   private ArrayList<ProgramRankItem> rankProgramData = null;

   ProgramRankAdaptor(ArrayList<ProgramRankItem> data) { rankProgramData = data; }

    @Override
    public ProgramRankAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_rank_program, parent, false);
        ProgramRankAdaptor.ViewHolder holder = new ProgramRankAdaptor.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ProgramRankAdaptor.ViewHolder holder, int position) {
        ProgramRankItem rankProgram = rankProgramData.get(position);
        holder.rankProgram.setText(rankProgram.getRankProgram());
        holder.rankGrade.setText(rankProgram.getRankProgramGrade());
    }

    @Override
    public int getItemCount() {
        return rankProgramData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView rankProgram;
       TextView rankGrade;

       //String program;
       //Float ratingbar;

       ViewHolder(View item) {
           super(item);

           rankProgram = itemView.findViewById(R.id.rank_program_list);
           rankGrade = itemView.findViewById(R.id.rank_program_grade_list);

           //rankProgram.setText(program);
           //rankGrade.setText(ratingbar.toString());

       }
    }
}

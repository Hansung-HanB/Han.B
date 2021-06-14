package com.example.hanb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProgramRecommendAdaptor extends RecyclerView.Adapter<ProgramRecommendAdaptor.ViewHolder> {
    private ArrayList<ProgramRecommendItem> programData = null;

   ProgramRecommendAdaptor(ArrayList<ProgramRecommendItem> list) {
       programData = list;
   }

    @Override
    public ProgramRecommendAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_recommend_program, parent, false);
        ProgramRecommendAdaptor.ViewHolder holder = new ProgramRecommendAdaptor.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ProgramRecommendAdaptor.ViewHolder holder, int position) {
       ProgramRecommendItem program = programData.get(position);
       holder.program.setText(program.getRecommendProgram());
       holder.grade.setText(program.getProgramGrade());
    }

    @Override
    public int getItemCount() {
        return programData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView program;
       TextView grade;

       ViewHolder(View itemView) {
           super(itemView);

           program = itemView.findViewById(R.id.recommend_program_list);
           grade = itemView.findViewById(R.id.program_grade_list);
       }
    }
}

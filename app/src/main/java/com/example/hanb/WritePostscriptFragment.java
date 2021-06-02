package com.example.hanb;

import android.content.Context;
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
import android.widget.Toast;

public class WritePostscriptFragment extends Fragment {
    private View view;
    private Button enrollButton_write_postscript;
    private ConstraintLayout write_postscript_layout;
    private EditText psText_write_postscript;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_write_postscript, container, false);

        enrollButton_write_postscript = view.findViewById(R.id.enrollButton_write_postscript);
        write_postscript_layout = view.findViewById(R.id.write_postscript_layout);
        psText_write_postscript = view.findViewById(R.id.psText_write_postscript);


        enrollButton_write_postscript.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "후기 등록", Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            PostscriptFragment postscriptFragment = new PostscriptFragment();
            transaction.replace(R.id.main_frame, postscriptFragment);
            transaction.commit();
        });

        write_postscript_layout.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(psText_write_postscript.getWindowToken(), 0);
        });

        return view;
    }
}
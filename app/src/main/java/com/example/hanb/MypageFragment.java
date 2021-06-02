package com.example.hanb;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MypageFragment extends Fragment {
    private View view;
    private Button changeNickButton_mypage;
    private Button changePWButton_mypage;
    private Button signOutButton_mypage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, container, false);
        changeNickButton_mypage = view.findViewById(R.id.changeNickButton_mypage);
        changePWButton_mypage = view.findViewById(R.id.changePWButton_mypage);
        signOutButton_mypage = view.findViewById(R.id.signOutButton_mypage);


        changeNickButton_mypage.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            ChangeNicknameFragment changeNicknameFragment = new ChangeNicknameFragment();
            transaction.replace(R.id.main_frame, changeNicknameFragment);
            transaction.commit();
        });

        changePWButton_mypage.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
            transaction.replace(R.id.main_frame, changePasswordFragment);
            transaction.commit();
        });

        signOutButton_mypage.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
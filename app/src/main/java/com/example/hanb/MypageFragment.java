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

        // 닉네임변경 버튼 클릭시 닉네임변경페이지로 이동
        changeNickButton_mypage.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            ChangeNicknameFragment changeNicknameFragment = new ChangeNicknameFragment();
            transaction.replace(R.id.main_frame, changeNicknameFragment);
            transaction.commit();
        });

        // 비밀번호변경 버튼 클릭시 비밀번호변경페이지로 이동
        changePWButton_mypage.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
            transaction.replace(R.id.main_frame, changePasswordFragment);
            transaction.commit();
        });

        // 마이페이지에서 로그아웃 클릭시 로그인 화면으로 이동
        signOutButton_mypage.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
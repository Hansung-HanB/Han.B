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

public class ChangePasswordFragment extends Fragment {
    private View view;
    private Button changePWButton_changePW;
    private ConstraintLayout change_password_layout;
    private EditText prePWText_changePW;
    private EditText newPWText_changePW;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_password, container, false);

        changePWButton_changePW = view.findViewById(R.id.changePWButton_changePW);
        change_password_layout = view.findViewById(R.id.change_password_layout);
        prePWText_changePW = view.findViewById(R.id.prePWText_changePW);
        newPWText_changePW = view.findViewById(R.id.newPWText_changePW);

        // 비밀번호 변경 후 버튼 클릭시 완료되었다는 토스트메시지 띄우고 비밀번호 변경 페이지에서 마이페이지로 이동
        changePWButton_changePW.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "비밀번호 변경 완료", Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            MypageFragment mypageFragment = new MypageFragment();
            transaction.replace(R.id.main_frame, mypageFragment);
            transaction.commit();
        });

        // 키보드 내리기
        change_password_layout.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(prePWText_changePW.getWindowToken(), 0);
        });

        // 키보드 내리기
        change_password_layout.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(newPWText_changePW.getWindowToken(), 0);
        });

        return view;
    }
}
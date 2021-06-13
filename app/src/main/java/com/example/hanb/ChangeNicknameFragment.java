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

public class ChangeNicknameFragment extends Fragment {
    private View view;
    private Button changeNickButton_changeNick;
    private ConstraintLayout change_nickname_layout;
    private EditText changeNickText_changeNick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_change_nickname, container, false);

        changeNickButton_changeNick = view.findViewById(R.id.changeNickButton_changeNick);
        change_nickname_layout = view.findViewById(R.id.change_nickname_layout);
        changeNickText_changeNick = view.findViewById(R.id.changeNickText_changeNick);

        // 닉네임 변경 후 버튼 클릭시 완료되었다는 토스트메시지 띄우고 닉네임 변경 페이지에서 마이페이지로 이동
        changeNickButton_changeNick.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "닉네임 변경 완료", Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            MypageFragment mypageFragment = new MypageFragment();
            transaction.replace(R.id.main_frame, mypageFragment);
            transaction.commit();
        });

        // 키보드 내리기
        change_nickname_layout.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(changeNickText_changeNick.getWindowToken(), 0);
        });

        return view;
    }
}
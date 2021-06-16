package com.example.hanb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);

        //처음화면
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new HomeFragment()).commit();

        //바텀 네비게이션뷰 안의 아이템 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home_menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();
                    break;

                case R.id.program_menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new ProgramFragment()).commit();
                    break;

                case R.id.postscript_menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new PostscriptFragment()).commit();
                    break;

                case R.id.mypage_menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MypageFragment()).commit();
                    break;
            }
            return true;
        });
    }

    // back버튼 클릭시 무조건 home화면으로 이동
    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.home_menu) {
            super.onBackPressed();
            finish();
        } else {
            bottomNavigationView.setSelectedItemId(R.id.home_menu);
        }
    }
}


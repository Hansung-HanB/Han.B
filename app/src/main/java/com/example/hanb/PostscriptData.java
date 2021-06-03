package com.example.hanb;

import java.util.ArrayList;

public class PostscriptData {
    public String program;
    public String postscript;
    public int ratingBar;

    // 화면에 표시될 문자열 초기화
    public PostscriptData(String program, String postscript,int ratingBar) {
        this.program = program;
        this.postscript = postscript;
        this.ratingBar=ratingBar;
    }

    // 입력받은 숫자의 리스트생성
    public static ArrayList<PostscriptData> createContactsList(int numContacts) {
        ArrayList<PostscriptData> contacts = new ArrayList<PostscriptData>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new PostscriptData("Program 0603_4"+i, "후기"+i, i%5));
        }

        return contacts;
    }
}

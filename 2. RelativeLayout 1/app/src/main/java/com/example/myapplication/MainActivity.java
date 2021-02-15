package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// 총 2개의 쓰레드 (UI쓰레드, 이벤트감지쓰레드)
// Activity 화면
// 메인 쓰레드 => onCreate() => UI 쓰레드(다 그린 후 관찰)
//                        => 이벤트 스레드
//                        => 이벤트 리스너(os)
// 클릭이 되면 이벤트 스레드에 등록이 됨 클릭될때마다 순차적으로 등록하고 순차적으로 실행하고 삭제한다.
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";

    private TextView tvTitle;

    // 매니페스트에서 설정된 자바 파일이
    // 실행될 때 가장 먼저 실행되는 함수 onCreate() (main method)
    // 초기화 함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 그림 그리는 함수 (activity_main.xml) -> 자바
        // ContentView는 도화지이다. 밑그림만 그리고 onCreate가 종료되면 화면을 보이게 한다.

        
        Log.d(TAG, "onCreate: ");
//        tvTitle = findViewById(R.id.tv_title);
//        tvTitle.setText("Hi");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    // 화면 그려지기 직전 => 데이터 다운로드  // onCreate 종료시에 그림 그려짐.
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("movie", MODE_PRIVATE);
        String title = sp.getString("title","none");
        Log.d(TAG, "onResume: " + title);

    } // onCreate 종료시에 그림 그려짐.함

    // 앱 멈출때 ex 다른 앱으로 갈때 등등.
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    // 앱 종료시
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        // 1. 파일 (그림, 사진( = 카메라로 찍은 사진)을 저장)
        // 2. 내부 DB = (SQL Lite) (주소록, 메모장 등과 같은 것을 저장)
        // 3. 외부서버 = DB (예를 들어 인스타그램 업로드할 사진)
        // 4. 하드 엑세스 저장소 (제일 많이 씀) = 앱마다 달려있음.(ex. 10개의 앱이 있으면 10개가 생김) 하지만 실제로는 한개이다.(키로 구분 = Shared Preference)

        SharedPreferences sp = getSharedPreferences("movie", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("title","바람과 함께 사라지다");
        editor.commit(); // 영구히 저장
    }
}
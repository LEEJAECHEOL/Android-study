package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.myapplication2.R;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private static final String TAG = "ItemAdapter";

    //싱글톤
    private final List<Movie> movies;

    public ItemAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // 전체크기를 확인하기위해서 필요 (나도, 어댑터도)
    @Override
    public int getCount() {
        return movies.size();
    }

    // 클릭하거나 어떤 이벤트 발생시에 컬렉션 정보를 확인하기 위해 필요
    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 객체를 생성해서 그림을 그리는 함수 (6번 화면크기만큼)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: " + position);
        if (convertView == null) {
            Log.d(TAG, "getView: conveterView is null");
        } else {
            Log.d(TAG, "getView: conveterView is not null");
        }
        // 1. 부모 컨텍스트 가져오기 (Context <- Activity(생명주기))
//        Context context = parent.getContext(); // mainActivityContext

        MainActivity mainActivity = (MainActivity)parent.getContext(); // Activity는 Context의 자식이다.

        // 인플레이터 객체 생성 완료 => list_item을 띄울떄 필요한 객체 생성
        // 2. 인플레이터 객체 생성 완료 (xml을 자바 객체로 만들게 해주는 도구)
        LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater inflater2 = LayoutInflater.from(context);

        // 재활용
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.list_item, parent);
//        }
        // 강제성이 없어서!! 새로 생성함
        View view = inflater.inflate(R.layout.list_item, parent, false);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvSubTitle = view.findViewById(R.id.tv_subtitle);
        tvTitle.setText(movies.get(position).getTitle());
        tvSubTitle.setText(movies.get(position).getSubTitle());

        // 클릭 이벤트
        view.setOnClickListener(v -> {
//            Toast.makeText(context, "클릭됨"+position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mainActivity, DetailActivity.class);
            intent.putExtra("title", movies.get(position).getTitle());

            mainActivity.startActivity(intent);
        });

        // 누르고 있을 때 삭제함
        view.setOnLongClickListener(v -> {
            Toast.makeText(mainActivity, "클릭됨"+position, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "movies Size" + movies.size());
            movies.remove(position);
            Log.d(TAG, "movies Change Size" + movies.size());
            this.notifyDataSetChanged(); // 데이터 변경 후 UI동기화 시 호출해야 함.
            return true;
        });

        return view;
    }
}
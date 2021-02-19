package com.example.myapplication9;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";

    // 무조건 만들어 주어야 함.onCreateView()
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = container.getContext();
        MainActivity at = (MainActivity)context;
        at.num = 2;
        Log.d(TAG, "onCreateView: " + at.num);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        return view;
    }
}

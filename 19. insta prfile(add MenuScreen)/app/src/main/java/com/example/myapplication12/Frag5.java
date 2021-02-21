package com.example.myapplication12;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Frag5 extends Fragment {

    private TabLayout tabs;
    private ViewPager vpContainer;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = container.getContext();
        MainActivity at = (MainActivity)context;

        View view = inflater.inflate(R.layout.frag_5, container, false);

        vpContainer = view.findViewById(R.id.vp_container);
        tabs = view.findViewById(R.id.tab);

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), 1);

        myFragmentPagerAdapter.addFragment(new ChildFrag1());
        myFragmentPagerAdapter.addFragment(new ChildFrag2());

        vpContainer.setAdapter(myFragmentPagerAdapter);

        tabs.setupWithViewPager(vpContainer);
        tabs.getTabAt(0).setIcon(R.drawable.ic_app);
        tabs.getTabAt(1).setIcon(R.drawable.ic_assignment);

        return view;
    }
}

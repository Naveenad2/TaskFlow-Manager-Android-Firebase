package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TechnicianPagerAdapter extends FragmentStateAdapter {



    public TechnicianPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new TechnicianComplaintFragment();
            case 0:
                return new TechnicianConnectionFragment();
            case 2:
                return new TechnicianMyTaskFragment();
            case 3:
                return new TechnicianMyTaskComplaintFragment();


        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AdminPremissionsFragment();
            case 2:
                return new AdminChatsFragment();
            case 1:
                return new AdminActivityFragment();


        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

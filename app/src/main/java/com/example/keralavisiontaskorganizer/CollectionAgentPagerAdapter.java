package com.example.keralavisiontaskorganizer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CollectionAgentPagerAdapter extends FragmentStateAdapter {

    public CollectionAgentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CollectionAgentCollectionFragment();
            case 1:
                return new CollectionAgentMyActivityFragment();



        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

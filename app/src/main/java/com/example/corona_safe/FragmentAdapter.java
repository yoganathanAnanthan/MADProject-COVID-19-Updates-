package com.example.corona_safe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 1 :
                return new com.example.corona_safe.SecondFragment();
            case 2 :
                return new com.example.corona_safe.ThirdFragment();
        }

        return new com.example.corona_safe.FirstFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

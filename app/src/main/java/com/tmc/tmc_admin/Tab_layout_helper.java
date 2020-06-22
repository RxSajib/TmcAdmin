package com.tmc.tmc_admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class Tab_layout_helper extends FragmentPagerAdapter {

    public Tab_layout_helper(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                WinnerFragement winnerFragement = new WinnerFragement();
                return winnerFragement;

            case 1:
                Referral_Fragement referral_fragement = new Referral_Fragement();
                return referral_fragement;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Winner";

            case 1:
                return "Referral";
        }

        return super.getPageTitle(position);
    }
}

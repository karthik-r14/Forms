package com.userdetails.forms.view.more;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CustomPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_FRAGMENTS = 3;
    private String item[]= {"ONE", "TWO", "THREE"};

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MoreFragment.newInstance(item[position]);
    }

    @Override
    public int getCount() {
        return NUM_FRAGMENTS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return item[position];
    }
}

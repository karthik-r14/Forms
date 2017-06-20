package com.userdetails.forms.view.more;

import android.app.ProgressDialog;
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
        Fragment fragment = new Fragment();
        if(position == 0 || position == 1) {
            fragment = MoreFragment.newInstance(item[position]);
        } else {
            fragment = MyFragment.newInstance();
        }
        return fragment;

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

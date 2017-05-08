package com.userdetails.forms.view.more;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.userdetails.forms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends AppCompatActivity {
    @BindView(R.id.more_pager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
    }
}

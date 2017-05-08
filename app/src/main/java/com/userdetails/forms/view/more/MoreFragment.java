package com.userdetails.forms.view.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.userdetails.forms.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoreFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.type)
    TextView type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);
        String text = (String) getArguments().get("type");
        type.setText(text);
        return view;
    }

    public static android.support.v4.app.Fragment newInstance(String type) {
        MoreFragment moreFragment = new MoreFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        moreFragment.setArguments(bundle);
        return moreFragment;
    }
}

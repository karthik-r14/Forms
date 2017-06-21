package com.userdetails.forms.view.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.userdetails.forms.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFragment extends Fragment {
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> myList = new ArrayList<>();
        myList.add("data ");
        myList.add("Hi");
        myList.add("Hello");
        myList.add("What's up");
        myList.add("Qwerty");
        myList.add("wiper");
        
        recyclerView.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter(myList, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public static android.support.v4.app.Fragment newInstance() {
        MyFragment myFragment= new MyFragment();
        return myFragment;
    }
}

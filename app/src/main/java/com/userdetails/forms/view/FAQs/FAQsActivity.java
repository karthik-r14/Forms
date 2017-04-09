package com.userdetails.forms.view.FAQs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.userdetails.forms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQsActivity extends AppCompatActivity {
    @BindView(R.id.faqs)
    ListView faqs;

    String[] questions = {"What is the use of this app?",
            "On which version of Android will this app work?",
            "What is the intended audience for this app?",
            "Who created this app?"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        ButterKnife.bind(this);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, questions);
        faqs.setAdapter(adapter);
        faqs.setBackgroundColor(Color.CYAN);
    }
}

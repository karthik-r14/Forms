package com.userdetails.forms.view.FAQs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.userdetails.forms.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQsActivity extends AppCompatActivity {
    @BindView(R.id.faqs)
    ListView faqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        ButterKnife.bind(this);
        CustomAdapter adapter = new CustomAdapter(this, getResources().getStringArray(R.array.questionNo),
                getResources().getStringArray(R.array.questions), getResources().getStringArray(R.array.answers));
        faqs.setAdapter(adapter);
        faqs.setBackgroundColor(Color.YELLOW);
    }
}

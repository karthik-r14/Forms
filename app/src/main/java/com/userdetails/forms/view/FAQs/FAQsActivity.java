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

    String[] questions = {"What is the use of this app?",
            "On which version of Android will this app work?",
            "What is the intended audience for this app?",
            "Who created this app?"
    };

    String[] answers = {"Its for filling up details of a user.",
            "This app will run on devices after android Lollipop.",
            "Its for everyone.",
            "This app is made by Karthik R."
    };

    String[] questionNo = {"1.", "2.", "3.", "4."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        ButterKnife.bind(this);
        MyAdapter adapter  = new MyAdapter(this, questionNo, questions, answers);
        faqs.setAdapter(adapter);
        faqs.setBackgroundColor(Color.YELLOW);
    }
}

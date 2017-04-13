package com.userdetails.forms.view.FAQs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.userdetails.forms.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQsActivity extends AppCompatActivity {
    public static final String QUESTIONS = "questions";
    public static final String ANSWERS = "answers";
    public static final String QUESTION_NUMBER = "question_number";
    private static final String TAG = FAQsActivity.class.getSimpleName();
    @BindView(R.id.faqs)
    ListView faqs;
    private DatabaseReference mDatabase;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener FaqListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> questions = (ArrayList<String>) dataSnapshot.child(QUESTIONS).getValue();
                ArrayList<String> answers = (ArrayList<String>) dataSnapshot.child(ANSWERS).getValue();
                ArrayList<String> questionNo = (ArrayList<String>) dataSnapshot.child(QUESTION_NUMBER).getValue();

                adapter = new CustomAdapter(FAQsActivity.this, questionNo.toArray(new String[questionNo.size()]),
                        questions.toArray(new String[questions.size()]), answers.toArray(new String[answers.size()]));

                faqs.setAdapter(adapter);
                faqs.setBackgroundColor(Color.YELLOW);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled", databaseError.toException());
            }
        };

        mDatabase.addValueEventListener(FaqListener);
    }
}

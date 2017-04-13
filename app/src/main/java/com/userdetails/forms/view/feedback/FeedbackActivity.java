package com.userdetails.forms.view.feedback;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.userdetails.forms.R;
import com.userdetails.forms.presenter.FeedbackPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static android.widget.Toast.LENGTH_SHORT;

public class FeedbackActivity extends AppCompatActivity implements FeedbackView {
    public static final String FEEDBACK = "feedback";
    @BindView(R.id.feedback_text)
    EditText feedbackText;
    @BindView(R.id.feedback_text_length)
    TextView feedbackTextLength;

    private FeedbackPresenter presenter;
    private DatabaseReference mDatabase;
    private String TAG = FeedbackActivity.class.getSimpleName();
    private String feedbackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        presenter = new FeedbackPresenter(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener feedbackListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> feedback = (ArrayList<String>) dataSnapshot.child(FEEDBACK).getValue();
                feedbackId = Integer.toString(feedback.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadFeedback:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addListenerForSingleValueEvent(feedbackListener);
    }

    @OnClick(R.id.submit_button)
    public void onSubmitButtonClick() {
        if (!isConnectivityAvailable()) {
            Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        presenter.validate(feedbackText.getText().toString());
    }

    @OnTextChanged(R.id.feedback_text)
    void onFeedbackTextChanged() {
        Integer textLength = feedbackText.getText().toString().length();
        feedbackTextLength.setText(Integer.toString(textLength));
    }

    @Override
    public void showToastMessage(@StringRes int message) {
        Toast.makeText(getApplicationContext(), message, LENGTH_SHORT).show();
    }

    @Override
    public void clearFeedbackText() {
        feedbackText.setText("");
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void storeFeedback(String feedbackText) {
        mDatabase.child(FEEDBACK).child(feedbackId).setValue(feedbackText);
    }


    private boolean isConnectivityAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

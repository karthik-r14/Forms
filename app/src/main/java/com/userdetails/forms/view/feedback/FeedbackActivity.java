package com.userdetails.forms.view.feedback;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_SHORT;

public class FeedbackActivity extends AppCompatActivity implements FeedbackView {
    public static final String FEEDBACK = "feedback";
    @BindView(R.id.feedback_text)
    EditText feedbackText;
    @BindView(R.id.feedback_text_length)
    TextView feedbackTextLength;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.feedback_layout)
    LinearLayout feedbackLayout;
    @BindView(R.id.imageView)
    ImageView image;

    private FeedbackPresenter presenter;
    private DatabaseReference mDatabase;
    private String TAG = FeedbackActivity.class.getSimpleName();
    private String feedbackId;
    private ArrayList<String> feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        presenter = new FeedbackPresenter(this);

        progressBar.setVisibility(VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        feedback = new ArrayList<>();

        ValueEventListener feedbackListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedback = (ArrayList<String>) dataSnapshot.child(FEEDBACK).getValue();
                feedbackId = Integer.toString(feedback.size());
                progressBar.setVisibility(GONE);
                feedbackLayout.setVisibility(VISIBLE);
                image.setVisibility(INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadFeedback:onCancelled", databaseError.toException());
            }
        };
       mDatabase.addValueEventListener(feedbackListener);
       //mDatabase.addListenerForSingleValueEvent(feedbackListener);
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

    @OnFocusChange(R.id.feedback_text)
    void onFocusChange() {
        image.setVisibility(VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        image.startAnimation(animation);
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

    @OnClick(R.id.delete_button)
    public void onDeleteButtonClick() {
        for (int i = 0; i < feedback.size(); ++i) {
            if(feedback.get(i) != null && feedback.get(i).length() < 10 ) {
                mDatabase.child(FEEDBACK).child(Integer.toString(i)).removeValue();
            }
        }
        finish();
    }

    private boolean isConnectivityAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

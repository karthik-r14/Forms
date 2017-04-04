package com.userdetails.forms.view.feedback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.userdetails.forms.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_SHORT;

public class FeedbackActivity extends AppCompatActivity {
    @BindView(R.id.feedback_text)
    EditText feedbackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit_button)
    public void onSubmitButtonClick() {
        Toast.makeText(getApplicationContext(), R.string.feedback_submit_text, LENGTH_SHORT).show();
        feedbackText.setText("");
    }
}

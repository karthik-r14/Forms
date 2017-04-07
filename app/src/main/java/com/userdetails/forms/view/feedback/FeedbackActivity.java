package com.userdetails.forms.view.feedback;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.R;
import com.userdetails.forms.presenter.FeedbackPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static android.widget.Toast.LENGTH_SHORT;

public class FeedbackActivity extends AppCompatActivity implements FeedbackView {
    @BindView(R.id.feedback_text)
    EditText feedbackText;
    @BindView(R.id.feedback_text_length)
    TextView feedbackTextLength;

    private FeedbackPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        presenter = new FeedbackPresenter(this);
    }

    @OnClick(R.id.submit_button)
    public void onSubmitButtonClick() {
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
}

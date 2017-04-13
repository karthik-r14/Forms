package com.userdetails.forms.presenter;

import com.userdetails.forms.R;
import com.userdetails.forms.view.feedback.FeedbackView;

public class FeedbackPresenter {
    private FeedbackView view;

    public FeedbackPresenter(FeedbackView view) {
        this.view = view;
    }

    public void validate(String feedbackText) {
        if(feedbackText.trim().isEmpty()) {
            view.showToastMessage(R.string.empty_feedback);
        }
        else {
            view.clearFeedbackText();
            view.showToastMessage(R.string.feedback_submit_text);
            view.storeFeedback(feedbackText);
            view.finishActivity();
        }
    }
}

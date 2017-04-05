package com.userdetails.forms.presenter;

import com.userdetails.forms.R;
import com.userdetails.forms.view.feedback.FeedbackView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class FeedbackPresenterTest {
    @Mock
    private FeedbackView view;

    private FeedbackPresenter presenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        presenter = new FeedbackPresenter(view);
    }

    @Test
    public void shouldShowToastMessageIfFeedbackIsEmpty() throws Exception {
        String feedbackText =" ";

        presenter.validate(feedbackText);

        verify(view).showToastMessage(R.string.empty_feedback);
    }

    @Test
    public void shouldClearFeedbackTextAndShowSavedToastMessageIfFeedbackIsNotEmpty() throws Exception {
        String feedbackText = "Nice App!";

        presenter.validate(feedbackText);

        verify(view).clearFeedbackText();
        verify(view).showToastMessage(R.string.feedback_submit_text);
        verify(view).finishActivity();
    }
}
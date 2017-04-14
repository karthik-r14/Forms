package com.userdetails.forms.view.rate_us;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.userdetails.forms.R;
import com.userdetails.forms.presenter.RateUsDialogPresenter;
import com.userdetails.forms.view.feedback.FeedbackActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_SHORT;

public class RateUsDialogFragment extends DialogFragment implements RateUsView {
    public static final String RATING = "rating";
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.button_layout)
    LinearLayout buttonLayout;
    @BindView(R.id.submit_and_feedback_button)
    Button submitAndFeedbackButton;
    @BindView(R.id.not_now)
    Button notNowButton;
    @BindView(R.id.tap_stars_text)
    TextView starsText;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rating_layout)
    LinearLayout ratingLayout;

    private RateUsDialogPresenter presenter;
    private DatabaseReference mDatabase;
    public static final String TAG = RateUsDialogFragment.class.getSimpleName();
    private String ratingId;

    public static RateUsDialogFragment newInstance() {
        return new RateUsDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_rate_us_dialog, (ViewGroup) getActivity().findViewById(R.id.rate_us_dialog));
        ButterKnife.bind(this, view);
        dialog.setTitle(R.string.rate_us);
        dialog.setContentView(view);
        dialog.setCancelable(false);

        progressBar.setVisibility(VISIBLE);

        presenter = new RateUsDialogPresenter(this);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                presenter.onRatingBarTouch(rating);
            }
        });

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);//color of empty stars

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ValueEventListener ratingListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> ratings = (ArrayList<String>) dataSnapshot.child(RATING).getValue();
                ratingId = Integer.toString(ratings.size());
                progressBar.setVisibility(GONE);
                ratingLayout.setVisibility(VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadRating:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addListenerForSingleValueEvent(ratingListener);

        return dialog;
    }

    @Override
    public void setButtonLayoutVisibility(int visibility) {
        buttonLayout.setVisibility(visibility);
    }

    @Override
    public void setColorOfRatingBar(int color) {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);//color of filled stars
    }

    @Override
    public void setTapStarsVisibility(int visibility) {
        starsText.setVisibility(visibility);
    }

    @Override
    public void setButtonText(@StringRes int buttonText) {
        submitAndFeedbackButton.setText(buttonText);
    }

    @OnClick(R.id.submit_and_feedback_button)
    public void onSubmitButtonClick() {
        if(!isConnectivityAvailable()) {
            Toast.makeText(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        if(submitAndFeedbackButton.getText().equals(getString(R.string.feedback))) {
            Intent intent = new Intent(getActivity(), FeedbackActivity.class);
            startActivity(intent);
        }
        mDatabase.child(RATING).child(ratingId).setValue(ratingBar.getRating());
        Toast.makeText(getActivity(), R.string.thank_you_submit, LENGTH_SHORT).show();
        getDialog().dismiss();
    }

    @OnClick(R.id.not_now)
    public void onNotNowClick() {
        getDialog().dismiss();
    }

    private boolean isConnectivityAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

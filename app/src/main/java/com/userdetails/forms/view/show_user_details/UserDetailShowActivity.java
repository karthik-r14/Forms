package com.userdetails.forms.view.show_user_details;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.R;
import com.userdetails.forms.model.Person;
import com.userdetails.forms.presenter.UserDetailPresenter;
import com.userdetails.forms.view.FAQs.FAQsActivity;
import com.userdetails.forms.view.about_us.AboutUsActivity;
import com.userdetails.forms.view.feedback.FeedbackActivity;
import com.userdetails.forms.view.rate_us.RateUsDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;


public class UserDetailShowActivity extends AppCompatActivity implements UserDetailView {
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final String ABOUT_US = "AboutUs";
    public static final String RATE_US = "RateUs";
    public static final String FEEDBACK = "Feedback";
    public static final String FAQS = "FAQs";
    public static final String PERSON = "Person";
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.image_text)
    TextView imageText;

    private boolean hadTakenPhoto;
    private UserDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_detail);
        ButterKnife.bind(this);
        presenter = new UserDetailPresenter(this);

        Intent intent = getIntent();
        Person person = intent.getParcelableExtra(PERSON);

        String name = person.getName();
        String age = person.getAge();
        String phoneNumber = person.getPhoneNumber();
        String email = person.getEmail();

        setAllViews(name, age, phoneNumber, email);
    }

    @OnClick(R.id.image)
    public void onImageClick() {
        presenter.onImageClick(hadTakenPhoto);
    }

    @Override
    public void showCustomToast() {
        Toast toast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
        toast.setView(getLayoutInflater().inflate(R.layout.layout_custom_toast, (ViewGroup) findViewById(R.id.custom_layout)));
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    @Override
    public void imageTextVisibility(int visibility) {
        imageText.setVisibility(GONE);
    }

    @Override
    public void setImageView(Bitmap photo) {
        image.setImageBitmap(photo);
    }

    @Override
    public void startAboutUsActivity() {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRateUsDialog() {
        RateUsDialogFragment rateUsDialogFragment = RateUsDialogFragment.newInstance();
        rateUsDialogFragment.show(getFragmentManager(), RateUsDialogFragment.TAG);
    }

    @Override
    public void startFeedbackActivity() {
        Intent intent = new Intent(UserDetailShowActivity.this, FeedbackActivity.class);
        startActivity(intent);
    }

    @Override
    public void startFAQSActivity() {
        Intent intent = new Intent(UserDetailShowActivity.this, FAQsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAlertDialogAndLaunchCamera(int title, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_REQUEST_CODE);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            hadTakenPhoto = true;
            presenter.setImageLayoutBasedOnPhoto(photo);
        }
    }

    private void setAllViews(String name, String age, String phoneNumber, String email) {
        this.name.setText(name);
        this.age.setText(age);
        this.phoneNumber.setText(phoneNumber);
        this.email.setText(email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_us:
                presenter.onMenuItemClick(ABOUT_US);
                return true;
            case R.id.rate_us:
                presenter.onMenuItemClick(RATE_US);
                return true;
            case R.id.settings:
                presenter.onMenuItemClick(FEEDBACK);
                return true;
            case R.id.faqs:
                presenter.onMenuItemClick(FAQS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

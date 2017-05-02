package com.userdetails.forms.view.form;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.R;
import com.userdetails.forms.model.Person;
import com.userdetails.forms.presenter.FormPresenter;
import com.userdetails.forms.view.show_user_details.UserDetailShowActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormActivity extends AppCompatActivity implements FormView {
    public static final String USER_DETAILS = "UserDetails";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.name_error)
    TextView nameError;
    @BindView(R.id.age_error)
    TextView ageError;
    @BindView(R.id.phone_error)
    TextView phoneError;
    @BindView(R.id.email_error)
    TextView emailError;

    private FormPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        presenter = new FormPresenter(this);
    }

    @OnClick(R.id.submit_button)
    public void submitButtonClick() {
        String name = this.name.getText().toString();
        String age = this.age.getText().toString();
        String phoneNumber = this.phoneNumber.getText().toString();
        String email = this.email.getText().toString();

        presenter.validate(name, age, phoneNumber, email);

       if (!presenter.hasErrors()) {
            storeDetailsInSharedPreferences(name, age, phoneNumber, email);
            showAlertDialog(name, age, phoneNumber, email);
        }
    }

    private void storeDetailsInSharedPreferences(String name, String age, String phoneNumber, String email) {
        SharedPreferences sharedPreferences = getSharedPreferences(USER_DETAILS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, name);
        editor.putString(AGE, age);
        editor.putString(PHONE, phoneNumber);
        editor.putString(EMAIL, email);
        editor.commit();
    }

    private void showAlertDialog(final String name, final String age, final String phoneNumber, final String address) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.form_icon);
        builder.setTitle(R.string.form_submission)
                .setMessage(R.string.message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), R.string.submit_message,
                                Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), UserDetailShowActivity.class);
                        Person person = new Person(name, age, phoneNumber, address);
                        intent.putExtra(UserDetailShowActivity.PERSON, person);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void nameErrorVisibility(int visibility) {
        nameError.setVisibility(visibility);
        nameError.requestFocus();
    }

    @Override
    public void ageErrorVisibility(int visibility) {
        ageError.setVisibility(visibility);
        ageError.requestFocus();
    }

    @Override
    public void phoneErrorVisibility(int visibility) {
        phoneError.setVisibility(visibility);
        phoneError.requestFocus();
    }

    @Override
    public void emailErrorVisibility(int visibility) {
        emailError.setVisibility(visibility);
        emailError.requestFocus();
    }
}
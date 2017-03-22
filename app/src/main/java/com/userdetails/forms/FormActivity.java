package com.userdetails.forms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.model.Person;
import com.userdetails.forms.presenter.FormPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormActivity extends AppCompatActivity implements FormView {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.name_error)
    TextView nameError;
    @BindView(R.id.age_error)
    TextView ageError;
    @BindView(R.id.phone_error)
    TextView phoneError;
    @BindView(R.id.address_error)
    TextView addressError;

    private FormPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        presenter = new FormPresenter(this);
    }

    @OnClick(R.id.submit_button)
    void submitButtonClick() {
        final String name = this.name.getText().toString();
        final String age = this.age.getText().toString();
        final String phoneNumber = this.phoneNumber.getText().toString();
        final String address = this.address.getText().toString();

        presenter.validate(name, age, phoneNumber, address);

        if (!presenter.hasErrors()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle(R.string.submission)
                    .setMessage(R.string.message)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getApplicationContext(), R.string.submit_message,
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), UserDetailShowActivity.class);
                            Person person = new Person(name, age, phoneNumber, address);
                            intent.putExtra("Person", person);
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
    public void addressErrorVisibility(int visibility) {
        addressError.setVisibility(visibility);
        addressError.requestFocus();
    }
}
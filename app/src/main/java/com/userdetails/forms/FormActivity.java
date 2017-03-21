package com.userdetails.forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    FormPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        presenter = new FormPresenter(this);
    }

    @OnClick(R.id.submit_button)
    void submitButtonClick() {
        presenter.validate(name.getText().toString(), age.getText().toString(), phoneNumber.getText().toString(), address.getText().toString());

        if (!presenter.hasErrors()) {
            Toast.makeText(getApplicationContext(), R.string.submit_message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void nameErrorVisibility(int visibility) {
        nameError.setVisibility(visibility);
    }

    @Override
    public void ageErrorVisibility(int visibility) {
        ageError.setVisibility(visibility);
    }

    @Override
    public void phoneErrorVisibility(int visibility) {
        phoneError.setVisibility(visibility);
    }

    @Override
    public void addressErrorVisibility(int visibility) {
        addressError.setVisibility(visibility);
    }
}
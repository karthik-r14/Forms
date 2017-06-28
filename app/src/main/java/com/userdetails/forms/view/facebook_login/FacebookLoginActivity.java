package com.userdetails.forms.view.facebook_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.userdetails.forms.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FacebookLoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginResult.getAccessToken();
                Toast.makeText(getApplicationContext(), "Login :"+loginResult.toString(),Toast.LENGTH_LONG).show();
                setFacebookData(loginResult);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error"+ error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setFacebookData(LoginResult loginResult) {

        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("response", response.toString());
                        try {
                            String firstName = response.getJSONObject().getString("first_name");
                            String gender = response.getJSONObject().getString("gender");
                            String birthday = object.getString("birthday");

                            Toast.makeText(getApplicationContext(), "Name :" + firstName, Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Gender :" + gender, Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "birthday :" + birthday, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(), data.getExtras().toString(), Toast.LENGTH_LONG).show();
    }
}

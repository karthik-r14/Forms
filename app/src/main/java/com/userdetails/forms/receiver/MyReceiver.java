package com.userdetails.forms.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.userdetails.forms.R;

public class MyReceiver extends BroadcastReceiver {
    public static final String STATE = "state";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            boolean isAirplaneModeOn = intent.getBooleanExtra(STATE, false);

            if (isAirplaneModeOn) {
                Toast.makeText(context, R.string.airplane_mode_message, Toast.LENGTH_LONG).show();
            }
        }
    }
}

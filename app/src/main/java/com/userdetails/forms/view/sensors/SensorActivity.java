package com.userdetails.forms.view.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.login.LoginResult;
import com.userdetails.forms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    @BindView(R.id.sensor_list)
    ListView sensorList;

    private SensorManager sensorManager;
    private List<Sensor> sensors;
    private Sensor proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ButterKnife.bind(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        ArrayAdapter<Sensor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sensors);
        sensorList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        Log.d("Sensor Name :", event.sensor.getName());
        Log.d("value", String.valueOf(value));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

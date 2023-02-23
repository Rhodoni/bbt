package com.ut3.bbt.game;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class CaptorActivity implements SensorEventListener {

    private SensorManager sm;

    public void setUpSensors(Context context){
        sm = (SensorManager) context.getSystemService(SENSOR_SERVICE);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

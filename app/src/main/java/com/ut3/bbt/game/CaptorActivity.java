package com.ut3.bbt.game;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


public class CaptorActivity implements SensorEventListener {

    private SensorManager sm;
    public boolean isJumping = false;
    private float old_x = 0, old_y = 0, old_z = 0 ;

    public void setUpSensors(Context context){
        sm = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME );
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR),SensorManager.SENSOR_DELAY_GAME );
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensor = sensorEvent.sensor.getType();
        float [] values = sensorEvent.values;
        float magField_x=0,magField_y=0,magField_z=0;
        long lastUpdate = 0 ;

        synchronized (this){
            switch(sensor){
                case Sensor.TYPE_ACCELEROMETER :
                    long curTime = System.currentTimeMillis();
                    if ( curTime - lastUpdate >100){
                        lastUpdate = curTime;
                        magField_y = values[1] ;
                        isJumping = magField_y - old_y > 1.5 ;
                        old_y = magField_y ;
                    }
                    break;
                case Sensor.TYPE_GAME_ROTATION_VECTOR:

                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

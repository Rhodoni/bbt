package com.ut3.bbt.game;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class CaptorActivity implements SensorEventListener {

    private SensorManager sm;
    private float old_x = 0, old_y = 0, old_z = 0 ;

    // Attributs
    public boolean isJumping = false;
    public double playerAcceleration = 0;
    public double lightFactor = 0;

    public void setUpSensors(Context context){
        sm = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME );
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR),SensorManager.SENSOR_DELAY_GAME );
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_GAME );
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensor = sensorEvent.sensor.getType();
        float [] values = sensorEvent.values;
        float magField_x=0,magField_y=0,magField_z=0;
        long lastUpdate = 0 ;

        synchronized (this) {
            switch(sensor) {
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

                    float[] rotationMatrix = new float[9];
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);

                    float[] orientationAngles = new float[3];
                    SensorManager.getOrientation(rotationMatrix, orientationAngles);

                    this.playerAcceleration = orientationAngles[2];

                    break;
                case Sensor.TYPE_LIGHT:
                    float value = sensorEvent.values[0];
                    float delta = 100;

                    // Normalisation par fonction sigmoid
                    this.lightFactor = value / (value + delta);
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

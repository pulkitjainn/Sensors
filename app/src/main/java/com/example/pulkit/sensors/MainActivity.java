package com.example.pulkit.sensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    public static final String TAG = "SENSORS";

    SensorManager sensMan;
    ImageView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ball = (ImageView) findViewById(R.id.ball);
        sensMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelSensor = sensMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensMan.registerListener(this, accelSensor, 1000 * 1000 );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensMan.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: ACCEL");
            int myColor = Color.rgb(
                    accelToCol(event.values[0]),
                    accelToCol(event.values[1]),
                    accelToCol(event.values[2])
            );
        ball.setColorFilter(myColor);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    int accelToCol (float accel) {
        accel = accel + 12;
        return (int) ((accel / 24) * 255);
    }
}


package com.consult.fitness;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.SENSOR_SERVICE;


public class HomeFragment extends Fragment implements SensorEventListener {

    private static final int PHYISCAL_ACTIVITY = 1;
    private SensorManager sensorManager;
    private Sensor mStepDetector;
    private boolean isDetectorSensorPresent;
    private ProgressBar progress_bar;
    private TextView txt_progress, txt_target;
    private int progress;
    private Bundle mbundle;
    String TAG = "HomeFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("Home");

        txt_target = (TextView) view.findViewById(R.id.txt_target);
        txt_progress = (TextView) view.findViewById(R.id.txt_progress);
        progress_bar = (ProgressBar) view.findViewById(R.id.progress_bar);



        txt_progress.setText(String.valueOf(progress));
        progress_bar.setProgress(progress);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            //ask for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, PHYISCAL_ACTIVITY);
                }
            }
        }


        sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            mStepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            isDetectorSensorPresent = true;
        } else
            isDetectorSensorPresent = false;


        return view;

    }




    @Override
    public void onResume() {

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            sensorManager.registerListener(this, mStepDetector, SensorManager.SENSOR_DELAY_NORMAL);
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            sensorManager.unregisterListener(this, mStepDetector);
        }
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor == mStepDetector) {
            progress++;
            txt_progress.setText(String.valueOf(progress));
            progress_bar.setProgress(progress);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        int test=20;
        outState.putString("progress", String.valueOf(test));

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle outState) {
        super.onViewStateRestored(outState);

    }
}


//if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
//            mStepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
//            isCounterSensorPresent = true;
//            Log.i("CHECK", "oifc: " + stepDetect);
//
//        } else {
//            isCounterSensorPresent = false;
//        }
//
//        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
//            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
//            isDetectorSensorPresent = true;
//            Log.i("CHECK", "oifd: " + stepDetect);
//        } else {
//            isDetectorSensorPresent = false;
//        }

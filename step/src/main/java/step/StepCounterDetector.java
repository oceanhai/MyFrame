package step;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * 通过Sensor.TYPE_STEP_COUNTER  来监听计步
 */
public class StepCounterDetector implements SensorEventListener {


    private static StepCounterDetector mStepCounterDetector;

    private StepCounterDetector() {

    }

    public static StepCounterDetector getIns() {
        if (mStepCounterDetector == null) {
            mStepCounterDetector = new StepCounterDetector();
        }
        return mStepCounterDetector;
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {
        Log.d("StepCounterManager", "传感器 step返回的值  " + (int) event.values[0]);

        StepCountManager.getIns()
                .setStepCountFromSensorStep((int) event.values[0]);

    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

    }
}

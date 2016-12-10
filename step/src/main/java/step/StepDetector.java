/*
 *  Pedometer - Android App
 *  Copyright (C) 2009 Levente Bagi
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package step;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;


/**
 * Detects steps and notifies all listeners (that implement StepListener).
 * <p/>
 * 通过Sensor.TYPE_ACCELEROMETER 来监听计步
 *
 * @todo REFACTOR: SensorListener is deprecated
 */
public class StepDetector implements SensorEventListener {
    private final static String TAG = "StepDetector";
    public static ArrayList<StepListener> mStepListeners = new ArrayList<StepListener>();
    private static StepDetector stepDetector;
    private float mLimit = 6;
    private float mLastValues[] = new float[3 * 2];
    private float mScale[] = new float[2];
    private float mYOffset;
    private float mLastDirections[] = new float[3 * 2];
    private float mLastExtremes[][] = {new float[3 * 2], new float[3 * 2]};
    private float mLastDiff[] = new float[3 * 2];
    private int mLastMatch = -1;
    private int currentStep = 0;

    private StepDetector() {
        int h = 480; // TODO: remove this constant
        mYOffset = h * 0.5f;
        mScale[0] = -(h * 0.5f * (1.0f / (SensorManager.STANDARD_GRAVITY * 2)));
        mScale[1] = -(h * 0.5f * (1.0f / (SensorManager.MAGNETIC_FIELD_EARTH_MAX)));
    }

    public static StepDetector getIns() {
        if (stepDetector == null) {
            synchronized (StepDetector.class) {
                if (stepDetector == null) {
                    stepDetector = new StepDetector();
                }
            }
        }
        return stepDetector;
    }

    public static void addStepListener(StepListener sl) {
        //Log.e(TAG, "Thread   " + Thread.currentThread());
        mStepListeners.add(sl);
    }

    public void setSensitivity(float sensitivity) {
        mLimit = sensitivity; // 1.97  2.96  4.44  6.66  10.00  15.00  22.50  33.75  50.62
    }

    //public void onSensorChanged(int sensor, float[] values) {
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        synchronized (this) {

            if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
                //方向传感器
            } else {
                //三轴加速度感应器
                int j = (sensor.getType() == Sensor.TYPE_ACCELEROMETER) ? 1 : 0;
                if (j == 1) {
                    float vSum = 0;
                    for (int i = 0; i < 3; i++) {
                        final float v = mYOffset + event.values[i] * mScale[j];
                        vSum += v;
                    }
                    int k = 0;
                    float v = vSum / 3;

                    float direction = (v > mLastValues[k] ? 1 : (v < mLastValues[k] ? -1 : 0));
                    if (direction == -mLastDirections[k]) {
                        // Direction changed
                        int extType = (direction > 0 ? 0 : 1); // minumum or maximum?
                        mLastExtremes[extType][k] = mLastValues[k];
                        float diff = Math.abs(mLastExtremes[extType][k] - mLastExtremes[1 - extType][k]);

                        if (diff > mLimit) {
                            // Log.e("step", "============detector==========");
                            boolean isAlmostAsLargeAsPrevious = diff > (mLastDiff[k] * 2 / 3);
                            boolean isPreviousLargeEnough = mLastDiff[k] > (diff / 3);
                            boolean isNotContra = (mLastMatch != 1 - extType);

                            if (isAlmostAsLargeAsPrevious && isPreviousLargeEnough && isNotContra) {

                                //如果内存中步数为0，那么从数据库中读取一下
                                if (StepCountManager.getIns()
                                        .getStepCountFromSensorAccInMemory() == 0 && StepCountManager.getIns()
                                        .getStepCountFromSensorStepInMemory() == 0) {
                                    //TODO 取值而不赋值  没实际意义
                                    StepCountManager.getIns().getStepCount();
                                }

                                //Log.d(TAG, "currentTime   " + System.currentTimeMillis());

                                //Log.i(T÷AG, "step=====");
                                if (StepManager.isLogined()) {
                                    StepCountManager.getIns().addStepCountForSensorAcc();
                                }

                                //Log.e(TAG, "Thread   " + Thread.currentThread());
                                for (StepListener stepListener : mStepListeners) {
                                    //由于增加了时间判断,所以当步数未改变时也会回调onStep方法.
                                    //增加一个步数的判断,让步数改变时才进行回调
                                    int stepCount = StepCountManager.getIns().getStepCount();
                                    /**
                                     * 这里把步数的判断逻辑删除，不然实现不了多回调
                                     */
//                                    if (currentStep != stepCount) {
                                    stepListener.onStep(stepCount);
                                    currentStep = stepCount;
//                                    }
                                    Log.e(TAG, "step============" + stepListener.toString());
                                }
                                mLastMatch = extType;
                            } else {
                                mLastMatch = -1;
                            }
                        }
                        mLastDiff[k] = diff;
                    }
                    mLastDirections[k] = direction;
                    mLastValues[k] = v;
                }
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

}